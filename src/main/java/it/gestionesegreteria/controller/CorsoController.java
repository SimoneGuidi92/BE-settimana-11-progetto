package it.gestionesegreteria.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.gestionesegreteria.model.CorsoLaurea;
import it.gestionesegreteria.model.DummyDB;
import it.gestionesegreteria.model.Studente;

@Controller
@RequestMapping("/corsi")
public class CorsoController {

	@Autowired
	ApplicationContext ctx;
	
	private DummyDB getDummyDb() {
		return ctx.getBean(DummyDB.class);
	}
	
	@GetMapping("/visualizzacorsi")	// url localhost:8080/corsi/visualizzacorsi
	public ModelAndView visualizzaCorsi() {
		
		return new ModelAndView("visualizzaCorsi", "dummydb", getDummyDb());
			
	}
	
	@GetMapping("/mostraformaggiungicorso")
	public ModelAndView mostraFormAggiungiCorso() {
		List<CorsoLaurea> corsi = getDummyDb().getCorsi();
		ModelAndView mav = new ModelAndView("aggiungiCorso", "corso", new CorsoLaurea());
		mav.addObject("corsi", corsi);
		return mav;
		
	}
	
	@PostMapping("/aggiungicorso")
	public ModelAndView aggiungiCorso(CorsoLaurea corso, BindingResult result, Model model) {
		
		getDummyDb().aggiungiCorso(corso);
		
		return visualizzaCorsi();
	}
	
	
	
	@GetMapping("/formaggiornacorso/{codice}")
	public ModelAndView mostraFormAggiornaCorso(@PathVariable String codice, Model model) {
		
		CorsoLaurea c = getDummyDb().getCorsoByCodice(codice);
		List<CorsoLaurea> corsi = getDummyDb().getCorsi();
		ModelAndView mav = new ModelAndView("aggiornaCorso", "corso", new CorsoLaurea());
		mav.addObject("corso", c);
		mav.addObject("corsi", corsi);
		return mav;
	}
	
	@PostMapping("/aggiornacorso/{codice}")
	public ModelAndView aggiornaCorsi(@Valid @ModelAttribute("codice") CorsoLaurea corso, @PathVariable String codice, BindingResult result, Model model) {
		corso.setCodice(codice);
		getDummyDb().eliminaCorso(corso.getCodice());
		getDummyDb().aggiungiCorso(corso);
		return visualizzaCorsi();
		
	}
	
	@GetMapping("/eliminacorso/{codice}")
	public ModelAndView eliminaCorso(@PathVariable("codice") String codice, Model model) {
		getDummyDb().eliminaCorso(codice);
		return visualizzaCorsi();
	}
	
	
}
