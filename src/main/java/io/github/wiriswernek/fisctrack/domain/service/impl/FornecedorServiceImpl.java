package io.github.wiriswernek.fisctrack.domain.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import io.github.wiriswernek.fisctrack.core.exceptions.BusinessException;
import io.github.wiriswernek.fisctrack.core.exceptions.ValidationException;
import io.github.wiriswernek.fisctrack.core.mapper.FornecedorMapper;
import io.github.wiriswernek.fisctrack.domain.model.dto.filter.FornecedorFilter;
import io.github.wiriswernek.fisctrack.domain.model.dto.request.FornecedorRequest;
import io.github.wiriswernek.fisctrack.domain.model.dto.response.FornecedorResponse;
import io.github.wiriswernek.fisctrack.domain.model.enums.SituacaoFornecedorEnum;
import io.github.wiriswernek.fisctrack.domain.model.repository.FornecedorRepository;
import io.github.wiriswernek.fisctrack.domain.model.repository.NotaFiscalRepository;
import io.github.wiriswernek.fisctrack.domain.service.FornecedorService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class FornecedorServiceImpl implements FornecedorService {

	private final FornecedorRepository fornecedorRepository;
	private final NotaFiscalRepository notaFiscalRepository;
	private final Validator validator;

	@Override
	public List<FornecedorResponse> search(FornecedorFilter filter) throws Exception {
		var query = fornecedorRepository.search(filter);
		return query.list().stream().map(FornecedorMapper::toResponseByEntity).toList();
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

		if (fornecedorRequest.getSituacao().equals(SituacaoFornecedorEnum.BAIXADO)
				&& !entity.getSituacao().equals(SituacaoFornecedorEnum.BAIXADO)) {
			entity.setSituacao(fornecedorRequest.getSituacao());
			entity.setDataBaixa(LocalDate.now());
		} else if (!fornecedorRequest.getSituacao().equals(SituacaoFornecedorEnum.BAIXADO)
				&& entity.getSituacao().equals(SituacaoFornecedorEnum.BAIXADO)) {
			entity.setSituacao(fornecedorRequest.getSituacao());
			entity.setDataBaixa(null);
		} else {
			entity.setSituacao(fornecedorRequest.getSituacao());
		}

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

	@Override
	public void baixar(Long id) throws Exception {
		var entity = fornecedorRepository.findById(id);
		if (entity == null) {
			throw new BusinessException("Fornecedor não encontrado");
		}

		entity.setSituacao(SituacaoFornecedorEnum.BAIXADO);
		entity.setDataBaixa(entity.getDataBaixa() == null ? java.time.LocalDate.now() : null);
		fornecedorRepository.persist(entity);
	}

	private void validate(FornecedorRequest fornecedorRequest) throws Exception {
		Set<ConstraintViolation<FornecedorRequest>> violations = validator.validate(fornecedorRequest);
		if (!violations.isEmpty()) {
			throw ValidationException.generateMessage(violations);
		}
	}

}
