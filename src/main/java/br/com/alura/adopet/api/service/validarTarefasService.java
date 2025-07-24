package br.com.alura.adopet.api.service;


import br.com.alura.adopet.api.dto.tarefasDto;
import br.com.alura.adopet.api.model.Tarefa;
import br.com.alura.adopet.api.repository.toDoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class validarTarefasService {

    @Autowired
    private toDoRepository repository;

    public tarefasDto cadastrar(@Valid tarefasDto dto) {
        Tarefa tarefa = new Tarefa(null, dto.dataCriacao(), dto.status());

        repository.save(tarefa);

        return new tarefasDto(
                tarefa.getId(),
                tarefa.isStatus(),
                tarefa.getDataCriacao()
        );
    }


    public List<tarefasDto> listarTarefas() {
        List <Tarefa> tarefas = repository.findAll();

        return tarefas.stream().map(tarefa -> new tarefasDto(
                        tarefa.getId(),
                        tarefa.isStatus(),
                        tarefa.getDataCriacao()
                ))
                .toList();
    }

}
