package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.infra.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidacaoTutorComLimiteDeAdocao implements ValidacaoSolicitacaoAdocao {

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    private TutorRepository tutorRepository;

    public void validar(SolicitacaoAdocaoDto dto){
        List<Adocao> adocoes = adocaoRepository.findAll();
        Tutor tutor = tutorRepository.getReferenceById(dto.IdTutor());

        int contadorAdocoesAprovadas = 0; // Variável contador inicializada ANTES do loop

        for (Adocao a : adocoes) {
            if (a.getTutor().equals(tutor) && a.getStatus() == StatusAdocao.APROVADO) {
                // Usamos .equals() para comparar objetos, é mais seguro que ==
                contadorAdocoesAprovadas = contadorAdocoesAprovadas + 1; // Incrementa o contador
            }
        }

        if (contadorAdocoesAprovadas >= 5) { // Verifica se o contador atingiu ou ultrapassou 5
            throw new ValidacaoException("Tutor chegou ao limite máximo de 5 adoções!");
        }
    }


}
