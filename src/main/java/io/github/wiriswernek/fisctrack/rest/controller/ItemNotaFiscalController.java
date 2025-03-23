package io.github.wiriswernek.fisctrack.rest.controller;

import io.github.wiriswernek.fisctrack.core.baseclass.BaseController;
import io.github.wiriswernek.fisctrack.domain.service.ItemNotaFiscalService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/api/nota-fiscal/{notaFiscalId}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class ItemNotaFiscalController extends BaseController {
//    private final ItemNotaFiscalService itemNotaFiscalService;

    @GET
    public Response getAll() {
        try {
            return Response.ok().build();
        } catch (Exception e) {
            return handleException(e);
        }
    }
}
