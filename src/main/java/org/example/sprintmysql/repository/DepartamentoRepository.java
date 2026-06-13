package org.example.sprintmysql.repository;

import org.example.sprintmysql.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartamentoRepository extends JpaRepository<Departamento, String> {
    List<Departamento> findByNombreContaining(String texto);
}
