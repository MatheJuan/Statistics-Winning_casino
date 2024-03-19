package com.devlpjruan.statisticswinning.panel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.devlpjruan.statisticswinning.entities.Person;
import com.devlpjruan.statisticswinning.observer.EditWindowObserver;

public class EditWindow {
	public boolean isConfirmed=false;
	
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
		for (EditWindowObserver obs : listaObservers) {
			obs.update(person);
		}
	}

	public JPanel createEditPerson() {
		JPanel painel = new JPanel(new GridBagLayout());
		GridBagConstraints gdc = new GridBagConstraints();

		gdc.anchor = GridBagConstraints.NORTHWEST;
		gdc.gridx = 0;
		gdc.gridy = 0;
		JLabel labelDinheiro = new JLabel("R$ inicial:");
		painel.add(labelDinheiro, gdc);

		gdc.gridx = 1;
		gdc.gridy = 0;
		JTextField txtDinheiro = new JTextField(2);
		painel.add(txtDinheiro, gdc);

		gdc.gridx = 0;
		gdc.gridy = 1;
		JLabel labelSorte = new JLabel("% de sorte:");
		painel.add(labelSorte, gdc);
		
		gdc.gridx = 1;
		gdc.gridy = 1;
		JTextField txtSorte = new JTextField(2);
		painel.add(txtSorte, gdc);

		gdc.gridx = 0;
		gdc.gridy = 2;
		JLabel labelAposta = new JLabel("Aposta:");
		painel.add(labelAposta, gdc);

		gdc.gridx = 1;
		gdc.gridy = 2;
		JTextField txtAposta = new JTextField(2);
		painel.add(txtAposta, gdc);

		gdc.gridx = 0;
		gdc.gridy = 3;
		JCheckBox checkB = new JCheckBox("Aposta Random", false);
		painel.add(checkB, gdc);

		gdc.gridx = 0;
		gdc.gridy = 4;
		gdc.anchor = GridBagConstraints.CENTER;
		
		JButton confirm = new JButton("Confirmar");
		painel.add(confirm, gdc);

		checkB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean wasSelected = checkB.isSelected();
				txtAposta.setEnabled(!wasSelected);
				if (!txtAposta.isEnabled()) {
					txtAposta.setText("0");
				}

			}
		});
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String dinheiro = txtDinheiro.getText();
					String fieldSorte= txtSorte.getText();
					String fieldAposta=txtAposta.getText();
					int sorte = Integer.parseInt(fieldSorte);
					int aposta = Integer.parseInt(fieldAposta);
					double cassino = 0.00;
					isConfirmed=true;
					
					Person pessoa = new Person(dinheiro, sorte, cassino, aposta);
					
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(confirm);
					frame.dispose();
					
					notifyObservers(pessoa);
					
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos.");
				}
			}
		});
		painel.setPreferredSize(new Dimension(150, 160));
		
		return painel;
	}
 public Boolean confirmedEdit() {
	 return isConfirmed;
 }
}
