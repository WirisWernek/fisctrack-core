package io.github.wiriswernek.fisctrack.domain.model.repository;

import io.github.wiriswernek.fisctrack.domain.model.dto.filter.FornecedorFilter;
import io.github.wiriswernek.fisctrack.domain.model.dto.filter.ProdutoFilter;
import io.github.wiriswernek.fisctrack.domain.model.entity.Fornecedor;
import io.github.wiriswernek.fisctrack.domain.model.entity.Produto;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class FornecedorRepository implements PanacheRepository<Fornecedor> {

    public PanacheQuery<Fornecedor> search(FornecedorFilter filter) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        builder.append("SELECT f FROM Fornecedor f WHERE 1=1 ");

        if (filter.getCnpj() != null && !filter.getCnpj().trim().isEmpty() ) {
            builder.append(" AND f.cnpj like ");
            builder.append("'%").append(filter.getCnpj()).append("%'");
        }

        if (filter.getRazaoSocial() != null && !filter.getRazaoSocial().trim().isEmpty()) {
            builder.append(" AND f.razaoSocial like ");
            builder.append("'%").append(filter.getRazaoSocial().toUpperCase()).append("%'");
        }

        if (filter.getSituacao() != null) {
            builder.append(" AND f.situacao = :situacao ");
            params.put("situacao", filter.getSituacao());
        }

        return this.find(builder.toString(), params);
    }

    public PanacheQuery<Fornecedor> findByCnpj(String cnpj) {
        return this.find("cnpj", cnpj);
    }

    public PanacheQuery<Fornecedor> findByCnpjAndNotId(String cnpj, Long id) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        builder.append("SELECT f FROM Fornecedor f WHERE 1=1 ");
        builder.append("AND f.cnpj = :cnpj ");
        builder.append("AND f.id != :id ");
        params.put("cnpj", cnpj);
        params.put("id", id);

        return this.find(builder.toString(), params);
    }
}
