package seriescalendar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//Lista das series acompanhadas com funcoes pre-implementadas
public class followed {

	private ArrayList<String> follow;//lista das series acompanhadas
	private int length;//tamanho da lista

	//Construtor
	public followed() {
		follow = new ArrayList<String>();
		length = 0;
	}

	//Remove um elemento da lista
	public void remover(String s) {
		int i;
		for (i = 0; i < length; i++) {
			if (s.equals(follow.get(i))) {
				follow.remove(i);
				length--;
				break;
			}
		}
	}

	//Salva a lista
	public void savemylist(File f) throws IOException {
		int i;
		String line;
		FileWriter filewriter = new FileWriter(f);
		BufferedWriter writer = new BufferedWriter(filewriter);
		for (i = 0; i < length; i++) {
			line = follow.get(i);
			writer.write(line);
			writer.newLine();
		}
		writer.close();
		filewriter.close();
	}

	//Implementa a lista da memoria na variavel lista atual
	public void getmylist(File f) throws IOException {
		String line;
		length = 0;
		follow.clear();
		FileReader filereader = new FileReader(f);
		BufferedReader br = new BufferedReader(filereader);
		line = br.readLine();
		while (line != null) {
			follow.add(line);
			line = br.readLine();
		}
		br.close();
		filereader.close();
	}

	//Limpa a lista
	public void removeall() {
		follow.clear();
		length = 0;
	}

	//Adiciona um termo a lista
	public void add(String s) {
		follow.add(s);
		length++;
	}

	//Devolve um vetor de arrays com todos os itens da lista
	public String[] listar() {
		return follow.toArray(new String[0]);
	}

	//Retorna uma booleana que diz se a string em questao pertence ou nao a lista
	public boolean checar(String s) {
		int i;
		boolean b = false;
		for (i = 0; i < length; i++) {
			if (s.equals(follow.get(i))) {
				b = true;
				break;
			}
		}
		return b;
	}

	//Retorna um vetor de strings com todos as series que nao estao na lista
	//de mandada
	public String[] miss(ArrayList<ArrayList<serieobjeto>> listafollow) {
		int i, j, c;
		boolean b;
		ArrayList<String> s = new ArrayList<String>();
		ArrayList<String> miss = new ArrayList<String>();
		for (i = 0; i < 7; i++) {
			c = listafollow.get(i).size();
			for (j = 0; j < c; j++) {
				s.add(listafollow.get(i).get(j).nome);
			}
		}
		for (i = 0; i < length; i++) {
			c = s.size();
			b = false;
			for (j = 0; (!b) && (j < c); j++) {
				if (follow.get(i).equals(s.get(j))) {
					b = true;
					s.remove(j);
				}
			}
			if (!b)
				miss.add(follow.get(i));
		}
		return miss.toArray(new String[0]);
	}

}
