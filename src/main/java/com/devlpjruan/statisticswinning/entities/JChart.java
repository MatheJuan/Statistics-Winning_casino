package com.devlpjruan.statisticswinning.entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class JChart {

	public JChart() {
		createJChart();
	}
 
//Fazer real-Time
// Implementar no MainPanel

	public static XYSeriesCollection createDataSet() {
	
		Person pessoa = new Person(0, 10000, 50);
		Random random = new Random();
		XYSeries series = new XYSeries("");

		int qtd = pessoa.getQtdJogadas();
		int win = pessoa.getVitorias();
		double cash = pessoa.getDinheiro();
		double aposta = 0;

		int x = 0;
		for (int i = 0; i < qtd && cash>0; i++) {
			 
			x = random.nextInt(2);
			 aposta = random.nextInt((int) cash);
			
			if (x >= 1) {
				win++;
				cash += aposta;
				/*System.out.println("===========");
				System.out.println("ganhou " + (cash - aposta));
				System.out.println("===========");*/

			} else if (x == 0) {
				win--;
				cash -= aposta;
			}
			/*System.out.println("===========");
			System.out.println("perdeu " + (aposta - cash));
			System.out.println("===========");*/
			series.add(i, win);
		}

		System.out.println(cash);

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		return dataset;
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
		plot.setForegroundAlpha(0.9f);
		plot.setRangeGridlinePaint(Color.RED);
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.black);
		plot.setDomainMinorGridlinesVisible(true);

		plot.getDomainAxis().setRange(0, 100);
		plot.getRangeAxis().setRange(0, 100);

		double centerYRange = Math.max(Math.abs(plot.getRangeAxis().getLowerBound()),
				Math.abs(plot.getRangeAxis().getUpperBound()));
		plot.getRangeAxis().setRange(-centerYRange, centerYRange);

		return chart;

	}

}
