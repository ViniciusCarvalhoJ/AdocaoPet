package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface toDoRepository extends JpaRepository<Tarefa, Long> {
}
