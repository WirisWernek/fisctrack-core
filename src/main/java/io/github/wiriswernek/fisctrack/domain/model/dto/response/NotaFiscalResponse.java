package io.github.wiriswernek.fisctrack.domain.model.dto.response;

import io.github.wiriswernek.fisctrack.domain.model.dto.request.EnderecoRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotaFiscalResponse {
    private Long id;
    private String numeroNota;
    private Float total;
    private LocalDateTime emissao;
    private FornecedorResponse fornecedor;
    private EnderecoResponse endereco;
}
