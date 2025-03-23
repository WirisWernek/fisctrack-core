package io.github.wiriswernek.fisctrack.core.mapper;

import io.github.wiriswernek.fisctrack.domain.model.dto.request.FornecedorRequest;
import io.github.wiriswernek.fisctrack.domain.model.dto.response.FornecedorResponse;
import io.github.wiriswernek.fisctrack.domain.model.entity.Fornecedor;

public class FornecedorMapper {

    public static Fornecedor toEntityByRequest(FornecedorRequest requestDTO) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setRazaoSocial(requestDTO.getRazaoSocial());
        fornecedor.setCnpj(requestDTO.getCnpj());
        fornecedor.setEmail(requestDTO.getEmail());
        fornecedor.setTelefone(requestDTO.getTelefone());
        fornecedor.setSituacao(requestDTO.getSituacao());
        return fornecedor;
    }

    public static FornecedorResponse toResponseByEntity(Fornecedor entity) {
        FornecedorResponse response = new FornecedorResponse();
        response.setId(entity.getId());
        response.setRazaoSocial(entity.getRazaoSocial());
        response.setCnpj(entity.getCnpj());
        response.setEmail(entity.getEmail());
        response.setTelefone(entity.getTelefone());
        response.setSituacao(entity.getSituacao());
        response.setDataBaixa(entity.getDataBaixa());
        return response;
    }
}
