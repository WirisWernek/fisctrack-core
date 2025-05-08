package io.github.wiriswernek.fisctrack.rest.controller;

import io.github.wiriswernek.fisctrack.core.baseclass.BaseController;
import io.github.wiriswernek.fisctrack.domain.model.dto.filter.FornecedorFilter;
import io.github.wiriswernek.fisctrack.domain.model.dto.request.FornecedorRequest;
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
import lombok.RequiredArgsConstructor;

@Path("/api/fornecedor")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class FornecedorController extends BaseController {

	@Inject
	private FornecedorService fornecedorService;

	@GET
	public Response getAll(@QueryParam("razaoSocial") String razaoSocial, @QueryParam("cnpj") String cnpj,
			@QueryParam("situacao") String situacao) {
		try {
			var filter = new FornecedorFilter(razaoSocial, cnpj, SituacaoFornecedorEnum.parse(situacao));
			var fornecedores = fornecedorService.search(filter);
			return Response.ok(fornecedores).build();
		} catch (Exception e) {
			return handleException(e);
		}
	}

	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") Long id) {
		try {
			var fornecedor = fornecedorService.findById(id);
			return Response.ok(fornecedor).build();
		} catch (Exception e) {
			return handleException(e);
		}
	}

	@POST
	@Transactional
	public Response create(FornecedorRequest fornecedor) {
		try {
			fornecedorService.create(fornecedor);
			return Response.status(Response.Status.CREATED).build();
		} catch (Exception e) {
			return handleException(e);
		}
	}

	@PUT
	@Path("/{id}")
	@Transactional
	public Response update(@PathParam("id") Long id, FornecedorRequest fornecedor) {
		try {
			fornecedorService.update(id, fornecedor);
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
			fornecedorService.delete(id);
			return Response.noContent().build();
		} catch (Exception e) {
			return handleException(e);
		}
	}

	@PATCH
	@Path("/{id}/baixa")
	@Transactional
	public Response baixar(@PathParam("id") Long id) {
		try {
			fornecedorService.baixar(id);
			return Response.noContent().build();
		} catch (Exception e) {
			return handleException(e);
		}
	}
}
