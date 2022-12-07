package com.jancyaragao.soc.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jancyaragao.soc.model.Marcacao;
import com.jancyaragao.soc.repository.ExameRepository;
import com.jancyaragao.soc.repository.FuncionarioRepository;
import com.jancyaragao.soc.repository.MarcacaoRepository;

import jakarta.validation.Valid;

@RequestMapping("/marcacoes")
@Controller
public class MarcacaoController {

    @Autowired
    private MarcacaoRepository marcacaoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    ExameRepository exameRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("marcacoes", marcacaoRepository.findAll());
        return "marcacoes/index";
    }

    @GetMapping("/adicionar")
    public String adicionar(Model model) {
        model.addAttribute("funcionarios", funcionarioRepository.findAll());
        model.addAttribute("exames", exameRepository.findAll());
        model.addAttribute("marcacao", new Marcacao());
        return "marcacoes/adicionar";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("marcacao") Marcacao marcacao, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("funcionarios", funcionarioRepository.findAll());
            model.addAttribute("exames", exameRepository.findAll());
            return "marcacoes/adicionar";
        } else if (marcacaoRepository.exists(marcacao.getFuncionario().getCodigo(), marcacao.getExame().getCodigo(),
                marcacao.getDataExame())) {
            model.addAttribute("funcionarios", funcionarioRepository.findAll());
            model.addAttribute("exames", exameRepository.findAll());
            model.addAttribute("messagemErro", "Não foi possível adicionar, marcação já existente");
            return "marcacoes/adicionar";
        }

        marcacaoRepository.save(marcacao);
        return "redirect:/marcacoes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("funcionarios", funcionarioRepository.findAll());
        model.addAttribute("exames", exameRepository.findAll());
        model.addAttribute("marcacao", marcacaoRepository.getReferenceById(id));
        return "marcacoes/editar";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id, @Valid @ModelAttribute("marcacao") Marcacao marcacao,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("funcionarios", funcionarioRepository.findAll());
            model.addAttribute("exames", exameRepository.findAll());
            return "marcacoes/editar";
        } else if (marcacaoRepository.exists(marcacao.getFuncionario().getCodigo(), marcacao.getExame().getCodigo(),
                marcacao.getDataExame())) {
            model.addAttribute("funcionarios", funcionarioRepository.findAll());
            model.addAttribute("exames", exameRepository.findAll());
            model.addAttribute("messagemErro", "Não foi possível atualizar, marcação já existente");
            return "marcacoes/editar";
        }

        marcacao.setId(id);
        marcacaoRepository.save(marcacao);
        return "redirect:/marcacoes";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, Model model) {
        marcacaoRepository.deleteById(id);
        return "redirect:/marcacoes";
    }

    @GetMapping("/relatorios")
    public String relatorioPorData(@RequestParam(value = "min") String min, @RequestParam(value = "max") String max,
            Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        model.addAttribute("marcacoes", marcacaoRepository.findMarcacoesByDate(LocalDate.parse(min, formatter),
                LocalDate.parse(max, formatter)));
        return "marcacoes/relatorios";
    }

}
