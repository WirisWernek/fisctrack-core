package io.github.wiriswernek.fisctrack.domain.service.impl;

import io.github.wiriswernek.fisctrack.core.exceptions.BusinessException;
import io.github.wiriswernek.fisctrack.core.exceptions.ValidationException;
import io.github.wiriswernek.fisctrack.core.mapper.NotaFiscalMapper;
import io.github.wiriswernek.fisctrack.domain.model.dto.filter.NotaFiscalFilter;
import io.github.wiriswernek.fisctrack.domain.model.dto.request.NotaFiscalRequest;
import io.github.wiriswernek.fisctrack.domain.model.dto.response.NotaFiscalResponse;
import io.github.wiriswernek.fisctrack.domain.model.entity.Fornecedor;
import io.github.wiriswernek.fisctrack.domain.model.entity.NotaFiscal;
import io.github.wiriswernek.fisctrack.domain.model.repository.FornecedorRepository;
import io.github.wiriswernek.fisctrack.domain.model.repository.NotaFiscalRepository;
import io.github.wiriswernek.fisctrack.domain.service.NotaFiscalService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
@RequiredArgsConstructor
public class NotaFiscalServiceImpl implements NotaFiscalService {

    private final NotaFiscalRepository notaFiscalRepository;
    private final FornecedorRepository fornecedorRepository;
    private final Validator validator;

    @Override
    public List<NotaFiscalResponse> search(NotaFiscalFilter filter) throws Exception {
        var query = notaFiscalRepository.search(filter);
        return query.list().stream().map(NotaFiscalMapper::toResponseByEntity).collect(Collectors.toList());
    }

    @Override
    public NotaFiscalResponse findById(Long id) throws Exception {
        var entity = notaFiscalRepository.findById(id);
        if (entity == null) {
            return null;
        }
        return NotaFiscalMapper.toResponseByEntity(entity);
    }

    @Override
    public void create(NotaFiscalRequest notaFiscalRequest) throws Exception {
        validate(notaFiscalRequest);

        var fornecedor = getFornecedor(notaFiscalRequest.getFornecedorId());
        var entity = NotaFiscalMapper.toEntityByRequest(notaFiscalRequest);

        entity.setFornecedor(fornecedor);
        notaFiscalRepository.persist(entity);
    }

    @Override
    public void update(Long id, NotaFiscalRequest notaFiscalRequest) throws Exception {
        var entity = getNotaFiscal(id);

        validate(notaFiscalRequest);
        var fornecedor = getFornecedor(notaFiscalRequest.getFornecedorId());

        entity.setNumeroNota(notaFiscalRequest.getNumeroNota());
        entity.setTotal(notaFiscalRequest.getTotal());
        entity.setFornecedor(fornecedor);

        var enderecoRequest = notaFiscalRequest.getEndereco();
        var endereco = entity.getEndereco();

        endereco.setBairro(enderecoRequest.getBairro());
        endereco.setCidade(enderecoRequest.getCidade());
        endereco.setEstado(enderecoRequest.getEstado());
        endereco.setNumero(enderecoRequest.getNumero());
        endereco.setPais(enderecoRequest.getPais());
        endereco.setRua(enderecoRequest.getRua());

    }

    @Override
    public void delete(Long id) throws Exception {
        var entity = getNotaFiscal(id);
        notaFiscalRepository.delete(entity);
    }

    private NotaFiscal getNotaFiscal(Long id) throws BusinessException {
        var entity = notaFiscalRepository.findById(id);
        if (entity == null) {
            throw new BusinessException("Nota Fiscal não encontrada");
        }
        return entity;
    }

    private void validate(NotaFiscalRequest notaFiscalRequest) throws Exception {
        Set<ConstraintViolation<NotaFiscalRequest>> violations = validator.validate(notaFiscalRequest);
        if (!violations.isEmpty()) {
            throw ValidationException.generateMessage(violations);
        }
    }

    private Fornecedor getFornecedor(Long fornecedorId) throws Exception {
        var fornecedor = fornecedorRepository.findById(fornecedorId);
        if (fornecedor == null) {
            throw new BusinessException("Fornecedor não encontrado");
        }
        return fornecedor;
    }
}
