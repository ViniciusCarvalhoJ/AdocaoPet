package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastrarPetDto;
import br.com.alura.adopet.api.dto.ResumoPetDto;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public ResumoPetDto cadastrar(@Valid CadastrarPetDto dto) {
        Pet pet = new Pet(null, dto.nome(), dto.tipo(), false);

        petRepository.save(pet);

        return new ResumoPetDto(
                pet.getId(),
                pet.getNome(),
                pet.getTipo(),
                pet.getAdotado()
        );
    }

    public List<ResumoPetDto> listarTodos() {
        var pets = petRepository.findAll();

        // Converte cada Pet em um ResumoPetDto
        return pets.stream()
                .map(pet -> new ResumoPetDto(
                        pet.getId(),
                        pet.getNome(),
                        pet.getTipo(),
                        pet.getAdotado()
                ))
                .toList();
    }
}
