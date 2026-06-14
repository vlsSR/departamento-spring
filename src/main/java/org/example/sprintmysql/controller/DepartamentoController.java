package org.example.sprintmysql.controller;

import org.example.sprintmysql.model.Departamento;
import org.example.sprintmysql.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {
    @Autowired
    private DepartamentoRepository repositorio;

    @GetMapping
    public ResponseEntity<List<Departamento>> listar() {
        return ResponseEntity.ok(repositorio.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> obtener(@PathVariable String id) {
        Optional<Departamento> resul = repositorio.findById(id);
        return resul.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Departamento> crear(@RequestBody Departamento departamento) {
        Departamento guardado = repositorio.save(departamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> actualizar(@PathVariable String id, @RequestBody Departamento datos) {
        Optional<Departamento> departamentoexiste = repositorio.findById(id);
        if (departamentoexiste.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Departamento d = departamentoexiste.get();
        d.setNombre(datos.getNombre());
        d.setLocalidad(datos.getLocalidad());
        return ResponseEntity.ok(repositorio.save(d));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        if (!repositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public List<Departamento> buscarByText(@RequestParam String texto) {
        return repositorio.findByNombreContaining(texto);
    }
}
