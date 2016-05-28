package seriescalendar;

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
