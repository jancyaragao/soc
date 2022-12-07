package com.jancyaragao.soc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jancyaragao.soc.model.Funcionario;
import com.jancyaragao.soc.repository.FuncionarioRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("funcionarios", funcionarioRepository.findAll());
        return "funcionarios/index";
    }

    @GetMapping("/adicionar")
    public String adicionar(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        return "funcionarios/adicionar";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("funcionario") Funcionario funcionario, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "funcionarios/adicionar";
        }

        funcionarioRepository.save(funcionario);
        return "redirect:/funcionarios";
    }

    @GetMapping("/editar/{codigo}")
    public String editar(@PathVariable Long codigo, Model model) {
        model.addAttribute("funcionario", funcionarioRepository.getReferenceById(codigo));
        return "funcionarios/editar";
    }

    @PostMapping("/atualizar/{codigo}")
    public String atualizar(@PathVariable Long codigo, @Valid @ModelAttribute("funcionario") Funcionario funcionario,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "funcionarios/editar";
        }

        funcionario.setCodigo(codigo);
        funcionarioRepository.save(funcionario);
        return "redirect:/funcionarios";
    }

    @GetMapping("/excluir/{codigo}")
    public String excluir(@PathVariable Long codigo, Model model) {
        funcionarioRepository.deleteById(codigo);
        return "redirect:/funcionarios";
    }

}
