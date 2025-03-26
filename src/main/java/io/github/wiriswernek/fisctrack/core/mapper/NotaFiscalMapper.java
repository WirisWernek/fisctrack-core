package io.github.wiriswernek.fisctrack.core.mapper;

import io.github.wiriswernek.fisctrack.domain.model.dto.request.NotaFiscalRequest;
import io.github.wiriswernek.fisctrack.domain.model.dto.response.NotaFiscalResponse;
import io.github.wiriswernek.fisctrack.domain.model.entity.NotaFiscal;

public class NotaFiscalMapper {
    public static NotaFiscal toEntityByRequest(NotaFiscalRequest requestDTO) {
        var endereco = EnderecoMapper.toEntityByRequest(requestDTO.getEndereco());

        return NotaFiscal.builder()
                .numeroNota(requestDTO.getNumeroNota())
                .total(requestDTO.getTotal())
                .endereco(endereco)
                .build();

    }


    public static NotaFiscalResponse toResponseByEntity(NotaFiscal entity) {
        var fornecedor = FornecedorMapper.toResponseByEntity(entity.getFornecedor());
        var endereco = EnderecoMapper.toResponseByEntity(entity.getEndereco());

        return NotaFiscalResponse.builder()
                .id(entity.getId())
                .numeroNota(entity.getNumeroNota())
                .total(entity.getTotal())
                .emissao(entity.getEmissao())
                .fornecedor(fornecedor)
                .endereco(endereco)
                .build();
    }
}
