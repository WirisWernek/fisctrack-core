package io.github.wiriswernek.fisctrack.domain.service;

import io.github.wiriswernek.fisctrack.domain.model.dto.filter.ProdutoFilter;
import io.github.wiriswernek.fisctrack.domain.model.dto.request.ProdutoRequest;
import io.github.wiriswernek.fisctrack.domain.model.dto.response.ProdutoResponse;

import java.util.List;

public interface ProdutoService {
    List<ProdutoResponse> search(ProdutoFilter filter) throws Exception;

    ProdutoResponse findById(Long id) throws Exception;

    void create(ProdutoRequest produtoRequest) throws Exception;

    void update(Long id, ProdutoRequest produtoRequest) throws Exception;

    void delete(Long id) throws Exception;
}
