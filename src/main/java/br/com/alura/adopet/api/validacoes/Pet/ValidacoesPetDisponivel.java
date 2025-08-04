package br.com.alura.adopet.api.validacoes.Pet;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.infra.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.validacoes.Adocao.ValidacaoSolicitacaoAdocao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacoesPetDisponivel implements ValidacaoSolicitacaoAdocao {

    @Autowired
    private PetRepository petRepository;

    public void validar(SolicitacaoAdocaoDto dto){
        boolean petJaFoiAdotado = petRepository.existsByIdAndAdotadoTrue(dto.idPet());

        if (petJaFoiAdotado) {
            throw new ValidacaoException("Pet já foi adotado!");

        }
    }
}
