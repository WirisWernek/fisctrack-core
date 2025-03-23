package io.github.wiriswernek.fisctrack.domain.model.dto.filter;

import io.github.wiriswernek.fisctrack.domain.model.enums.SituacaoProdutoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProdutoFilter {
    private Long id;
    private String descricao;
    private SituacaoProdutoEnum situacao;
}
