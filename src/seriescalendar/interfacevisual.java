package seriescalendar;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;

public class interfacevisual extends JFrame implements ActionListener {
	JTextArea jtFollow;
	JList<String> j1List, j2List;
	JButton jbUpdate, jbRemove, jbAdd, jbCheck;
	JLabel jlUpdate;
	JScrollPane jsText1,jsText2,jsText3;
	String[] Saux;
	static seriescalendar sc;
	static followed mylist;

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

		if (source == jbUpdate) {
			sc = new seriescalendar();
			Saux = sc.nomesdalista();
			files.clear();
			for (int i = 0; i < Saux.length; i++) {
				if (!mylist.checar(Saux[i])) {
					files.addElement(Saux[i]);
				}
			}
		}

		if (source == jbRemove) {
			while (j2List.getSelectedValue() != null) {
				mylist.remover(j2List.getSelectedValue().toString());
				files.addElement(j2List.getSelectedValue().toString());
				files2.remove(j2List.getSelectedIndex());
			}
		}

		if (source == jbAdd) {
			while (j1List.getSelectedValue() != null) {
				mylist.add(j1List.getSelectedValue().toString());
				files2.addElement(j1List.getSelectedValue().toString());
				files.remove(j1List.getSelectedIndex());
				Saux = mylist.listar();
			}
		}

		if (source == jbCheck) {
			ArrayList<ArrayList<serieobjeto>> listafollow;
			int i, j, length;
			Calendar data = sc.data();
			listafollow = sc.listafollow(mylist);
			Saux[0] = "";
			for (i = 0; i < 7; i++) {
				length = listafollow.get(i).size();
				Saux[0] += dayofweek(data.get(Calendar.DAY_OF_WEEK)) + ":\n";
				for (j = 0; j < length; j++) {
					Saux[0] += listafollow.get(i).get(j).nome + " " + listafollow.get(i).get(j).episodio + "\n";
				}
				Saux[0]+="\n";
				data.add(Calendar.DATE, 1);
			}
			jtFollow.setText(Saux[0]);
		}
	}

	public interfacevisual() {
		setSize(1250, 750);
		setTitle("Calendario de S�ries");
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Ends process when closed
		setResizable(false); // It is not allowed to resize the Layout

		jtFollow = new JTextArea(50, 20);
		jtFollow.setBounds(760, 80, 350, 495);
		jtFollow.setFont(new Font("SansSerif", Font.BOLD, 12));

		j1List = new JList<String>(files);
		jsText1 = new JScrollPane(j1List);
		//j1List.setSize(new Dimension(80, 180));
		jsText1.setBounds(20, 80, 350, 495);
		//j1List.setAutoscrolls(true);
		
		
		

		j2List = new JList<String>(files2);
		jsText2 = new JScrollPane(j2List);
		//j1List.setSize(new Dimension(80, 180));
		jsText2.setBounds(390, 80, 350, 495);
		//j2List.setSize(new Dimension(80, 180));
		//j2List.setAutoscrolls(true);

		jlUpdate = new JLabel("Update", Label.LEFT);
		jlUpdate.setBounds(20, 10, 80, 25);
		jlUpdate.setFont(new Font("SansSerif", Font.BOLD, 15));

		jbUpdate = new JButton("Update");
		jbRemove = new JButton("Remove");
		jbAdd = new JButton("Add");
		jbCheck = new JButton("Check");
		jbUpdate.setBounds(350, 40, 90, 25);
		jbRemove.setBounds(390, 600, 90, 25);
		jbAdd.setBounds(20, 600, 90, 25);
		jbCheck.setBounds(760, 600, 90, 25);
		jbUpdate.addActionListener(this);
		jbRemove.addActionListener(this);
		jbAdd.addActionListener(this);
		jbCheck.addActionListener(this);

		getContentPane().setLayout(null);

		getContentPane().add(jlUpdate);
		getContentPane().add(jbUpdate);
		getContentPane().add(jsText1);
		getContentPane().add(jsText2);
		getContentPane().add(jbRemove);
		getContentPane().add(jbAdd);
		getContentPane().add(jbCheck);
		getContentPane().add(jtFollow);
	}

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