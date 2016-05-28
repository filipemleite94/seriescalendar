package seriescalendar;

import java.util.Calendar;

public class serieobjeto {
	String nome = "", episodio = "";
	Calendar data;

	public serieobjeto(String serie, Calendar data) {
		char a;
		int i;
		this.data=data;
		String[] aux = serie.split("-summary");
		a = (aux[1]).charAt(0);
		for (i = 0; a != '>'; i++) {
			a = aux[1].charAt(i);
		}
		a = aux[1].charAt(i);
		for (; a != '<';) {
			i++;
			nome += a;
			a = aux[1].charAt(i);
		}
		aux = serie.split("rem;");
		for (i = 0; a != '>'; i++) {
			a = aux[1].charAt(i);
		}
		a = aux[1].charAt(i);
		for (; a != '<';) {
			i++;
			episodio += a;
			a = aux[1].charAt(i);
		}
	}
	
	public String diamesano(){
		String s=data.get(Calendar.DAY_OF_MONTH)+"/"+(data.get(Calendar.MONTH)+1)+"/"+data.get(Calendar.YEAR);
		return s;
	}
	
}
