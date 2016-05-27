package seriescalendar;

public class serieobjeto {
	String nome="", episodio="";
	public serieobjeto(String serie){
		char a;
		int i;
		String[] aux=serie.split("-summary");
		a=(aux[1]).charAt(0);
		for(i=0; a!='>'; i++){
			a=aux[1].charAt(i);
		}
		a=aux[1].charAt(i);
		for(; a!='<';){
			i++;
			nome+=a;
			a=aux[1].charAt(i);
		}
		aux=serie.split("rem;");
		for(i=0; a!='>'; i++){
			a=aux[1].charAt(i);
		}
		a=aux[1].charAt(i);
		for(; a!='<';){
			i++;
			episodio+=a;
			a=aux[1].charAt(i);
		}
	}
}
