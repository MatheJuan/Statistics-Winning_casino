package com.devlpjruan.statisticswinning.entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class JChart {
	public JChart() {
		createJChart();
	}
	
	Person pessoa = new Person(0, 10000, 50);
	int qtd = pessoa.getQtdJogadas();
	int win = pessoa.getVitorias();
	public static Double cash = 50.0;
	double aposta = 0;
	public static Random random = new Random();
	public static double luck = 50.0;
 
	public static XYSeriesCollection createDataSet() {
		XYSeries series = new XYSeries("");
		series.add(0, 0);

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		return dataset;
	}

	//Responsavel pela atualização e randomizacao da coordenada Y
	public static void updateChart(JFreeChart freeChart) {
		XYSeriesCollection dataset = (XYSeriesCollection) freeChart.getXYPlot().getDataset();
		XYSeries series = dataset.getSeries(0);
		 
	
		int item = series.getItemCount();
		double eixoX = (double) series.getX(item - 1);
		double eixoY= (double) series.getY(item - 1);
		double constY= random.nextDouble();
		
		XYPlot plot = freeChart.getXYPlot();
		double xBound = plot.getDomainAxis().getUpperBound();
		double yBound = plot.getRangeAxis().getUpperBound();
		
		// 
		if(constY<=(luck/100)) {
			System.out.println("LevelUP!! "+constY);
			series.add(eixoX+1, eixoY+1);
		}else{
			System.out.println("DownUp!! "+constY);
			 series.add(eixoX+1, eixoY-1);
		}
		//Ajuste dinamico dos eixos aposchegar o limite
		if (eixoY > yBound) {
		    plot.getRangeAxis().setRange(1, eixoY);
		}
		if (eixoX > xBound) {
		    plot.getDomainAxis().setRange(2, eixoX); 
		}
	}
	 

	public static JFreeChart createJChart() {
		XYSeriesCollection dataset = createDataSet();

		JFreeChart chart = ChartFactory.createXYLineChart("Probabilities", "X", "Y", dataset);

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesStroke(0, new BasicStroke(1.0f));
		renderer.setSeriesShapesVisible(0, false);
		XYPlot plot = chart.getXYPlot();

		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.WHITE);
		plot.setForegroundAlpha(1.0f);
		plot.setRangeGridlinePaint(Color.RED);
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.black);
		plot.setDomainMinorGridlinesVisible(true);
		
		 plot.getRangeAxis().setRange(1, 400);
		plot.getDomainAxis().setRange(2, 700);
		
		// Centraliza o eixo X no meio do chart.
		double centerYRange = Math.max(Math.abs(plot.getRangeAxis().getLowerBound()),
				Math.abs(plot.getRangeAxis().getUpperBound()));
		plot.getRangeAxis().setRange(-centerYRange, centerYRange);
		
		
		return chart;
	}
}
