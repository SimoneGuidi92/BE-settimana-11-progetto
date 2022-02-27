package it.gestionesegreteria.util;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import it.gestionesegreteria.model.CorsoLaurea;
import it.gestionesegreteria.model.DummyDB;
import it.gestionesegreteria.model.Studente;

@Component
public class DummyDBLoader implements CommandLineRunner {

	@Autowired
	ApplicationContext ctx;

	@Override
	public void run(String... args) throws Exception {

		DummyDB dummyDb = ctx.getBean(DummyDB.class);
		valorizzaDb(dummyDb);

	}

	

	public void valorizzaDb(DummyDB db) {
		CorsoLaurea c1 = CorsoLaurea.builder().codice("a1").nome("Informatica").indirizzo("Via accademica 12").numeroEsami(10).build();
		CorsoLaurea c2 = CorsoLaurea.builder().codice("b2").nome("Giovani astronauti").indirizzo("Via dalla luna 19").numeroEsami(58).build();
		Studente s1 = Studente.builder().matricola("alunno1").nome("Jack").cognome("Sparrow").dataNascita(LocalDate.parse("1957-07-25")).email("pippo@gmail.com")
				.indirizzo("Via viosa 57").citta("Venezia").corsoLaurea(c2).build();
		Studente s2 = Studente.builder().matricola("alunno2").nome("Mario").cognome("Rossi").dataNascita(LocalDate.parse("1999-05-22"))
				.email("brodo@gmail.com").indirizzo("Via ciao 32").citta("Roma").corsoLaurea(c1).build();
		db.aggiungiStudente(s1);
		db.aggiungiStudente(s2);
		db.aggiungiCorso(c1);
		db.aggiungiCorso(c2);

	}



}
