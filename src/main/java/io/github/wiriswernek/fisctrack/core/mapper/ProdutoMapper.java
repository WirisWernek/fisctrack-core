package io.github.wiriswernek.fisctrack.core.mapper;

import io.github.wiriswernek.fisctrack.domain.model.dto.request.ProdutoRequest;
import io.github.wiriswernek.fisctrack.domain.model.dto.response.ProdutoResponse;
import io.github.wiriswernek.fisctrack.domain.model.entity.Produto;

public class ProdutoMapper {
    public static Produto toEntityByRequest(ProdutoRequest requestDTO) {
        return Produto.builder().descricao(requestDTO.getDescricao()).situacao(requestDTO.getSituacao()).build();
    }

    public static ProdutoResponse toResponseByEntity(Produto entity) {
        return new ProdutoResponse(entity.getId(), entity.getDescricao(), entity.getSituacao());
    }
}
