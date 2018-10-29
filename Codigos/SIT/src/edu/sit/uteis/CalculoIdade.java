package edu.sit.uteis;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

public class CalculoIdade {
	public static int calculaIdade(LocalDate dataNasc) {

	    Calendar dataNascimento = Calendar.getInstance();  
	    dataNascimento.setTime(Date.valueOf(dataNasc)); 
	    Calendar hoje = Calendar.getInstance();  

	    int idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR); 

	    if (hoje.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) {
	      idade--;  
	    } 
	    else 
	    { 
	        if (hoje.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH) && hoje.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
	            idade--; 
	        }
	    }

	    return idade;
	}
}
