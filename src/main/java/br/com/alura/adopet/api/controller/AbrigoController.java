package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.CadastrarAbrigoDto;
import br.com.alura.adopet.api.dto.CadastrarPetDto;
import br.com.alura.adopet.api.dto.ResumoAbrigoDto;
import br.com.alura.adopet.api.dto.ResumoPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.service.AbrigoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoRepository repository;

    @Autowired
    private AbrigoService abrigoService;

    @GetMapping
    public ResponseEntity<List<Abrigo>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResumoAbrigoDto> cadastrar(@RequestBody @Valid CadastrarAbrigoDto dto) {
        var abrigoCadastrado = abrigoService.cadastrarAbrigo(dto);
        return ResponseEntity.ok(abrigoCadastrado);

    }

    @GetMapping("/{id}/{nome}/pets")
    public ResponseEntity<List<ResumoPetDto>> listarPets(
            @PathVariable Long id,
            @PathVariable String nome) {

        List<ResumoPetDto> pets = abrigoService.listarPetsDoAbrigo(id, nome);
        return ResponseEntity.ok(pets);
    }

    @PostMapping("/{idOuNome}/pets")
    @Transactional
    public ResponseEntity<Void> cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid CadastrarPetDto dto) {

        abrigoService.cadastrarPetNoAbrigo(idOuNome, dto);
        return ResponseEntity.ok().build();
    }


}
