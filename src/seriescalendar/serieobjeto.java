package seriescalendar;

import java.util.Calendar;

//Armazena o objeto serie com os dados pertinentes a ele
//pode ser melhorado caso haja a vontade de conhecer mais dados da serie
public class serieobjeto {
	String nome = "", episodio = "";
	Calendar data;

	//Construtor, recebe o trecho do codigo fonte pertinente a uma unica serie e tira
	//dele os dados buscados
	public serieobjeto(String serie, Calendar data) {
		char a;
		int i;
		this.data = data;
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

	//Retorna a data de exibicao da serie no formato dd/mm/aaaa
	public String diamesano() {
		String s = data.get(Calendar.DAY_OF_MONTH) + "/" + (data.get(Calendar.MONTH) + 1) + "/"
				+ data.get(Calendar.YEAR);
		return s;
	}

}
