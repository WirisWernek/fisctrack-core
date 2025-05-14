package io.github.wiriswernek.fisctrack.rest.controller;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import io.github.wiriswernek.fisctrack.core.exceptions.ApiErrors;
import io.github.wiriswernek.fisctrack.domain.model.dto.filter.NotaFiscalFilter;
import io.github.wiriswernek.fisctrack.domain.model.dto.request.NotaFiscalRequest;
import io.github.wiriswernek.fisctrack.domain.model.dto.response.NotaFiscalResponse;
import io.github.wiriswernek.fisctrack.domain.service.NotaFiscalService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/nota-fiscal")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotaFiscalController {

	@Inject
	private NotaFiscalService notaFiscalService;

	@GET
	@APIResponse(responseCode = "200", description = "Lista de Notas Fiscais", content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotaFiscalResponse[].class)))
	@APIResponse(responseCode = "400", description = "Erro de validação", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	public Response getAll(@QueryParam("numeroNota") String numeroNota, @QueryParam("fornecedorId") Long fornecedorId, @QueryParam("dataEmissaoInicio") String dataEmissaoInicio, @QueryParam("dataEmissaoFim") String dataEmissaoFim) throws Exception {
		var filter = new NotaFiscalFilter(numeroNota, fornecedorId, dataEmissaoInicio, dataEmissaoFim);
		var notasFiscais = notaFiscalService.search(filter);
		return Response.ok(notasFiscais).build();
	}

	@GET
	@Path("/{id}")
	@APIResponse(responseCode = "200", description = "Nota Fiscal encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotaFiscalResponse.class)))
	@APIResponse(responseCode = "400", description = "Erro de validação", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	public Response getById(@PathParam("id") Long id) throws Exception {
		var notaFiscal = notaFiscalService.findById(id);
		return Response.ok(notaFiscal).build();
	}

	@POST
	@Transactional
	@APIResponse(responseCode = "201", description = "Nota Fiscal criada com sucesso")
	@APIResponse(responseCode = "400", description = "Erro de validação", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	public Response create(NotaFiscalRequest notaFiscal) throws Exception {
		notaFiscalService.create(notaFiscal);
		return Response.status(Response.Status.CREATED).build();
	}

	@PUT
	@Path("/{id}")
	@Transactional
	@APIResponse(responseCode = "200", description = "Nota Fiscal atualizada com sucesso")
	@APIResponse(responseCode = "400", description = "Erro de validação", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	public Response update(@PathParam("id") Long id, NotaFiscalRequest notaFiscalRequest) throws Exception {
		notaFiscalService.update(id, notaFiscalRequest);
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	@APIResponse(responseCode = "200", description = "Nota Fiscal excluída com sucesso")
	@APIResponse(responseCode = "400", description = "Erro de validação", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	@APIResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrors.class)))
	public Response delete(@PathParam("id") Long id) throws Exception {
		notaFiscalService.delete(id);
		return Response.noContent().build();
	}
}
