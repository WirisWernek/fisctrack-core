package io.github.wiriswernek.fisctrack.rest.controller;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import io.github.wiriswernek.fisctrack.core.exceptions.ApiErrors;
import io.github.wiriswernek.fisctrack.domain.model.dto.filter.FornecedorFilter;
import io.github.wiriswernek.fisctrack.domain.model.dto.request.FornecedorRequest;
import io.github.wiriswernek.fisctrack.domain.model.dto.response.FornecedorResponse;
import io.github.wiriswernek.fisctrack.domain.model.enums.SituacaoFornecedorEnum;
import io.github.wiriswernek.fisctrack.domain.service.FornecedorService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/fornecedor")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FornecedorController {

	@Inject
	private FornecedorService fornecedorService;

	@GET
	@APIResponse(responseCode = "200", description = "Lista de fornecedores", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FornecedorResponse[].class)))
	@APIResponse(responseCode = "400", description = "Erro de validação", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	public List<FornecedorResponse> getAll( @QueryParam("razaoSocial") String razaoSocial, @QueryParam("cnpj") String cnpj, @QueryParam("situacao") String situacao) throws Exception {
		var filter = new FornecedorFilter(razaoSocial, cnpj, SituacaoFornecedorEnum.parse(situacao));
		return fornecedorService.search(filter);
	}

	@GET
	@Path("/{id}")
	@APIResponse(responseCode = "200", description = "Lista de fornecedores", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FornecedorResponse.class)))
	@APIResponse(responseCode = "400", description = "Erro de validação", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	public Response getById(@PathParam("id") Long id) throws Exception {
		var fornecedor = fornecedorService.findById(id);
		return Response.ok(fornecedor).build();
	}

	@POST
	@Transactional
	@APIResponse(responseCode = "201", description = "Fornecedor criado com sucesso")
	@APIResponse(responseCode = "400", description = "Erro de validação", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	public Response create(FornecedorRequest fornecedor) throws Exception {
		fornecedorService.create(fornecedor);
		return Response.status(Response.Status.CREATED).build();
	}

	@PUT
	@Path("/{id}")
	@Transactional
	@APIResponse(responseCode = "200", description = "Fornecedor atualizado com sucesso")
	@APIResponse(responseCode = "400", description = "Erro de validação", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	public Response update(@PathParam("id") Long id, FornecedorRequest fornecedor) throws Exception {
		fornecedorService.update(id, fornecedor);
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	@APIResponse(responseCode = "200", description = "Fornecedor excluído com sucesso")
	@APIResponse(responseCode = "400", description = "Erro de validação", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	public Response delete(@PathParam("id") Long id) throws Exception {
		fornecedorService.delete(id);
		return Response.noContent().build();
	}

	@PATCH
	@Path("/{id}/baixa")
	@Transactional
	@APIResponse(responseCode = "200", description = "Fornecedor baixado com sucesso")
	@APIResponse(responseCode = "400", description = "Erro de validação", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	public Response baixar(@PathParam("id") Long id) throws Exception {
		fornecedorService.baixar(id);
		return Response.noContent().build();
	}
}
