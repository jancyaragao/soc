package com.jancyaragao.soc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jancyaragao.soc.model.Exame;
import com.jancyaragao.soc.repository.ExameRepository;

@RequestMapping("/exames")
@RestController
public class ExameController {
    
    @Autowired
    private ExameRepository exameRepository;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Exame> listar() {
        return exameRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Exame adicionar(@RequestBody Exame exame) {
        return exameRepository.save(exame);
    }

    @PutMapping("/{codigo}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Exame> atualizar(@PathVariable Long codigo, @RequestBody Exame exame) {
        if (!exameRepository.existsById(codigo)) {
            return ResponseEntity.notFound().build();
        }
        exame.setCodigo(codigo);

        return ResponseEntity.ok(exameRepository.save(exame));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> remover(@PathVariable Long codigo) {
        if (!exameRepository.existsById(codigo)) {
            return ResponseEntity.notFound().build();
        }
        exameRepository.deleteById(codigo);

        return ResponseEntity.noContent().build();
    }

}
