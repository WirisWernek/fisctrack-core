package io.github.wiriswernek.fisctrack.domain.model.dto.request;

import io.github.wiriswernek.fisctrack.domain.model.enums.SituacaoProdutoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequest {
    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotNull(message = "Situação é obrigatória")
    private SituacaoProdutoEnum situacao;
}
