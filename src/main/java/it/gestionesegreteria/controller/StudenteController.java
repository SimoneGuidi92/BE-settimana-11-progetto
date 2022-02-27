package it.gestionesegreteria.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
@RequestMapping("/studenti")
public class StudenteController {

	@Autowired
	ApplicationContext ctx;
	
	private DummyDB getDummyDb() {
		return ctx.getBean(DummyDB.class);
	}
	
	@GetMapping("/visualizzastudenti")	// url localhost:8080/studenti/visualizzastudenti
	public ModelAndView visualizzaStudenti() {
		
		return new ModelAndView("visualizzaStudenti", "dummydb", getDummyDb());
			
	}
	
	@GetMapping("/mostraform")
	public ModelAndView mostraForm() {
		List<CorsoLaurea> corsi = getDummyDb().getCorsi();
		ModelAndView mav = new ModelAndView("aggiungiStudente", "studente", new Studente());
		mav.addObject("corsi", corsi);
		return mav;
		
	}
	
	@PostMapping("/aggiungistudente")
	public ModelAndView aggiungiStudente(Studente studente, BindingResult result, Model model) {
		
		getDummyDb().aggiungiStudente(studente);
		
		return visualizzaStudenti();
	}
	
	
	
	@GetMapping("/formaggiornastudente/{matricola}")
	public ModelAndView mostraFormAggiornaStudente(@PathVariable String matricola, Model model) {
		
		Studente s = getDummyDb().getStudenteByMatricola(matricola);
		List<CorsoLaurea> corsi = getDummyDb().getCorsi();
		ModelAndView mav = new ModelAndView("aggiornaStudente", "studente", new Studente());
		mav.addObject("studente", s);
		mav.addObject("corsi", corsi);
		return mav;
	}
	
	@PostMapping("/aggiornastudente/{matricola}")
	public ModelAndView aggiornaStudente(@Valid @ModelAttribute("studente") Studente studente, @PathVariable String matricola, BindingResult result, Model model) {
		studente.setMatricola(matricola);
		getDummyDb().eliminaStudente(studente.getMatricola());
		getDummyDb().aggiungiStudente(studente);
		return visualizzaStudenti();
		
	}
	
	@GetMapping("/eliminastudente/{matricola}")
	public ModelAndView eliminaStudente(@PathVariable("matricola") String matricola, Model model) {
		getDummyDb().eliminaStudente(matricola);
		return visualizzaStudenti();
	}
	
	
	
	
	
}
