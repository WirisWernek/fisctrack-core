package io.github.wiriswernek.fisctrack.domain.service.impl;

import java.util.List;
import java.util.Set;

import io.github.wiriswernek.fisctrack.core.exceptions.BusinessException;
import io.github.wiriswernek.fisctrack.core.exceptions.ValidationException;
import io.github.wiriswernek.fisctrack.core.mapper.ProdutoMapper;
import io.github.wiriswernek.fisctrack.domain.model.dto.filter.ProdutoFilter;
import io.github.wiriswernek.fisctrack.domain.model.dto.request.ProdutoRequest;
import io.github.wiriswernek.fisctrack.domain.model.dto.response.ProdutoResponse;
import io.github.wiriswernek.fisctrack.domain.model.enums.SituacaoProdutoEnum;
import io.github.wiriswernek.fisctrack.domain.model.repository.ItemNotaFiscalRepository;
import io.github.wiriswernek.fisctrack.domain.model.repository.ProdutoRepository;
import io.github.wiriswernek.fisctrack.domain.service.ProdutoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

	private final ProdutoRepository produtoRepository;
	private final ItemNotaFiscalRepository itemNotaFiscalRepository;
	private final Validator validator;

	@Override
	public List<ProdutoResponse> search(ProdutoFilter filter) throws Exception {
		var query = produtoRepository.search(filter);
		return query.list().stream().map(ProdutoMapper::toResponseByEntity).toList();
	}

	@Override
	public ProdutoResponse findById(Long id) throws Exception {
		var entity = produtoRepository.findById(id);
		if (entity == null) {
			return null;
		}
		return ProdutoMapper.toResponseByEntity(entity);
	}

	@Override
	public void create(ProdutoRequest produtoRequest) throws Exception {
		Set<ConstraintViolation<ProdutoRequest>> violations = validator.validate(produtoRequest);
		if (!violations.isEmpty()) {
			throw ValidationException.generateMessage(violations);
		}
		var entity = ProdutoMapper.toEntityByRequest(produtoRequest);
		produtoRepository.persist(entity);

	}

	@Override
	public void update(Long id, ProdutoRequest produtoRequest) throws Exception {
		var entity = produtoRepository.findById(id);
		if (entity == null) {
			throw new BusinessException("Produto não encontrado");
		}
		Set<ConstraintViolation<ProdutoRequest>> violations = validator.validate(produtoRequest);
		if (!violations.isEmpty()) {
			throw ValidationException.generateMessage(violations);
		}

		entity.setDescricao(produtoRequest.getDescricao());
		entity.setSituacao(produtoRequest.getSituacao());
	}

	@Override
	public void delete(Long id) throws Exception {
		var entity = produtoRepository.findById(id);
		if (entity == null) {
			throw new BusinessException("Produto não encontrado");
		}

		var itemNotaFiscal = itemNotaFiscalRepository.find("produto", entity).list();
		if (!itemNotaFiscal.isEmpty()) {
			throw new BusinessException("Produto não pode ser excluído, pois está vinculado a uma nota fiscal");
		}
		produtoRepository.delete(entity);
	}

	@Override
	public void updateSituacao(Long id) throws Exception {
		var entity = produtoRepository.findById(id);
		if (entity == null) {
			throw new BusinessException("Produto não encontrado");
		}

		switch (entity.getSituacao()) {
			case ATIVO:
				entity.setSituacao(SituacaoProdutoEnum.INATIVO);
				break;
			case INATIVO:
				entity.setSituacao(SituacaoProdutoEnum.ATIVO);
				break;
			default:
				throw new BusinessException("Status atual do produto não é válido");
		}
		produtoRepository.persist(entity);
	}
}
