package seriescalendar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

//Classque funciona como um banco de dados para as series com exibicao proxima.
//Cria as variaveis da classe serie objeto e funciona como ponte para acessa-las
public class seriescalendar {
	static int year;
	static int day;
	static int month;
	static ArrayList<ArrayList<serieobjeto>> lista;
	private static Calendar dataagora;

	//Metodo para encapsulamento
	public Calendar data() {
		return dataagora;
	}

	//Retorna os nomes de todas as series que vao ser transmitidas no periodo
	//de uma semana uma unica vez cada nome.
	public String[] nomesdalista() {
		int i, j, k, length, length2, length3;
		String nome;
		boolean b;
		ArrayList<String> s = new ArrayList<String>();
		length = lista.size();
		for (i = 0; i < length; i++) {
			length2 = lista.get(i).size();
			for (j = 0; j < length2; j++) {
				length3 = s.size();
				b = false;
				nome = lista.get(i).get(j).nome;
				for (k = 0; k < length3; k++) {
					if (s.get(k).equals(nome)) {
						b = true;
						break;
					}
				}
				if (!b)
					s.add(nome);
			}
		}
		return s.toArray(new String[0]);
	}
	
	//Retorna se a string em questao esta no banco de dados ou nao
	public boolean checar(String s) {
		int i, length;
		boolean b = false;
		String[] Saux = this.nomesdalista();
		length = Saux.length;
		for (i = 0; i < length; i++) {
			if (s.equals(Saux[i])) {
				b = true;
				break;
			}
		}
		return b;
	}
	
	//Metodo que ao receber o codigo fonte pre-arranjado e o calendaria usa-os
	//pra criar a lista com as serie-objetos
	private static void dolist(String s) {
		int i, j, aux, aux2;
		Calendar data = dataagora;
		lista = new ArrayList<ArrayList<serieobjeto>>();
		serieobjeto objserie;
		String[] stdia, stserie;
		stdia = s.split("iv id=\"d");
		aux = day + 7;
		for (i = day; i < aux; i++) {
			lista.add(new ArrayList<serieobjeto>());
			stserie = (stdia[i]).split("input");
			aux2 = stserie.length;
			for (j = 1; j < aux2; j++) {
				objserie = new serieobjeto(stserie[j], data);
				lista.get(i - day).add(objserie);
			}
			data.add(Calendar.DATE, 1);
		}
	}

	//Verifica se vai ser necessario acessar o proximo mes ou nao
	private static boolean otimizador() {
		int monthaux;
		Calendar data = dataagora;
		year = data.get(Calendar.YEAR);
		month = data.get(Calendar.MONTH) + 1;
		day = data.get(Calendar.DAY_OF_MONTH);
		data.add(Calendar.DATE, 7);
		monthaux = data.get(Calendar.MONTH) + 1;
		if (month == monthaux)
			return false;
		else
			return true;
	}

	//Construtor, ele acessa os sites necessários e edita um pouco o codigo-fonte
	//deles para entao mandar pro dolist
	public seriescalendar() {
		int month2, year2;
		getUrlSource src;
		String srccode = null;
		boolean check;
		dataagora = Calendar.getInstance();
		check = otimizador();
		try {
			src = new getUrlSource("http://www.pogdesign.co.uk/cat/" + month + "-" + year);
			srccode = src.urlsource;
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (check) {
			month2 = month + 1;
			year2 = year;
			try {
				if (month2 == 13) {
					month2 = 1;
					year2 = year + 1;
				}
				src = new getUrlSource("http://www.pogdesign.co.uk/cat/" + month2 + "-" + year2);
				srccode += src.urlsource + "end\n";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		dolist(srccode);
	}

	//Essa funcao escreve a lista separando em dias da semana eles;
	public void escreverlista() {
		int i, j, limit;
		for (i = 0; i < 7; i++) {
			limit = lista.get(i).size();
			for (j = 0; j < limit; j++) {
				System.out.println(i + " = " + lista.get(i).get(j).nome + " episodio: " + lista.get(i).get(j).episodio);
			}
		}
	}

	//Faz a lista de serie-objeto com base na lista das series de interesse
	public ArrayList<ArrayList<serieobjeto>> listafollow(followed f) {
		ArrayList<ArrayList<serieobjeto>> listafollow = new ArrayList<ArrayList<serieobjeto>>();
		int i, j, length;
		for (i = 0; i < 7; i++) {
			listafollow.add(new ArrayList<serieobjeto>());
			length = lista.get(i).size();
			for (j = 0; j < length; j++) {
				if (f.checar(lista.get(i).get(j).nome))
					listafollow.get(i).add(lista.get(i).get(j));
			}
		}
		return listafollow;
	}
}
