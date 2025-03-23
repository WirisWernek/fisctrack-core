package io.github.wiriswernek.fisctrack.domain.model.repository;

import io.github.wiriswernek.fisctrack.domain.model.entity.ItemNotaFiscal;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemNotaFiscalRepository implements PanacheRepository<ItemNotaFiscal> {
}
