package it.gestionesegreteria.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Getter;


@Component
@Getter
public class DummyDB {

	private final List<Studente> studenti = new ArrayList<>();
	private final List<CorsoLaurea> corsi = new ArrayList<>();
	
	
	
	
	public void aggiungiCorso(CorsoLaurea corso) {
		corsi.add(corso);
	}
	
	public void aggiungiStudente(Studente studente) {
		studenti.add(studente);
	}
	
	public boolean eliminaStudente(String matricola) {
		for(int i = 0; i < studenti.size(); i++) {
			if(studenti.get(i).getMatricola().equals(matricola)) {
				studenti.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean eliminaCorso(String codice) {
		for(int i = 0; i < corsi.size(); i++) {
			if(corsi.get(i).getCodice().equals(codice)) {
				corsi.remove(i);
				return true;
			}
		}
		return false;
	}
	
	
	
	
	public Studente getStudenteByMatricola(String matricola) {
		for(Studente studente : studenti) {
			if(studente.getMatricola().equals(matricola)) {
				return studente;
			}
		}
		return null; 
		
	}
	
	public CorsoLaurea getCorsoByCodice(String codice) {
		for(CorsoLaurea corso : corsi) {
			if(corso.getCodice().equals(codice)) {
				return corso;
			}
		}
		return null;
	}
	
}
