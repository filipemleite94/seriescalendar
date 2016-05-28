package seriescalendar;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class interfacevisual extends JFrame implements ActionListener{
	JList<String> j1List, j2List;
	JButton jbUpdate, jbRemove, jbadd;
	JLabel jlUpdate, jlName, jlTag, jlNote;
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
				if(!mylist.checar(Saux[i])){
					files.addElement(Saux[i]);
				}
			}
		}
		
		if (source == jbRemove) {
			if (j2List.getSelectedValue() != null) {
				mylist.remover(j2List.getSelectedValue().toString());
				files.addElement(j2List.getSelectedValue().toString());
				files2.remove(j2List.getSelectedIndex());
			}
		}
		
		if (source == jbadd) {
			if (j1List.getSelectedValue() != null) {
				mylist.add(j1List.getSelectedValue().toString());
				files2.addElement(j1List.getSelectedValue().toString());
				files.remove(j1List.getSelectedIndex());
				Saux = mylist.listar();
			}
		}
	}
	
	public interfacevisual() {
		setSize(1250, 750);
		setTitle("Calendario de Séries");
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Ends process when closed
		setResizable(false); // It is not allowed to resize the Layout

		j1List = new JList<String>(files);
		j1List.setSize(new Dimension(80, 180));
		j1List.setBounds(20, 80, 420, 495);
		j1List.setAutoscrolls(true);
		
		j2List = new JList<String>(files2);
		j2List.setSize(new Dimension(80, 180));
		j2List.setBounds(460, 80, 420, 495);
		j2List.setAutoscrolls(true);

		jlUpdate = new JLabel("Update", Label.LEFT);
		jlName = new JLabel("Name", Label.LEFT);
		jlTag = new JLabel("Tag", Label.LEFT);
		jlNote = new JLabel("Note", Label.LEFT);
		jlUpdate.setBounds(20, 10, 80, 25);
		jlName.setBounds(510, 10, 70, 25);
		jlTag.setBounds(750, 600, 80, 25);
		jlNote.setBounds(505, 40, 70, 25);
		jlUpdate.setFont(new Font("SansSerif", Font.BOLD, 20));
		jlName.setFont(new Font("SansSerif", Font.BOLD, 20));
		jlTag.setFont(new Font("SansSerif", Font.BOLD, 20));
		jlNote.setFont(new Font("SansSerif", Font.BOLD, 20));

		jbUpdate = new JButton("Update");
		jbRemove = new JButton("Remove");
		jbadd = new JButton("Add");
		jbUpdate.setBounds(350, 40, 90, 25);
		jbRemove.setBounds(20, 600, 90, 25);
		jbadd.setBounds(350, 600, 90, 25);
		jbUpdate.addActionListener(this);
		jbRemove.addActionListener(this);
		jbadd.addActionListener(this);

		getContentPane().setLayout(null);

		getContentPane().add(jlUpdate);
		getContentPane().add(jlName);
		getContentPane().add(jbUpdate);
		getContentPane().add(jlNote);
		getContentPane().add(j1List);
		getContentPane().add(j2List);
		getContentPane().add(jbRemove);
		getContentPane().add(jbadd);
		getContentPane().add(jlTag);
	}

}

