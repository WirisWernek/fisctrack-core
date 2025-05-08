package io.github.wiriswernek.fisctrack.domain.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequest {
	@NotBlank(message = "CEP é obrigatório")
	private String cep;
	@NotBlank(message = "Rua é obrigatório")
	private String rua;
	@NotNull(message = "Número é obrigatório")
	private Integer numero;
	@NotBlank(message = "Bairro é obrigatório")
	private String bairro;
	@NotBlank(message = "Cidade é obrigatória")
	private String cidade;
	@NotBlank(message = "Estado é obrigatório")
	private String estado;
	@NotBlank(message = "Pais é obrigatório")
	private String pais;

}
