package br.com.alura.adopet.api.validacoes.Tutor;

import br.com.alura.adopet.api.dto.CadastrarTutorDto;
import br.com.alura.adopet.api.infra.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadarDadosCadastradoTutor {

    @Autowired
    private TutorRepository tutorRepository;

    public void validar(CadastrarTutorDto dto){
        boolean existeTelefone = tutorRepository.existsByTelefone(dto.telefone());
        boolean existeEmail = tutorRepository.existsByEmail(dto.email());

        if(existeTelefone || existeEmail){
            throw new ValidacaoException("Tutor ja tem cadastrado!");
        }

    }
}
