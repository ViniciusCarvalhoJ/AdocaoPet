package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Abrigo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {

    Abrigo findByNome(String nome);

    boolean existsByNomeAndTelefoneAndEmail(String nome, String telefone, String email);

    boolean existsByIdAndNome(Long id, String nome);

    Optional<Abrigo> findByIdAndNome(Long id, String nome);
}
