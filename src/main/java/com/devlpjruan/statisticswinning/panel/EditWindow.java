package com.devlpjruan.statisticswinning.panel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
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
		JPanel painel = new JPanel(new GridBagLayout());
		GridBagConstraints gdc = new GridBagConstraints();
		
		gdc.anchor = GridBagConstraints.NORTHWEST; 
		gdc.gridx=0;
		gdc.gridy=0;
		JLabel labelDinheiro = new JLabel("$ inicial: ");
		painel.add(labelDinheiro, gdc);
		
		gdc.gridx=1;
		gdc.gridy=0;
		JTextField txtDinheiro = new JTextField(2);
		painel.add(txtDinheiro, gdc);
		
		gdc.gridx=0;
		gdc.gridy=1;
		JLabel labelSorte = new JLabel("% sorte: ");
		painel.add(labelSorte, gdc);
		
		gdc.gridx=1;
		gdc.gridy=1;
		JTextField txtSorte = new JTextField(2);
		painel.add(txtSorte, gdc);
		
		gdc.gridx=0;
		gdc.gridy=2;
		gdc.gridwidth = 2;
		JButton confirm = new JButton("Confirmar");
		painel.add(confirm, gdc );
		
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				
				String dinheiro=txtDinheiro.getText();
				String textSorte = txtSorte.getText();
				
				try {
				int sorte= Integer.parseInt(textSorte);
				double value = 0.00;
				Person pessoa = new Person(dinheiro, sorte, BigDecimal.valueOf(value));
				notifyObservers(pessoa);
				
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(confirm);
				frame.dispose();
				
				}catch (NumberFormatException e1) {
				
					JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos.");
				}
			}
		});
		painel.setPreferredSize(new Dimension(120,100));;
	

		return painel;
	}
 }
