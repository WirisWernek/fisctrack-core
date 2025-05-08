package io.github.wiriswernek.fisctrack.domain.model.repository;

import java.util.HashMap;
import java.util.Map;

import io.github.wiriswernek.fisctrack.domain.model.dto.filter.NotaFiscalFilter;
import io.github.wiriswernek.fisctrack.domain.model.entity.NotaFiscal;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotaFiscalRepository implements PanacheRepository<NotaFiscal> {
    public PanacheQuery<NotaFiscal> search(NotaFiscalFilter filter) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        builder.append("SELECT nf FROM NotaFiscal nf WHERE 1=1 ");

        if (filter.getNumeroNota() != null && !filter.getNumeroNota().trim().isEmpty()) {
            builder.append(" AND nf.numeroNota = :numeroNota ");
            params.put("numeroNota", filter.getNumeroNota());
        }

        if (filter.getFornecedorId() != null) {
            builder.append(" AND nf.fornecedor.id = :fornecedorId ");
            params.put("fornecedorId", filter.getFornecedorId());
        }

        if (filter.getDataEmissaoInicio() != null) {
            builder.append(" AND cast(nf.emissao as date) >= cast(:emissaoInicio as date) ");
            params.put("emissaoInicio", filter.getDataEmissaoInicio());
        }

        if (filter.getDataEmissaoFim() != null) {
            builder.append(" AND cast(nf.emissao as date) <= cast(:emissaoFim as date) ");
            params.put("emissaoFim", filter.getDataEmissaoFim());
        }

        return this.find(builder.toString(), Sort.by("id").ascending(), params);
    }
}
