package seriescalendar;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import javax.swing.*;

public class interfacevisual extends JFrame implements ActionListener {
	JTextArea jtFollow;
	JList<String> j1List, j2List;
	JButton jbUpdate, jbRemove, jbAdd, jbCheck, jbGet, jbSave;
	JLabel jlUpdate;
	JScrollPane jsText1, jsText2, jsText3;
	String[] Saux = null;
	static seriescalendar sc = null;
	static followed mylist = null;

	DefaultListModel<String> files = new DefaultListModel<String>();
	DefaultListModel<String> files2 = new DefaultListModel<String>();

	public static void main(String[] args) {
		mylist = new followed();
		interfacevisual b = new interfacevisual();
		b.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String S;
		
		//Realiza o update do banco de dados das series;
		if (source == jbUpdate) {
			sc = new seriescalendar();
			Saux = sc.nomesdalista();
			files.clear();
			Arrays.sort(Saux);
			for (int i = 0; i < Saux.length; i++) {
					files.addElement(Saux[i]);
			}
		}
		
		//Remove uma serie acompanhada
		if (source == jbRemove) {
			while (j2List.getSelectedValue() != null) {
				S = j2List.getSelectedValue().toString();
				files2.remove(j2List.getSelectedIndex());
				mylist.remover(S);
				if(!sc.checar(S))
					files.addElement(S);
			}
		}

		//Adiciona uma serie a ser acompanhada
		if (source == jbAdd) {
			while (j1List.getSelectedValue() != null) {
				S=j1List.getSelectedValue().toString();
				files.remove(j1List.getSelectedIndex());
				if (!mylist.checar(S)) {
					mylist.add(S);
					Saux = mylist.listar();
					Arrays.sort(Saux);
					files2.clear();
					for (int i = 0; i < Saux.length; i++) {
						files2.addElement(Saux[i]);
					}
				}
			}
			Saux = sc.nomesdalista();
			files.clear();
			Arrays.sort(Saux);
			for (int i = 0; i < Saux.length; i++) {
					files.addElement(Saux[i]);
			}
		}
		
		//Pega a lista atual no arquivo texto
		if (source == jbGet) {
			File f = new File("arquivo.txt");
			mylist.removeall();
			files2.clear();
			try {
				FileReader filereader = new FileReader(f);
				BufferedReader br = new BufferedReader(filereader);
				S = br.readLine();
				while (S != null) {
					mylist.add(S);
					S = br.readLine();
				}
				br.close();
				filereader.close();
				Saux = mylist.listar();
				Arrays.sort(Saux);
				for (int i = 0; i < Saux.length; i++) {
					files2.addElement(Saux[i]);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		//Salva a lista atual em arquivo texto
		if (source == jbSave) {
			File f = new File("arquivo.txt");
			try {
				mylist.savemylist(f);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		//Checa quais são os dias de exibicao da lista acompanhada
		if (source == jbCheck) {
			ArrayList<ArrayList<serieobjeto>> listafollow;
			int i, j, length;
			Calendar data = sc.data();
			listafollow = sc.listafollow(mylist);
			S = "";
			Saux = mylist.miss(listafollow);
			length = Saux.length;
			if (length != 0)
				S = "Not Aired:\n";
			for (i = 0; i < length; i++) {
				S += Saux[i] + "\n";
			}
			if (length != 0)
				S += "\n";
			for (i = 0; i < 7; i++) {
				length = listafollow.get(i).size();
				S += dayofweek(data.get(Calendar.DAY_OF_WEEK)) + ":\n";
				for (j = 0; j < length; j++) {
					S += listafollow.get(i).get(j).nome + " " + listafollow.get(i).get(j).episodio + "\n";
				}
				S += "\n";
				data.add(Calendar.DATE, 1);
			}
			jtFollow.setText(S);
		}
	}

	//Construtor da interface visual
	public interfacevisual() {
		setSize(1250, 750);
		setTitle("Calendario de Séries");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false); // It is not allowed to resize the Layout

		jtFollow = new JTextArea(30, 20);
		jsText3 = new JScrollPane(jtFollow);
		jsText3.setBounds(760, 80, 350, 495);
		jtFollow.setFont(new Font("SansSerif", Font.BOLD, 12));

		j1List = new JList<String>(files);
		jsText1 = new JScrollPane(j1List);
		// j1List.setSize(new Dimension(80, 180));
		jsText1.setBounds(20, 80, 350, 495);
		// j1List.setAutoscrolls(true);

		j2List = new JList<String>(files2);
		jsText2 = new JScrollPane(j2List);
		// j1List.setSize(new Dimension(80, 180));
		jsText2.setBounds(390, 80, 350, 495);
		// j2List.setSize(new Dimension(80, 180));
		// j2List.setAutoscrolls(true);

		jlUpdate = new JLabel("Calendário de Séries", Label.LEFT);
		jlUpdate.setBounds(20, 10, 200, 25);
		jlUpdate.setFont(new Font("SansSerif", Font.BOLD, 15));

		jbUpdate = new JButton("Update");
		jbGet = new JButton("Get my List!");
		jbSave = new JButton("Save my List!");
		jbRemove = new JButton("Remove");
		jbAdd = new JButton("Add");
		jbCheck = new JButton("Check");
		jbUpdate.setBounds(100, 40, 90, 25);
		jbGet.setBounds(400, 40, 120, 25);
		jbSave.setBounds(550, 40, 120, 25);
		jbRemove.setBounds(390, 600, 90, 25);
		jbAdd.setBounds(20, 600, 90, 25);
		jbCheck.setBounds(760, 600, 90, 25);
		jbUpdate.addActionListener(this);
		jbRemove.addActionListener(this);
		jbAdd.addActionListener(this);
		jbCheck.addActionListener(this);
		jbGet.addActionListener(this);
		jbSave.addActionListener(this);

		getContentPane().setLayout(null);

		getContentPane().add(jlUpdate);
		getContentPane().add(jbUpdate);
		getContentPane().add(jbGet);
		getContentPane().add(jbSave);
		getContentPane().add(jsText1);
		getContentPane().add(jsText2);
		getContentPane().add(jbRemove);
		getContentPane().add(jbAdd);
		getContentPane().add(jbCheck);
		getContentPane().add(jsText3);
	}

	//metodo que troca um inteiro por um dia da semana
	public static String dayofweek(int i) {
		String day = null;
		switch (i) {
		case 1:
			day = "Sunday";
			break;
		case 2:
			day = "Monday";
			break;
		case 3:
			day = "Tuesday";
			break;
		case 4:
			day = "Wednesday";
			break;
		case 5:
			day = "Thursday";
			break;
		case 6:
			day = "Friday";
			break;
		case 7:
			day = "Saturday";
			break;
		}
		return day;
	}

}