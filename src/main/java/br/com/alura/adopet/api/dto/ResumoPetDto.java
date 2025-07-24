package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.TipoPet;


//Output dos dados
public record ResumoPetDto(Long id,
                           String nome,
                           TipoPet tipo,
                           Boolean adotado) {
}
