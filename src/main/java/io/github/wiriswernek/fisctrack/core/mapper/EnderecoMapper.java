package io.github.wiriswernek.fisctrack.core.mapper;

import io.github.wiriswernek.fisctrack.domain.model.dto.request.EnderecoRequest;
import io.github.wiriswernek.fisctrack.domain.model.dto.response.EnderecoResponse;
import io.github.wiriswernek.fisctrack.domain.model.entity.Endereco;

public class EnderecoMapper {
    public static Endereco toEntityByRequest(EnderecoRequest requestDTO) {
        return Endereco.builder()
                .rua(requestDTO.getRua())
                .numero(requestDTO.getNumero())
                .bairro(requestDTO.getBairro())
                .cidade(requestDTO.getCidade())
                .estado(requestDTO.getEstado())
                .pais(requestDTO.getPais())
                .build();
    }

    public static EnderecoResponse toResponseByEntity(Endereco entity) {
        return EnderecoResponse.builder()
                .id(entity.getId())
                .rua(entity.getRua())
                .numero(entity.getNumero())
                .bairro(entity.getBairro())
                .cidade(entity.getCidade())
                .estado(entity.getEstado())
                .pais(entity.getPais())
                .build();

    }

}
