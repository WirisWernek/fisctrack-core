package io.github.wiriswernek.fisctrack.domain.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotaFiscalRequest {
    @NotBlank(message = "Número da nota é obrigatório")
    private String numeroNota;
    @NotNull(message = "Total é obrigatório")
    private Float total;
    @NotNull(message = "Fornecedor é obrigatório")
    private Long fornecedorId;
    @NotNull(message = "Enderço é obrigatório")
    private EnderecoRequest endereco;
}
