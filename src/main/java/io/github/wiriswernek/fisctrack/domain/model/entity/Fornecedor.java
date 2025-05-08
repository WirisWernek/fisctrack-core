package io.github.wiriswernek.fisctrack.domain.model.entity;

import java.time.LocalDate;

import io.github.wiriswernek.fisctrack.domain.model.enums.SituacaoFornecedorEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FORNECEDOR")
public class Fornecedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "RAZAO_SOCIAL")
	private String razaoSocial;

	@Column(name = "CNPJ")
	private String cnpj;

	@Column(name = "TELEFONE")
	private String telefone;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "DATA_BAIXA")
	private LocalDate dataBaixa;

	@Column(name = "SITUACAO")
	@Enumerated(EnumType.STRING)
	private SituacaoFornecedorEnum situacao;

	@PrePersist
	public void prePersist() {
		this.normalize();
	}

	@PreUpdate
	public void preUpdate() {
		this.normalize();
	}

	private void normalize() {
		this.setRazaoSocial(this.getRazaoSocial().toUpperCase());
		this.setEmail(this.getEmail().toLowerCase());
	}
}
