package com.loja.brinquedos.controller;

import com.loja.brinquedos.model.Brinquedo;
import com.loja.brinquedos.repository.BrinquedoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/brinquedos")
public class BrinquedoController {

    @Autowired
    private BrinquedoRepository brinquedoRepository;
    @GetMapping("/listar")
    public String listarTodos(Model model) {
        model.addAttribute("brinquedos", brinquedoRepository.findAll());
        return "listar";
    }
    @GetMapping("/novo")
    public String exibirFormularioNovo(Model model) {
        model.addAttribute("brinquedo", new Brinquedo());
        return "formulario";
    }
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Brinquedo brinquedo) {
        brinquedoRepository.save(brinquedo);
        return "redirect:/brinquedos/listar";
    }
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Brinquedo brinquedo = brinquedoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Brinquedo não encontrado - ID: " + id));
        model.addAttribute("brinquedo", brinquedo);
        return "formulario";
    }
    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        Brinquedo brinquedo = brinquedoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Brinquedo não encontrado - ID: " + id));
        brinquedoRepository.delete(brinquedo);
        return "redirect:/brinquedos/listar";
    }
    @GetMapping("/descricao/{id}")
    public String verDescricao(@PathVariable Long id, Model model) {
        Brinquedo brinquedo = brinquedoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Brinquedo não encontrado - ID: " + id));
        model.addAttribute("brinquedo", brinquedo);
        return "descricao";
    }
}
