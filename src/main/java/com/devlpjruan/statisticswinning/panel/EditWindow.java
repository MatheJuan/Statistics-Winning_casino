package com.devlpjruan.statisticswinning.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.devlpjruan.statisticswinning.entities.Person;
import com.devlpjruan.statisticswinning.observer.EditWindowObserver;

public class EditWindow {
	 
	public EditWindow() {
	}
 
	private List<EditWindowObserver> listaObservers = new ArrayList<>();
	
	public void removeObserver(EditWindowObserver obs) {
		listaObservers.remove(obs);
	}
	public void addObserver(EditWindowObserver obs) {
		listaObservers.add(obs);
	} 
	private void notifyObservers(Person person) {
		for(EditWindowObserver obs : listaObservers) {
			obs.update(person);
		}
	}
 
	public JPanel EditPerson() {
		JPanel painel = new JPanel();
		
		JLabel labelSorte = new JLabel("Porcentagem de sorte: ");
		JTextField text2 = new JTextField(2);
		
		JLabel labelDinheiro = new JLabel("Montante inicial: ");
		JTextField text1 = new JTextField(7);
		
		JButton confirm = new JButton("Confirmar");
		
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				
				String dinheiro=text1.getText();
				String textSorte = text2.getText();
				
				if(dinheiro!=null && !dinheiro.isEmpty() && textSorte!=null && !textSorte.isEmpty()) {
				int sorte= Integer.parseInt(textSorte);
				
				Person pessoa = new Person(dinheiro, sorte);
				notifyObservers(pessoa);
				
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(confirm);
				frame.dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Por favor, insira um montante válido.");
				}
			}
		});
		
		painel.add(labelDinheiro);
		painel.add(text1);
		painel.add(labelSorte);
		painel.add(text2);
		painel.add(confirm);
		
		return painel;
	}
 }
