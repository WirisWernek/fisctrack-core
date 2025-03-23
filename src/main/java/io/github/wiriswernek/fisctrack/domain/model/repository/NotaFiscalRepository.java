package io.github.wiriswernek.fisctrack.domain.model.repository;

import io.github.wiriswernek.fisctrack.domain.model.entity.NotaFiscal;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotaFiscalRepository implements PanacheRepository<NotaFiscal> {
}
