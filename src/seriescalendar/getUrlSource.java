package seriescalendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

//Ler o codigo fonte da pagina original e pre-edita ele
public class getUrlSource {
	public String urlsource;
	int i;

	public getUrlSource(String url) throws IOException {
		URL yahoo = new URL(url);
		URLConnection yc = yahoo.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
		String inputLine;
		StringBuilder a = new StringBuilder();
		for (i = 0; i < 450; in.readLine(), i++)
			; // Os títulos começam depois da linha 450;
		for (inputLine = in.readLine(); !inputLine.equals("<div class=\"noday\"></div>"); inputLine = in.readLine())
			;
		for (; inputLine.equals("<div class=\"noday\"></div>"); inputLine = in.readLine())
			;
		for (inputLine = in.readLine(); !inputLine.equals("   <div class=\"box728 abot\">"); inputLine = in.readLine())
			a.append(inputLine + "\n\r");
		in.close();
		urlsource = a.toString();
	}

}
