package io.github.wiriswernek.fisctrack.rest.controller;

import java.util.List;

import io.github.wiriswernek.fisctrack.core.exceptions.ApiErrors;
import io.github.wiriswernek.fisctrack.core.exceptions.BusinessException;
import io.github.wiriswernek.fisctrack.core.exceptions.ValidationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ControllerAdvice implements ExceptionMapper<Exception> {

	@Context
	UriInfo uriInfo;

	@Override
	public Response toResponse(Exception e) {
		if (e instanceof BusinessException businessException) {
			return handleBusinessException(businessException);
		} else if (e instanceof ValidationException validationException) {
			return handleValidationException(validationException);
		}

		return handleUnknownException(e);
	}

	private Response handleBusinessException(BusinessException ex) {
		var error = new ApiErrors(ex.getMessage());
		return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
	}

	private Response handleValidationException(ValidationException ex) {
		var errors = ex.getMessage();
		var error = new ApiErrors();
		error.setErrors(List.of(errors.split(";")));
		return Response.status(Response.Status.BAD_REQUEST).entity(error).build();

	}

	private Response handleUnknownException(Exception ex) {
		var error = new ApiErrors(ex.getMessage());
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();

	}
}