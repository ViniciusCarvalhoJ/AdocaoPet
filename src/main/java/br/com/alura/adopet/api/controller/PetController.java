package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.CadastrarPetDto;
import br.com.alura.adopet.api.dto.ResumoPetDto;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetRepository repository;
    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<List<ResumoPetDto>> listarTodos(){
        var lista = petService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<ResumoPetDto> registrarPet (@RequestBody @Valid CadastrarPetDto dto){
        var resumo = petService.cadastrar(dto);
        return ResponseEntity.ok(resumo);

    }
    }
