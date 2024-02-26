package com.devlpjruan.statisticswinning.entities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class Panel extends JFrame {
 
	private static final long serialVersionUID = 1L;
	private static final int milisec= 1000;
	private int width=900;
	private int height=500;
	 
	
		public Panel() { 
			setSize(width, height);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setResizable(true);
			add(createGUI());
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
			SwingUtilities.invokeLater(() ->createGUI());
			
		}
		
		public static JPanel createGUI(){
			
			JFreeChart jchart = JChart.createJChart();
			JPanel mainPanel = new JPanel(new BorderLayout());
			ChartPanel chartPanel = new ChartPanel(jchart);
			JPanel panel = new JPanel();
			
			panel.setPreferredSize(new Dimension(0,80));
			panel.setLayout(new GridLayout(2, 100, 20 ,25));
			panel.setBackground(Color.LIGHT_GRAY);
			
			JLabel label1 = new JLabel("Vitorias:");
			JLabel label2 = new JLabel("Derrotas:");
			JLabel label3 = new JLabel("Partidas:");
			JLabel label4 = new JLabel("Lucro do casino:");
			
			panel.add(label1);
			panel.add(label2);
			panel.add(label3);
			panel.add(label4);
			
			
			chartPanel.setPreferredSize(new Dimension(800, 400));
			mainPanel.setBackground(Color.BLACK);
			mainPanel.setBorder(BorderFactory.createEmptyBorder());	
			mainPanel.add(chartPanel, BorderLayout.NORTH);
			mainPanel.add(panel, BorderLayout.SOUTH);
			
			Timer timer = new Timer(milisec, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JChart.updateChart(jchart);
				}
			}); timer.start();
			
			
		  return mainPanel;
			
			}
	
}
