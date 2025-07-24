package br.com.alura.adopet.api.dto;

import javax.xml.crypto.Data;

public record tarefasDto(Long id,
                         boolean status,
                         Data dataCriacao) {
}
