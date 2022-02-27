package it.gestionesegreteria.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.gestionesegreteria.model.CorsoLaurea;
import it.gestionesegreteria.model.DummyDB;


@Component
public class CorsoLaureaConverter implements Converter<String, CorsoLaurea>{
	
	@Autowired
	ApplicationContext ctx;

	@Override
	public CorsoLaurea convert(String codice) {
		
		return ctx.getBean(DummyDB.class).getCorsoByCodice(codice);
	}

}
