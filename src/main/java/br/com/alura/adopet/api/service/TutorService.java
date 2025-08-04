package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastrarTutorDto;
import br.com.alura.adopet.api.dto.ResumoTutorDto;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.Tutor.ValidadarDadosCadastradoTutor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private List<ValidadarDadosCadastradoTutor> validacoes;

    public ResumoTutorDto cadastrarTutor(@Valid CadastrarTutorDto dto) {
        validacoes.forEach(v -> v.validar(dto));
        Tutor tutor = new Tutor(dto.nome(), dto.telefone(), dto.email());
        tutorRepository.save(tutor);
        return new ResumoTutorDto(dto.nome(), dto.email(), dto.telefone());
    }
}

