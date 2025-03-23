package io.github.wiriswernek.fisctrack.domain.model.dto.request;

import io.github.wiriswernek.fisctrack.domain.model.enums.SituacaoFornecedorEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FornecedorRequest {
    @NotBlank(message = "Razão Social é obrigatória")
    private String razaoSocial;
    @NotBlank(message = "CNPJ é obrigatório")
    private String cnpj;
    @NotBlank(message = "Email é obrigatório")
    private String email;
    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;
    @NotNull(message = "Situação é obrigatória")
    private SituacaoFornecedorEnum situacao;
}
