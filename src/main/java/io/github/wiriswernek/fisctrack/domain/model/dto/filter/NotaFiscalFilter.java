package io.github.wiriswernek.fisctrack.domain.model.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class NotaFiscalFilter {
    private String numeroNota;
    private Long fornecedorId;
    private LocalDate dataEmissaoInicio;
    private LocalDate dataEmissaoFim;

    public NotaFiscalFilter(String numeroNota, Long fornecedorId, String dataEmissaoInicio, String dataEmissaoFim) {
        this.numeroNota = numeroNota;
        this.fornecedorId = fornecedorId;
        this.dataEmissaoInicio = dataEmissaoInicio != null && !dataEmissaoInicio.trim().isEmpty() ? LocalDate.parse(dataEmissaoInicio) : null;
        this.dataEmissaoFim = dataEmissaoFim != null && !dataEmissaoFim.trim().isEmpty() ? LocalDate.parse(dataEmissaoFim) : null;
    }
}
