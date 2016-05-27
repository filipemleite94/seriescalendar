package seriescalendar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class seriescalendar {
	static int year;
	static int day;
	static int month;
	static ArrayList<ArrayList<serieobjeto>> lista;
	
	private static void dolist(String s){
		int i, j, aux, aux2;
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
		    	objserie = new serieobjeto(stserie[j]);
		    	lista.get(i-day).add(objserie);
		    }
		}
	}
	
	private static boolean otimizador(){
		int monthaux;
		Calendar data = Calendar.getInstance();
		year = data.get(Calendar.YEAR);
	    month = data.get(Calendar.MONTH)+1;
	    day = data.get(Calendar.DAY_OF_MONTH);
	    data.add(Calendar.DATE, 7);
	    monthaux=data.get(Calendar.MONTH)+1;
	    if(month==monthaux)
	    	return false;
	    else return true;
	}

	
	
	public static void main(String[] args) {
		getUrlSource src;
		String srccode = null;
		boolean check;
		check = otimizador();
		int month2, year2, i, j, limit;
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
				if(month2==12){
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
		for(i=0;i<7;i++){
			limit=lista.get(i).size();
			for(j=0;j<limit;j++){
				System.out.println(i + " = " + lista.get(i).get(j).nome + " episodio: " + lista.get(i).get(j).episodio);
			}
		}
	}

}
