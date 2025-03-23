package io.github.wiriswernek.fisctrack.domain.model.dto.response;

import io.github.wiriswernek.fisctrack.domain.model.enums.SituacaoFornecedorEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FornecedorResponse {
    private Long id;
    private String razaoSocial;
    private String cnpj;
    private String email;
    private String telefone;
    private SituacaoFornecedorEnum situacao;
    private LocalDate dataBaixa;
}
