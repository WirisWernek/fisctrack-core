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

    public static SituacaoProdutoEnum parse(String descricao) {
        for (SituacaoProdutoEnum situacao : SituacaoProdutoEnum.values()) {
            if (situacao.name().equals(descricao)) {
                return situacao;
            }
        }
        return null;
    }

}
