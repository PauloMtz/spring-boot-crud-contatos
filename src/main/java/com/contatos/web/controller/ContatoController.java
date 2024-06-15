package com.contatos.web.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.contatos.core.exception.ValidacaoException;
import com.contatos.domain.model.Contato;
import com.contatos.domain.repository.ContatoRepository;
import com.contatos.domain.service.ContatoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/contato")
public class ContatoController {
    
    @Autowired
    private ContatoService contatoService;

    @Autowired
    private ContatoRepository contatoRepository;

    @GetMapping("/cadastrar")
    public ModelAndView carregarCadastro() {
        
        var mv = new ModelAndView("pages/form"); // template
        mv.addObject("contato", new Contato());
        
        return mv;
    }

    @PostMapping("/cadastrar")
	public String cadastrar(@Valid Contato contato, BindingResult result, 
		RedirectAttributes attr) {
        
		if (result.hasErrors()) {
			return "pages/form";
		}

		try {
			String cepFormatado = contato.getCpf().replaceAll("[\\.\\-]", "");
			contato.setCpf(cepFormatado);
			
			contatoService.salvar(contato);
			attr.addFlashAttribute("success", "Registro inserido com sucesso.");
			return "redirect:/contato/listar";
		} catch (ValidacaoException e) {
			result.addError(e.getFieldError());
			return "pages/form";
		}
	}

    @GetMapping("/listar")
    public String listar(Model model) {
        return listaPaginada(model, 1);
    }

    @GetMapping("/listar/{pageNumber}")
	public String listaPaginada(Model model, 
        @PathVariable(value = "pageNumber") int currentPage) {
		
        Page<Contato> page = contatoService.listarTodos(currentPage);
        List<Contato> contatos = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        String url = "/contato/listar/";
        String pag = "";

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("url", url);
        model.addAttribute("pag", pag);
        model.addAttribute("contatos", contatos);
        model.addAttribute("registros", contatoRepository.countByNome());
		
        return "pages/list";
	}

	@GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        
        var mv = new ModelAndView("/pages/form"); // template
        mv.addObject("contato", contatoService.buscarPorId(id));
        
        return mv;
    }

	/*
        o hibernate e a jpa diferem inserir de atualizar 
        no método save através do id que foi passado
        não pode ter input type hidden no form
    */
    @PostMapping("/{id}/editar")
    public String editar(@Valid @ModelAttribute("contato") Contato contato, 
        BindingResult result, RedirectAttributes attr) throws IOException {

        if (result.hasErrors()) {
			return "pages/form"; // template
		}

        try {
            contatoService.salvar(contato);
            attr.addFlashAttribute("success", "Registro atualizado com sucesso.");
            return "redirect:/contato/listar"; // rota
        } catch(ValidacaoException e) {
            result.addError(e.getFieldError());
            return "pages/form"; // template
        }
    }

	@GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes attr) {
        
        contatoService.excluir(id);
        attr.addFlashAttribute("success", "Registro excluído com sucesso.");
        
        return "redirect:/contato/listar"; // rota
    }

    /*@GetMapping("/buscar")
	public String getPorNome(@RequestParam("nome") String nome, ModelMap model) {		
		
        model.addAttribute("contatos", contatoService.buscarPorNome(nome));
		
        return "/pages/list"; // template
	}*/

    @GetMapping("/buscar")
    public String getPorNome(String nome, Model model) {
        return buscaPaginada(model, nome, 1);
    }

    @GetMapping("/buscar/{pageNumber}")
    public String buscaPaginada(Model model, @RequestParam("nome") String nome,
        @PathVariable(value = "pageNumber") int currentPage) {

        Page<Contato> page = contatoService.buscarPorNome(nome, currentPage);
        List<Contato> contatos = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        String url = "/contato/buscar/";
        String pag = "?nome=" + nome;

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("url", url);
        model.addAttribute("pag", pag);
        model.addAttribute("contatos", contatos);
        model.addAttribute("registros", totalItems);

        return "pages/list"; // template
    }
}
