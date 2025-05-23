package io.github.wiriswernek.fisctrack.domain.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemNotaFiscalResponse {
	private Long id;
	private Integer quantidade;
	private Float valorUnitario;
	private ProdutoResponse produto;
}
