package io.github.wiriswernek.fisctrack.domain.model.dto.response;

import io.github.wiriswernek.fisctrack.domain.model.enums.SituacaoProdutoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoResponse {
    private Long id;
    private String descricao;
    private SituacaoProdutoEnum situacao;
}
