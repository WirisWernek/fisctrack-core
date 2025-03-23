package io.github.wiriswernek.fisctrack.core.baseclass;


import io.github.wiriswernek.fisctrack.core.exceptions.ApiErrors;
import io.github.wiriswernek.fisctrack.core.exceptions.BusinessException;
import io.github.wiriswernek.fisctrack.core.exceptions.ValidationException;
import jakarta.ws.rs.core.Response;

import java.util.List;

public abstract class BaseController {

    public Response handleException(Exception e) {
        if (e instanceof BusinessException) {
            var error = new ApiErrors(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        } else if (e instanceof ValidationException) {
            var errors = e.getMessage();
            var error = new ApiErrors();
            error.setErrors(List.of(errors.split(";")));
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        } else {
            var error = new ApiErrors(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }
}
