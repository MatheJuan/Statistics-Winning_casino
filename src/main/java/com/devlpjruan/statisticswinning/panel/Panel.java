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
	private final int milisec = 80;
	private int width = 900;
	private int height = 500;
	public static boolean isPaused = true;
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
		int partidas = pessoa1.getpartidas();
		int vitorias = pessoa1.getVitorias();
		int derrotas = pessoa1.getDerrotas();
		int sorte = pessoa1.getSorte();

		BigDecimal saldoCassino = pessoa1.getLucroCassino();

		JChart jch = new JChart();
		JFreeChart jchart = jch.createJChart();

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

		JButton startButtom = new JButton("START");
		JButton stopButtom = new JButton("STOP");
		JButton editButton = new JButton("EDIT");
		
		startButtom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editButton.setEnabled(false);;
				stateExecution(false);
			}
		});

		
		stopButtom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stateExecution(true);

			}
		});
		
		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame editFrame = new JFrame();
				editFrame.setSize(80, 100);
				editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				editFrame.add(eWindow.EditPerson());

				editFrame.pack();
				editFrame.setLocationRelativeTo(null);
				editFrame.setVisible(true);
			}
		});

		controlPanel.add(startButtom);
		controlPanel.add(stopButtom);
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

		Timer timer = new Timer(milisec, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isPaused) {
					jch.updateChart(jchart, pessoa1, label1, label2, label3, label4, label5, label6);

				}
			}
		});
		timer.start();
		return mainPanel;
	}

	public void stateExecution(Boolean result) {
		isPaused = result;
	}

}