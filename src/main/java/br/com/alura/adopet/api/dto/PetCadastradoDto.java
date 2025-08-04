package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.TipoPet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PetCadastradoDto(
                               @NotBlank String nome,
                               @NotBlank Long id,
                               @NotNull TipoPet tipo) {
}
