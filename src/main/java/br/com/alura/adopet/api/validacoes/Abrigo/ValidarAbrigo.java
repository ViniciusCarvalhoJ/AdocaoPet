package br.com.alura.adopet.api.validacoes.Abrigo;

import br.com.alura.adopet.api.dto.CadastrarAbrigoDto;
import br.com.alura.adopet.api.infra.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ValidarAbrigo {

    @Autowired
    private AbrigoRepository abrigoRepository;

    public void validar(CadastrarAbrigoDto dto) {
        boolean verificarDadosTutor = abrigoRepository.existsByNomeAndTelefoneAndEmail(dto.nome(), dto.telefone(), dto.email());
        if (verificarDadosTutor) {
            throw new ValidacaoException("Abrigo já tem cadastro!");
        }
    }

    public void validarListaDePets(Long id, String nome){

        Optional<Abrigo> abrigo = abrigoRepository.findByIdAndNome(id, nome);

        if (abrigo.isEmpty()) {
            throw new ValidacaoException("Abrigo não encontrado!");
        }
    }
}
