package io.github.wiriswernek.fisctrack.domain.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "NOTA_FISCAL")
public class NotaFiscal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NUMERO_NOTA")
	private String numeroNota;

	@Column(name = "EMISSAO")
	private LocalDateTime emissao;

	@Column(name = "TOTAL")
	private Float total;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORNECEDOR_ID")
	private Fornecedor fornecedor;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ENDERECO_ID")
	private Endereco endereco;

	@OneToMany(mappedBy = "notaFiscal", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemNotaFiscal> itens;

	@PrePersist()
	public void prePersist() {
		this.emissao = LocalDateTime.now();

		this.total = itens.stream()
				.map(item -> item.getValorUnitario() * item.getQuantidade())
				.reduce(0f, Float::sum);
	}

	@PreUpdate()
	public void preUpdate() {
		this.total = itens.stream()
				.map(item -> item.getValorUnitario() * item.getQuantidade())
				.reduce(0f, Float::sum);
	}

	public void addItem(ItemNotaFiscal item) {
		if (this.itens == null) {
			this.itens = new ArrayList<>();
		}
		this.itens.add(item);
		item.setNotaFiscal(this);
	}

	public void removeItem(ItemNotaFiscal item) {
		if (this.itens == null) {
			this.itens = new ArrayList<>();
			return;
		}
		this.itens.remove(item);
		item.setNotaFiscal(null);
	}
}
