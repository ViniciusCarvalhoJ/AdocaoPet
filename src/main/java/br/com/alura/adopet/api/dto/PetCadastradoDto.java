package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.TipoPet;

public record PetCadastradoDto(Long id,
                               String nome,
                               TipoPet tipo,
                               Boolean Adotado) {
}
