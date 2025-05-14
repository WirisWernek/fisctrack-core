package io.github.wiriswernek.fisctrack.rest.controller;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import io.github.wiriswernek.fisctrack.core.exceptions.ApiErrors;
import io.github.wiriswernek.fisctrack.domain.model.dto.filter.ProdutoFilter;
import io.github.wiriswernek.fisctrack.domain.model.dto.request.ProdutoRequest;
import io.github.wiriswernek.fisctrack.domain.model.dto.response.ProdutoResponse;
import io.github.wiriswernek.fisctrack.domain.model.enums.SituacaoProdutoEnum;
import io.github.wiriswernek.fisctrack.domain.service.ProdutoService;
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

@Path("/produto")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoController {

	@Inject
	private ProdutoService produtoService;

	@GET
	@APIResponse(responseCode = "200", description = "Lista de Produtos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoResponse[].class)))
	@APIResponse(responseCode = "400", description = "Erro de validação", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	public Response getAll(@QueryParam("id") Long id, @QueryParam("descricao") String descricao, @QueryParam("situacao") String situacao) throws Exception {
		var filter = new ProdutoFilter(id, descricao, SituacaoProdutoEnum.parse(situacao));
		var produtos = produtoService.search(filter);
		return Response.ok(produtos).build();
	}

	@GET
	@Path("/{id}")
	@APIResponse(responseCode = "200", description = "Produto encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoResponse.class)))
	@APIResponse(responseCode = "400", description = "Erro de validação", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	public Response getById(@PathParam("id") Long id) throws Exception {
		var produto = produtoService.findById(id);
		return Response.ok(produto).build();
	}

	@POST
	@Transactional
	@APIResponse(responseCode = "201", description = "Produto criado com sucesso")
	@APIResponse(responseCode = "400", description = "Erro de validação", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	public Response create(ProdutoRequest produto) throws Exception {
		produtoService.create(produto);
		return Response.status(Response.Status.CREATED).build();
	}

	@PUT
	@Path("/{id}")
	@Transactional
	@APIResponse(responseCode = "200", description = "Produto atualizado com sucesso")
	@APIResponse(responseCode = "400", description = "Erro de validação", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	public Response update(@PathParam("id") Long id, ProdutoRequest produto) throws Exception {
		produtoService.update(id, produto);
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	@APIResponse(responseCode = "200", description = "Produto excluído com sucesso")
	@APIResponse(responseCode = "400", description = "Erro de validação", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	public Response delete(@PathParam("id") Long id) throws Exception {
		produtoService.delete(id);
		return Response.noContent().build();
	}

	@PATCH
	@Path("/{id}/situacao")
	@Transactional
	@APIResponse(responseCode = "200", description = "Situação do produto atualizada com sucesso")
	@APIResponse(responseCode = "400", description = "Erro de validação", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	public Response updateSituacao(@PathParam("id") Long id) throws Exception {
		produtoService.updateSituacao(id);
		return Response.noContent().build();
	}
}
