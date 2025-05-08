package io.github.wiriswernek.fisctrack.domain.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemNotaFiscalRequest {
	@NotNull(message = "Produto é obrigatório")
	private Long produtoId;
	@NotNull(message = "Quantidade é obrigatória")
	private Integer quantidade;
	@NotNull(message = "Valor unitário é obrigatório")
	private Float valorUnitario;
}
