package io.github.wiriswernek.fisctrack.rest.controller;

import io.github.wiriswernek.fisctrack.core.baseclass.BaseController;
import io.github.wiriswernek.fisctrack.domain.model.dto.filter.NotaFiscalFilter;
import io.github.wiriswernek.fisctrack.domain.model.dto.request.NotaFiscalRequest;
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
import lombok.RequiredArgsConstructor;

@Path("/api/nota-fiscal")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class NotaFiscalController extends BaseController {

    @Inject
    NotaFiscalService notaFiscalService;

    @GET
    public Response getAll(@QueryParam("numeroNota") String numeroNota, @QueryParam("fornecedorId") Long fornecedorId, @QueryParam("dataEmissaoInicio") String dataEmissaoInicio, @QueryParam("dataEmissaoFim") String dataEmissaoFim) {
        try {
            var filter = new NotaFiscalFilter(numeroNota, fornecedorId, dataEmissaoInicio, dataEmissaoFim);
            var notasFiscais = notaFiscalService.search(filter);
            return Response.ok(notasFiscais).build();
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        try {
            var notaFiscal = notaFiscalService.findById(id);
            return Response.ok(notaFiscal).build();
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @POST
    @Transactional
    public Response create(NotaFiscalRequest notaFiscal) {
        try {
            notaFiscalService.create(notaFiscal);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, NotaFiscalRequest notaFiscalRequest) {
        try {
            notaFiscalService.update(id, notaFiscalRequest);
            return Response.noContent().build();
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            notaFiscalService.delete(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return handleException(e);
        }
    }
}
