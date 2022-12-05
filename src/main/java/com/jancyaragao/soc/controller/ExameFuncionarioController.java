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

import com.jancyaragao.soc.model.ExameFuncionario;
import com.jancyaragao.soc.repository.ExameFuncionarioRepository;

@RequestMapping("/exame_funcionario")
@RestController
public class ExameFuncionarioController {
    
    @Autowired
    private ExameFuncionarioRepository exameFuncionarioRepository; 


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ExameFuncionario> listar() {
        return exameFuncionarioRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExameFuncionario adicionar(@RequestBody ExameFuncionario exameFuncionario) {
        return exameFuncionarioRepository.save(exameFuncionario);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ExameFuncionario> atualizar(@PathVariable Long id, @RequestBody ExameFuncionario exameFuncionario) {
        if(!exameFuncionarioRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        exameFuncionario.setId(id);

        return ResponseEntity.ok(exameFuncionarioRepository.save(exameFuncionario));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if(!exameFuncionarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        exameFuncionarioRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
