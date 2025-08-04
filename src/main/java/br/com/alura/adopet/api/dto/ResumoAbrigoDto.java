package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ResumoAbrigoDto(    @NotBlank String nome,
                                  @NotBlank String telefone,
                                  @Email String email) {

}
