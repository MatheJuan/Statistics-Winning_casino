package com.devlpjruan.statisticswinning.entities;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class Panel extends JFrame {
 
	private static final long serialVersionUID = 1L;
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
 
		
	}
	
	public static JPanel createGUI(){
		JFreeChart jchart = JChart.createJChart();
		
		JPanel mainPanel = new JPanel();
		ChartPanel chartPanel = new ChartPanel(jchart);
		chartPanel.setPreferredSize(new Dimension(800, 600));
		mainPanel.setBackground(Color.BLACK);
		mainPanel.setBorder(BorderFactory.createEmptyBorder());
		mainPanel.add(chartPanel);
		
		return mainPanel;
		
	}
	
	
	
}
