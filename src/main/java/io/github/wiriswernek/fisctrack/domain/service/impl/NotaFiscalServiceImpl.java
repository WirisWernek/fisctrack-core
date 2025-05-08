package io.github.wiriswernek.fisctrack.domain.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.github.wiriswernek.fisctrack.core.exceptions.BusinessException;
import io.github.wiriswernek.fisctrack.core.exceptions.ValidationException;
import io.github.wiriswernek.fisctrack.core.mapper.ItemNotaFiscalMapper;
import io.github.wiriswernek.fisctrack.core.mapper.NotaFiscalMapper;
import io.github.wiriswernek.fisctrack.domain.model.dto.filter.NotaFiscalFilter;
import io.github.wiriswernek.fisctrack.domain.model.dto.request.NotaFiscalRequest;
import io.github.wiriswernek.fisctrack.domain.model.dto.response.NotaFiscalResponse;
import io.github.wiriswernek.fisctrack.domain.model.entity.Fornecedor;
import io.github.wiriswernek.fisctrack.domain.model.entity.ItemNotaFiscal;
import io.github.wiriswernek.fisctrack.domain.model.entity.NotaFiscal;
import io.github.wiriswernek.fisctrack.domain.model.repository.FornecedorRepository;
import io.github.wiriswernek.fisctrack.domain.model.repository.NotaFiscalRepository;
import io.github.wiriswernek.fisctrack.domain.model.repository.ProdutoRepository;
import io.github.wiriswernek.fisctrack.domain.service.NotaFiscalService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class NotaFiscalServiceImpl implements NotaFiscalService {

	private final NotaFiscalRepository notaFiscalRepository;
	private final FornecedorRepository fornecedorRepository;
	private final ProdutoRepository produtoRepository;
	private final Validator validator;

	@Override
	public List<NotaFiscalResponse> search(NotaFiscalFilter filter) throws Exception {
		var query = notaFiscalRepository.search(filter);
		return query.list().stream().map(NotaFiscalMapper::toResponseByEntity).toList();
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
	public void create(NotaFiscalRequest notaFiscalRequest) throws Exception, BusinessException {
		validate(notaFiscalRequest);

		var entity = NotaFiscalMapper.toEntityByRequest(notaFiscalRequest);
		var fornecedor = getFornecedor(notaFiscalRequest.getFornecedorId());
		var itens = getItensNota(notaFiscalRequest, entity);

		entity.setFornecedor(fornecedor);
		entity.getItens().clear();
		itens.forEach(entity::addItem);
		notaFiscalRepository.persist(entity);
	}

	@Override
	public void update(Long id, NotaFiscalRequest notaFiscalRequest) throws Exception {
		var entity = getNotaFiscal(id);

		validate(notaFiscalRequest);
		var fornecedor = getFornecedor(notaFiscalRequest.getFornecedorId());
		var itens = getItensNota(notaFiscalRequest, entity);

		entity.setNumeroNota(notaFiscalRequest.getNumeroNota());
		entity.setTotal(notaFiscalRequest.getTotal());
		entity.setFornecedor(fornecedor);

		entity.getItens().clear();
		itens.forEach(entity::addItem);

		var enderecoRequest = notaFiscalRequest.getEndereco();
		var endereco = entity.getEndereco();

		endereco.setCep(enderecoRequest.getCep());
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

	private List<ItemNotaFiscal> getItensNota(NotaFiscalRequest notaFiscalRequest, NotaFiscal entity)
			throws BusinessException {
		var produtosNaoEncontrados = new ArrayList<String>();

		var itens = new ArrayList<ItemNotaFiscal>();

		notaFiscalRequest.getItens().forEach(item -> {
			var produto = produtoRepository.findById(item.getProdutoId());
			if (produto == null) {
				produtosNaoEncontrados.add("Produto não encontrado ".concat(item.getProdutoId().toString()));
				return;
			}

			var itemEntity = ItemNotaFiscalMapper.toEntityByRequest(item);
			itemEntity.setNotaFiscal(entity);
			itemEntity.setProduto(produto);
			itens.add(itemEntity);
		});

		if (!produtosNaoEncontrados.isEmpty()) {
			throw new BusinessException(produtosNaoEncontrados.toString());
		}

		return itens;
	}
}
