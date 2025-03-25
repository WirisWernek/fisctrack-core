package io.github.wiriswernek.fisctrack.domain.model.enums;

import lombok.Getter;

@Getter
public enum SituacaoFornecedorEnum {
    ATIVO("Ativo"),
    BAIXADO("Baixado"),
    SUSPENSO("Suspenso");

    private String descricao;

    SituacaoFornecedorEnum(String descricao) {
        this.descricao = descricao;
    }

    public static SituacaoFornecedorEnum parse(String descricao) {
        for (SituacaoFornecedorEnum sitacao : SituacaoFornecedorEnum.values()) {
            if (sitacao.name().equals(descricao)) {
                return sitacao;
            }
        }
        return null;
    }
}
