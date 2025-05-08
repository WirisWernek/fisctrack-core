package io.github.wiriswernek.fisctrack.core.mapper;

import io.github.wiriswernek.fisctrack.domain.model.dto.request.ItemNotaFiscalRequest;
import io.github.wiriswernek.fisctrack.domain.model.dto.response.ItemNotaFiscalResponse;
import io.github.wiriswernek.fisctrack.domain.model.entity.ItemNotaFiscal;

public class ItemNotaFiscalMapper {
	public static ItemNotaFiscal toEntityByRequest(ItemNotaFiscalRequest requestDTO) {
		return ItemNotaFiscal.builder()
				.quantidade(requestDTO.getQuantidade())
				.valorUnitario(requestDTO.getValorUnitario())
				.build();
	}

	public static ItemNotaFiscalResponse toResponseByEntity(ItemNotaFiscal entity) {
		var produto = ProdutoMapper.toResponseByEntity(entity.getProduto());

		return ItemNotaFiscalResponse.builder()
				.id(entity.getId())
				.quantidade(entity.getQuantidade())
				.valorUnitario(entity.getValorUnitario())
				.produto(produto)
				.build();
	}
}
