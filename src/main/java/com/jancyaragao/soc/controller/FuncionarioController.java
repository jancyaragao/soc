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

import com.jancyaragao.soc.model.Funcionario;
import com.jancyaragao.soc.repository.FuncionarioRepository;

@RequestMapping("/funcionarios")
@RestController
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Funcionario> listar() {
        return funcionarioRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario adicionar(@RequestBody Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    @PutMapping("/{codigo}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Funcionario> atualizar(@PathVariable Long codigo, @RequestBody Funcionario funcionario) {
        if (!funcionarioRepository.existsById(codigo)) {
            return ResponseEntity.notFound().build();
        }
        funcionario.setCodigo(codigo);

        return ResponseEntity.ok(funcionarioRepository.save(funcionario));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> remover(@PathVariable Long codigo) {
        if (!funcionarioRepository.existsById(codigo)) {
            return ResponseEntity.notFound().build();
        }
        funcionarioRepository.deleteById(codigo);

        return ResponseEntity.noContent().build();
    }

}
