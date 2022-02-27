package it.gestionesegreteria.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CorsoLaurea {

	@NotNull
	private String codice;
	@NotNull
	private String nome;
	private String indirizzo;
	@Min(10)
	private Integer numeroEsami;
	
	
	@Override
	public String toString() {
		return "CorsoLaurea [codice=" + codice + ", nome=" + nome + ", indirizzo=" + indirizzo + ", numeroEsami="
				+ numeroEsami + "]";
	}
	
	
}
