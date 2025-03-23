package io.github.wiriswernek.fisctrack.domain.service;

import io.github.wiriswernek.fisctrack.domain.model.dto.filter.FornecedorFilter;
import io.github.wiriswernek.fisctrack.domain.model.dto.request.FornecedorRequest;
import io.github.wiriswernek.fisctrack.domain.model.dto.response.FornecedorResponse;

import java.util.List;

public interface FornecedorService {
    List<FornecedorResponse> search(FornecedorFilter filter) throws Exception;

    FornecedorResponse findById(Long id) throws Exception;

    void create(FornecedorRequest fornecedorRequest) throws Exception;

    void update(Long id, FornecedorRequest fornecedorRequest) throws Exception;

    void delete(Long id) throws Exception;
}
