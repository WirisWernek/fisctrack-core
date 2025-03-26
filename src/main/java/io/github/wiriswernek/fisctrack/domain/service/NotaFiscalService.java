package io.github.wiriswernek.fisctrack.domain.service;

import io.github.wiriswernek.fisctrack.domain.model.dto.filter.NotaFiscalFilter;
import io.github.wiriswernek.fisctrack.domain.model.dto.request.NotaFiscalRequest;
import io.github.wiriswernek.fisctrack.domain.model.dto.response.NotaFiscalResponse;

import java.util.List;

public interface NotaFiscalService {
    List<NotaFiscalResponse> search(NotaFiscalFilter filter) throws Exception;

    NotaFiscalResponse findById(Long id) throws Exception;

    void create(NotaFiscalRequest notaFiscalRequest) throws Exception;

    void update(Long id, NotaFiscalRequest notaFiscalRequest) throws Exception;

    void delete(Long id) throws Exception;
}
