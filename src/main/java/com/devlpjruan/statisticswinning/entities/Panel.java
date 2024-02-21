package com.devlpjruan.statisticswinning.entities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class Panel extends JFrame {
 
	private static final long serialVersionUID = 1L;
	private static final int milisec= 1;
	private int height=600;
	private int width=400;
	
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
		JPanel mainPanel = new JPanel();
		ChartPanel chartPanel = new ChartPanel(jchart);
		
		chartPanel.setPreferredSize(new Dimension(800, 600));
		mainPanel.setBackground(Color.BLACK);
		mainPanel.setBorder(BorderFactory.createEmptyBorder());
		mainPanel.add(chartPanel);
		
		Timer timer = new Timer(milisec, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JChart.updateChart(jchart);
			}
		}); timer.start();
	 
		 return mainPanel;
		
	}
	
	
	
}
