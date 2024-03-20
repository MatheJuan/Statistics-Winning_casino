package com.devlpjruan.statisticswinning.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import com.devlpjruan.statisticswinning.chart.JChart;
import com.devlpjruan.statisticswinning.entities.Person;
import com.devlpjruan.statisticswinning.observer.EditWindowObserver;

public class Panel extends JFrame implements EditWindowObserver {
	private static final long serialVersionUID = 1L;
	private final int milisec = 35;
	private int width = 900;
	private int height = 500;
	public boolean isPaused = true;
	private EditWindow eWindow = new EditWindow();
	private Person pessoa1;
	 
	
	@Override
	public void update(Person person) {
		this.pessoa1 = person;
	}
	
	
	public Panel() {
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.RED);
		setResizable(true);
		pessoa1 = new Person();
		eWindow.addObserver(this);
		add(createGUI());
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		SwingUtilities.invokeLater(() -> createGUI());

	}

		public JPanel createGUI() {
			BigDecimal money = pessoa1.getDinheiro();
			BigDecimal saldoCassino = pessoa1.getLucroCassino();
			int partidas = pessoa1.getPartidas();
			int vitorias = pessoa1.getVitorias();
			int derrotas = pessoa1.getDerrotas();
			int sorte = pessoa1.getSorte();
	
			JChart jch = new JChart();
			JFreeChart jchart = JChart.createJChart();
			JPanel mainPanel = new JPanel(new BorderLayout());
			ChartPanel chartPanel = new ChartPanel(jchart);
			JPanel panel = new JPanel();
			JPanel controlPanel = new JPanel();
	
			panel.setPreferredSize(new Dimension(0, 80));
			panel.setLayout(new GridLayout(2, 100, 20, 25));
			panel.setBackground(Color.LIGHT_GRAY);
	
			JLabel label1 = new JLabel("Vitorias: " + vitorias);
			JLabel label2 = new JLabel("Derrotas: " + derrotas);
			JLabel label3 = new JLabel("Partidas: " + partidas);
			JLabel label4 = new JLabel("Lucro do casino: " + saldoCassino);
			JLabel label5 = new JLabel("Dinheiro: " + money);
			JLabel label6 = new JLabel("Sorte: " + sorte);
	
			Timer timer = new Timer(milisec, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (!isPaused) {
						jch.updateChart(jchart, pessoa1, label1, label2, label3, label4, label5, label6);
						
					}
				}
			});
			timer.start();
	
			JButton startButton = new JButton("START");
			JButton stopButton = new JButton("STOP");
			JButton editButton = new JButton("EDIT");
			
			
			startButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(eWindow.confirmedEdit()) {
					editButton.setText("NEW");
					startButton.setText("RESUME");
	
					stateExecution(false);
					startButton.setEnabled(false);
					stopButton.setEnabled(true);
					}else {
						JOptionPane.showMessageDialog(null, "Valores nulos não podem ser executados!! :(");
					}
				}
			});
	
			stopButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					stateExecution(true);
					startButton.setEnabled(true);
					stopButton.setEnabled(false);
				}
			});
	
			editButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame editFrame = new JFrame();
					editFrame.setSize(80, 100);
					editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					editFrame.add(eWindow.createEditPerson());
					editFrame.pack();
					editFrame.setLocationRelativeTo(null);
					editFrame.setVisible(true);
					
	
					 
					if (editButton.getText().equals("NEW")) {
						JFreeChart newChart = newData();
						chartPanel.setChart(newChart);
						startButton.setText("START");
						stopButton.doClick();
						timer.stop();
						Timer newTimer = new Timer(milisec, new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (!isPaused) {
									jch.updateChart(newChart, pessoa1, label1, label2, label3, label4, label5, label6);
	
								}
							}
						});
						newTimer.start();
					}
				}
			});
	
			controlPanel.add(startButton);
			controlPanel.add(stopButton);
			controlPanel.add(editButton);
	
			panel.add(label1);
			panel.add(label2);
			panel.add(label3);
			panel.add(label4);
			panel.add(label5);
			panel.add(label6);
	
			chartPanel.setPreferredSize(new Dimension(800, 400));
			mainPanel.setBackground(Color.BLACK);
			mainPanel.setBorder(BorderFactory.createEmptyBorder());
			mainPanel.add(chartPanel, BorderLayout.NORTH);
			mainPanel.add(controlPanel, BorderLayout.CENTER);
			mainPanel.add(panel, BorderLayout.SOUTH);

		return mainPanel;
	}
	public static JFreeChart newData() {
		Person person = new Person();

		new JLabel("Vitorias: " + person.getVitorias());
		new JLabel("Derrotas: " + person.getDerrotas());
		new JLabel("Partidas: " + person.getPartidas());
		new JLabel("Lucro do casino: " + person.getLucroCassino());
		new JLabel("Dinheiro: " + person.getDinheiro());
		new JLabel("Sorte: " + person.getSorte());
		JFreeChart jchart = JChart.createJChart();
		return jchart;
		}

	public void stateExecution(Boolean result) {
		isPaused = result;
	}

}