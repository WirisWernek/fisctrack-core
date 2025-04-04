package io.github.wiriswernek.fisctrack.domain.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ENDERECO_ID")
    private Endereco endereco;

    @OneToMany(mappedBy = "notaFiscal", cascade = CascadeType.REMOVE)
    private List<ItemNotaFiscal> items;

    @PrePersist()
    public void prePersist() {
        this.emissao = LocalDateTime.now();
    }
}
