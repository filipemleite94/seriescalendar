package seriescalendar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class seriescalendar {
	static int year;
	static int day;
	static int month;
	static ArrayList<ArrayList<serieobjeto>> lista;
	private static Calendar dataagora;
	
	public Calendar data(){
		return dataagora;
	}
	
	public String[] nomesdalista(){
		int i, j, k, length, length2, length3;
		String nome;
		boolean b;
		ArrayList<String> s = new ArrayList<String>();
		length = lista.size();
		for(i = 0; i<length; i++){
			length2 = lista.get(i).size();
			for(j = 0; j<length2; j++){
				length3=s.size();
				b=false;
				nome = lista.get(i).get(j).nome;
				for( k = 0; k<length3; k++){
					if (s.get(k).equals(nome)) {
						b=true;
						break;
					}
				}
				if (!b) s.add(nome);
			}
		}
		return s.toArray(new String[0]);
	}
	
	private static void dolist(String s){
		int i, j, aux, aux2;
		Calendar data = dataagora;
		lista = new ArrayList<ArrayList<serieobjeto>>();
		serieobjeto objserie;
		String[] stdia, stserie;
		stdia=s.split("iv id=\"d");
		aux=day+7;
		for(i=day; i<aux; i++){
			lista.add(new ArrayList<serieobjeto>());
		    stserie = (stdia[i]).split("input");
		    aux2=stserie.length;
		    for(j=1; j<aux2; j++){
		    	objserie = new serieobjeto(stserie[j], data);
		    	lista.get(i-day).add(objserie);
		    }
		    data.add(Calendar.DATE, 1);
		}
	}
	
	private static boolean otimizador(){
		int monthaux;
		Calendar data = dataagora;
		year = data.get(Calendar.YEAR);
	    month = data.get(Calendar.MONTH)+1;
	    day = data.get(Calendar.DAY_OF_MONTH);
	    data.add(Calendar.DATE, 7);
	    monthaux=data.get(Calendar.MONTH)+1;
	    if(month==monthaux)
	    	return false;
	    else return true;
	}

	public seriescalendar(){
		int month2, year2; 
		getUrlSource src;
		String srccode = null;
		boolean check;
		dataagora = Calendar.getInstance();
		check = otimizador();
		try {
			src = new getUrlSource("http://www.pogdesign.co.uk/cat/"+month+"-"+year);
			srccode=src.urlsource;
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(check){
			month2=month+1;
			year2=year;
			try {
				if(month2==13){
					month2=1;
					year2=year+1;
				}
				src = new getUrlSource("http://www.pogdesign.co.uk/cat/"+month2+"-"+year2);
				srccode+=src.urlsource+"end\n";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		dolist(srccode);
	}
	
	public void escreverlista() {
		int i, j, limit;
		for(i=0;i<7;i++){
			limit=lista.get(i).size();
			for(j=0;j<limit;j++){
				System.out.println(i + " = " + lista.get(i).get(j).nome + " episodio: " + lista.get(i).get(j).episodio);
			}
		}
	}
	
	public ArrayList<ArrayList<serieobjeto>> listafollow (followed f){
		ArrayList<ArrayList<serieobjeto>> listafollow = new ArrayList<ArrayList<serieobjeto>>();
		int i, j, length;
		for(i=0; i<7; i++){
			listafollow.add(new ArrayList<serieobjeto>());
			length=lista.get(i).size();			
			for(j=0;j<length;j++){
				if(f.checar(lista.get(i).get(j).nome)) listafollow.get(i).add(lista.get(i).get(j));
			}
		}
		return listafollow;
	}
}
