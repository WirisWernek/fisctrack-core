package io.github.wiriswernek.fisctrack.rest.controller;

import io.github.wiriswernek.fisctrack.core.baseclass.BaseController;
import io.github.wiriswernek.fisctrack.domain.model.dto.filter.ProdutoFilter;
import io.github.wiriswernek.fisctrack.domain.model.dto.request.ProdutoRequest;
import io.github.wiriswernek.fisctrack.domain.model.enums.SituacaoProdutoEnum;
import io.github.wiriswernek.fisctrack.domain.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/api/produto")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class ProdutoController extends BaseController {

    @Inject
    private ProdutoService produtoService;

    @GET
    public Response getAll(@QueryParam("id") Long id, @QueryParam("descricao") String descricao, @QueryParam("situacao") String situacao) {
        try {
            var filter = new ProdutoFilter(id, descricao, SituacaoProdutoEnum.parse(situacao));
            var produtos = produtoService.search(filter);
            return Response.ok(produtos).build();
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        try {
            var produto = produtoService.findById(id);
            return Response.ok(produto).build();
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @POST
    @Transactional
    public Response create(ProdutoRequest produto) {
        try {
            produtoService.create(produto);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, ProdutoRequest produto) {
        try {
            produtoService.update(id, produto);
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
            produtoService.delete(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return handleException(e);
        }
    }
}
