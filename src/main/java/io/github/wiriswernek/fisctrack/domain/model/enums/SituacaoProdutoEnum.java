package io.github.wiriswernek.fisctrack.domain.model.enums;

import lombok.Getter;

@Getter
public enum SituacaoProdutoEnum {
    ATIVO("Ativo"),
    INATIVO("Inativo");

    private String descricao;

    SituacaoProdutoEnum(String descricao) {
        this.descricao = descricao;
    }

    private static SituacaoProdutoEnum parse(String descricao) {
        for (SituacaoProdutoEnum situacao : SituacaoProdutoEnum.values()) {
            if (situacao.descricao.equals(descricao)) {
                return situacao;
            }
        }
        return null;
    }

}
