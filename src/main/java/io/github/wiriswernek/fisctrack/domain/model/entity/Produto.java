package io.github.wiriswernek.fisctrack.domain.model.entity;

import io.github.wiriswernek.fisctrack.domain.model.enums.SituacaoProdutoEnum;
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
@Table(name = "PRODUTO")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "DESCRICAO")
	private String descricao;

	@Column(name = "SITUACAO")
	@Enumerated(EnumType.STRING)
	private SituacaoProdutoEnum situacao;

	@PrePersist
	public void prePersist() {
		this.normalize();
	}

	@PreUpdate
	public void preUpdate() {
		this.normalize();
	}

	private void normalize() {
		this.setDescricao(this.getDescricao().toUpperCase());
	}
}
