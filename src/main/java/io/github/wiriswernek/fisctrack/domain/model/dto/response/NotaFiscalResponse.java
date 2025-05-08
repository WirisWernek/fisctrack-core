package io.github.wiriswernek.fisctrack.domain.model.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private Long quantidadeProdutos;
	private List<ItemNotaFiscalResponse> itens;
}
