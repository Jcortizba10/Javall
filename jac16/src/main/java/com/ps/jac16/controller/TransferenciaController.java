package com.ps.jac16.controller;
import com.ps.jac16.model.Transferencia;
import com.ps.jac16.repository.TransferenciaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {
    @Autowired
    private TransferenciaRepositorio transferenciaRepositorio;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Transferencia transferencia) {
        // Realiza la lógica necesaria para guardar la transferencia en la base de datos
        Transferencia nuevaTransferencia = transferenciaRepositorio.save(transferencia);
        return ResponseEntity.ok(nuevaTransferencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Transferencia transferencia) {
        // Verifica si la transferencia con el ID proporcionado existe
        Optional<Transferencia> transferenciaExistente = transferenciaRepositorio.findById(id);

        if (transferenciaExistente.isPresent()) {
            // Realiza la lógica necesaria para actualizar la transferencia en la base de datos
            Transferencia transferenciaActualizada = transferenciaRepositorio.save(transferencia);
            return ResponseEntity.ok(transferenciaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        // Busca la transferencia por ID en la base de datos
        Optional<Transferencia> transferencia = transferenciaRepositorio.findById(id);

        if (transferencia.isPresent()) {
            return ResponseEntity.ok(transferencia.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        // Obtiene todas las transferencias en la base de datos
        List<Transferencia> transferencias = transferenciaRepositorio.findAll();
        return ResponseEntity.ok(transferencias);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        // Verifica si la transferencia con el ID proporcionado existe
        Optional<Transferencia> transferenciaExistente = transferenciaRepositorio.findById(id);

        if (transferenciaExistente.isPresent()) {
            // Realiza la lógica necesaria para eliminar la transferencia de la base de datos
            transferenciaRepositorio.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
