package br.com.alura.adopet.api.controller;


import br.com.alura.adopet.api.dto.tarefasDto;
import br.com.alura.adopet.api.model.usuario;
import br.com.alura.adopet.api.repository.toDoRepository;
import br.com.alura.adopet.api.service.validarTarefasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class toDoController {

    @Autowired
    private toDoRepository toDoRepository;

    @Autowired
    private validarTarefasService validarTarefasService;

    @GetMapping
    private ResponseEntity<List<tarefasDto>> tarefas(){
        var tarefas = validarTarefasService.listarTarefas();
        return ResponseEntity.ok(tarefas);
    }


}
