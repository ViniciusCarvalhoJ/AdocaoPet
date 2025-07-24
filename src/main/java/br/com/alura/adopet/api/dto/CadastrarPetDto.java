package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.TipoPet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


//Input do dados
public record CadastrarPetDto(Long  id,
                              @NotBlank  String nome,
                              String peso,
                              @NotNull TipoPet tipo,
                              String raca,
                              Integer idade) {
}
