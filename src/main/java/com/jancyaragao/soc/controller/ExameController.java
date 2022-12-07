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

import com.jancyaragao.soc.model.Exame;
import com.jancyaragao.soc.repository.ExameRepository;
import com.jancyaragao.soc.repository.MarcacaoRepository;

import jakarta.validation.Valid;

@RequestMapping("/exames")
@Controller
public class ExameController {

    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private MarcacaoRepository marcacaoRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("exames", exameRepository.findAll());
        return "exames/index";
    }

    @GetMapping("/adicionar")
    public String adicionar(Model model) {
        model.addAttribute("exame", new Exame());
        return "exames/adicionar";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("exame") Exame exame, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "exames/adicionar";
        }

        exameRepository.save(exame);
        return "redirect:/exames";
    }

    @GetMapping("/editar/{codigo}")
    public String editar(@PathVariable Long codigo, Model model) {
        model.addAttribute("exame", exameRepository.getReferenceById(codigo));
        return "exames/editar";
    }

    @PostMapping("/atualizar/{codigo}")
    public String atualizar(@PathVariable Long codigo, @Valid @ModelAttribute("exame") Exame exame,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "exames/editar";
        }

        exame.setCodigo(codigo);
        exameRepository.save(exame);
        return "redirect:/exames";
    }

    @GetMapping("/excluir/{codigo}")
    public String excluir(@PathVariable Long codigo, Model model) {
        if(marcacaoRepository.existsByExame(codigo)) {
            model.addAttribute("exames", exameRepository.findAll());
            model.addAttribute("messagemErro", "Não foi possível remover, exame já marcado");
            return "exames/index";
        }

        exameRepository.deleteById(codigo);
        return "redirect:/exames";
    }

}
