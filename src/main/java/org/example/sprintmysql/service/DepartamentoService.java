package org.example.sprintmysql.service;

import org.example.sprintmysql.model.Departamento;
import org.example.sprintmysql.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository repositorio;

    public List<Departamento> listarTodos() {
        return repositorio.findAll();
    }

    public Optional<Departamento> buscarPorId(String id) {
        return repositorio.findById(id);
    }

    public Departamento guardar(Departamento departamento) {
        return repositorio.save(departamento);
    }

    public Optional<Departamento> actualizar(String id, Departamento datos) {
        Optional<Departamento> departamentoexistente = repositorio.findById(id);
        if (departamentoexistente.isEmpty()) return Optional.empty();

        Departamento dep = departamentoexistente.get();
        dep.setNombre(datos.getNombre());
        dep.setLocalidad(datos.getLocalidad());
        dep.setCodigo(datos.getCodigo());
        return Optional.of(repositorio.save(dep));
    }

    public boolean eliminar(String id) {
        if (repositorio.existsById(id)) return false;
        repositorio.deleteById(id);
        return true;
    }
}
