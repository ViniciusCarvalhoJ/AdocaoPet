package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastrarAbrigoDto;
import br.com.alura.adopet.api.dto.CadastrarPetDto;
import br.com.alura.adopet.api.dto.ResumoAbrigoDto;
import br.com.alura.adopet.api.dto.ResumoPetDto;
import br.com.alura.adopet.api.infra.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.validacoes.Abrigo.ValidarAbrigo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private List<ValidarAbrigo> validacoes;

    @Autowired
    private List<Pet> petList;

    public ResumoAbrigoDto cadastrarAbrigo(@Valid CadastrarAbrigoDto dto) {
        validacoes.forEach(v -> v.validar(dto));
        Abrigo abrigo = new Abrigo(dto.nome(), dto.email(), dto.telefone());
        abrigoRepository.save(abrigo);
        return new ResumoAbrigoDto(dto.nome(), dto.telefone(), dto.email());

    }

    public List<ResumoPetDto> listarPetsDoAbrigo(Long id, String nome) {
        boolean existe = abrigoRepository.existsByIdAndNome(id, nome);
        if (!existe) {
            throw new ValidacaoException("Abrigo não encontrado com esse ID e nome");
        }
        Abrigo abrigo = abrigoRepository.findByIdAndNome(id, nome)
                .orElseThrow(() -> new ValidacaoException("Abrigo não encontrado"));

        List<Pet> pets = abrigo.getPets();

        List<ResumoPetDto> petsDto = pets.stream()
                .map(pet -> new ResumoPetDto(pet.getId(), pet.getNome(), pet.getTipo(), pet.getAdotado()))
                .toList();

        return petsDto;
    }


    public void cadastrarPetNoAbrigo(String idOuNome, CadastrarPetDto dto) {
        Abrigo abrigo;

        try {
            // Tenta buscar pelo ID
            Long id = Long.parseLong(idOuNome);
            abrigo = abrigoRepository.getReferenceById(id);
        } catch (NumberFormatException e) {
            // Se não for ID, busca pelo nome
            abrigo = abrigoRepository.findByNome(idOuNome);
        }

        if (abrigo == null) {
            throw new EntityNotFoundException("Abrigo não encontrado");
        }

        // Cria e associa o Pet
        Pet pet = new Pet(null, dto.nome(), dto.tipo(), dto.idade(), false, abrigo);

        // Salva o pet no repositório
        petRepository.save(pet);
    }


}
