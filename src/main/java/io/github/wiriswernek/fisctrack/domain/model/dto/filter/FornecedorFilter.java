package io.github.wiriswernek.fisctrack.domain.model.dto.filter;

import io.github.wiriswernek.fisctrack.domain.model.enums.SituacaoFornecedorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FornecedorFilter {
    private String razaoSocial;
    private String cnpj;
    private SituacaoFornecedorEnum situacao;
}
