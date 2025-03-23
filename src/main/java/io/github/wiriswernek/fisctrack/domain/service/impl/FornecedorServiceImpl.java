package io.github.wiriswernek.fisctrack.domain.service.impl;

import io.github.wiriswernek.fisctrack.core.exceptions.BusinessException;
import io.github.wiriswernek.fisctrack.core.exceptions.ValidationException;
import io.github.wiriswernek.fisctrack.core.mapper.FornecedorMapper;
import io.github.wiriswernek.fisctrack.domain.model.dto.filter.FornecedorFilter;
import io.github.wiriswernek.fisctrack.domain.model.dto.request.FornecedorRequest;
import io.github.wiriswernek.fisctrack.domain.model.dto.response.FornecedorResponse;
import io.github.wiriswernek.fisctrack.domain.model.repository.FornecedorRepository;
import io.github.wiriswernek.fisctrack.domain.model.repository.ItemNotaFiscalRepository;
import io.github.wiriswernek.fisctrack.domain.model.repository.NotaFiscalRepository;
import io.github.wiriswernek.fisctrack.domain.service.FornecedorService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
@RequiredArgsConstructor
public class FornecedorServiceImpl implements FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final NotaFiscalRepository notaFiscalRepository;
    private final Validator validator;

    @Override
    public List<FornecedorResponse> search(FornecedorFilter filter) throws Exception {
        var query = fornecedorRepository.search(filter);
        return query.list().stream().map(FornecedorMapper::toResponseByEntity).collect(Collectors.toList());
    }

    @Override
    public FornecedorResponse findById(Long id) throws Exception {
        var entity = fornecedorRepository.findById(id);
        if (entity == null) {
            return null;
        }
        return FornecedorMapper.toResponseByEntity(entity);
    }

    @Override
    public void create(FornecedorRequest fornecedorRequest) throws Exception {
        validate(fornecedorRequest);

        var query = fornecedorRepository.findByCnpj(fornecedorRequest.getCnpj());
        if (!query.list().isEmpty()) {
            throw new BusinessException("CNPJ já cadastrado para outro fornecedor");
        }

        var entity = FornecedorMapper.toEntityByRequest(fornecedorRequest);
        fornecedorRepository.persist(entity);
    }

    @Override
    public void update(Long id, FornecedorRequest fornecedorRequest) throws Exception {
        var entity = fornecedorRepository.findById(id);
        if (entity == null) {
            throw new BusinessException("Fornecedor não encontrado");
        }

        validate(fornecedorRequest);

        var query = fornecedorRepository.findByCnpjAndNotId(fornecedorRequest.getCnpj(), id);
        if (!query.list().isEmpty()) {
            throw new BusinessException("CNPJ já cadastrado para outro fornecedor");
        }

        entity.setCnpj(fornecedorRequest.getCnpj());
        entity.setRazaoSocial(fornecedorRequest.getRazaoSocial());
        entity.setTelefone(fornecedorRequest.getTelefone());
        entity.setEmail(fornecedorRequest.getEmail());
        entity.setSituacao(fornecedorRequest.getSituacao());
    }

    @Override
    public void delete(Long id) throws Exception {
        var entity = fornecedorRepository.findById(id);
        if (entity == null) {
            throw new BusinessException("Fornecedor não encontrado");
        }

        var notaFiscal = notaFiscalRepository.find("fornecedor", entity).list();
        if (!notaFiscal.isEmpty()) {
            throw new BusinessException("Fornecedor não pode ser excluído, pois está vinculado a uma nota fiscal");
        }
        fornecedorRepository.delete(entity);
    }

    private void validate(FornecedorRequest fornecedorRequest) throws Exception {
        Set<ConstraintViolation<FornecedorRequest>> violations = validator.validate(fornecedorRequest);
        if (!violations.isEmpty()) {
            throw ValidationException.generateMessage(violations);
        }
    }
}
