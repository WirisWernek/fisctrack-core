package io.github.wiriswernek.fisctrack.domain.model.repository;

import java.util.HashMap;
import java.util.Map;

import io.github.wiriswernek.fisctrack.domain.model.dto.filter.ProdutoFilter;
import io.github.wiriswernek.fisctrack.domain.model.entity.Produto;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {

    public PanacheQuery<Produto> search(ProdutoFilter filter) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        builder.append("SELECT p FROM Produto p WHERE 1=1 ");

        if (filter.getId() != null) {
            builder.append(" AND p.id = :id ");
            params.put("id", filter.getId());
        }

        if (filter.getDescricao() != null && !filter.getDescricao().trim().isEmpty()) {
            builder.append(" AND p.descricao like ");
            builder.append("'%").append(filter.getDescricao().toUpperCase()).append("%'");
        }

        if (filter.getSituacao() != null) {
            builder.append(" AND p.situacao = :situacao ");
            params.put("situacao", filter.getSituacao());
        }

        return this.find(builder.toString(), params);
    }


}
