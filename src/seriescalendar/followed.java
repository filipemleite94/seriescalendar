package seriescalendar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class followed {
	
	private ArrayList<String> follow;
	private int length;
	
	public followed(){
		follow = new ArrayList<String>();
		length = 0;
	}
	
	public void remover (String s){
		int i;
		for(i=0; i<length; i++){
			if(s.equals(follow.get(i))){
				follow.remove(i);
				length--;
				break;
			}
		}
	}
	
	public void savemylist(File f) throws IOException{
		int i;
		String line;
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		for(i = 0; i < length; i++){
			line = follow.get(i);
			writer.write(line);
			writer.newLine();
		}
		writer.close();
	}
	
	public void getmylist(File f) throws IOException{
		String line;
		length = 0;
		follow.clear();
		BufferedReader br = new BufferedReader(new FileReader(f));
		line = br.readLine();
		while(line != null){
			follow.add(line);
			line = br.readLine();
		}
	}
	
	public void removeall (){
		follow.clear();
		length = 0;
	}
	
	public void add (String s){
		follow.add(s);
		length++;
	}
	
	public String[] listar(){
		return follow.toArray(new String[0]);
	}
	
	public boolean checar(String s){
		int i;
		boolean b = false;
		for(i=0; i<length; i++){
			if(s.equals(follow.get(i))){
				b = true;
				break;
			}
		}		
		return b;
	}
	
}
