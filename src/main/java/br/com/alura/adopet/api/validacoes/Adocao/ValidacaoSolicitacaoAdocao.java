package br.com.alura.adopet.api.validacoes.Adocao;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;

public interface ValidacaoSolicitacaoAdocao {

    void validar(SolicitacaoAdocaoDto dto);
}
