package com.devlpjruan.statisticswinning.entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
	 
	static Person pessoa = new Person(0, "100.0" , 0, 0);
	static BigDecimal money = pessoa.getDinheiro().setScale(2);
	static int partidas = pessoa.getpartidas();
	static int vitorias = pessoa.getVitorias();
	static int derrotas = pessoa.getDerrotas();
	 
	public static double luck = 50.0;
	public static Random random = new Random();
 
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
		
		BigDecimal aposta = BigDecimal.valueOf(random.nextDouble()).setScale(2, RoundingMode.HALF_UP);//xxxxxx
		BigDecimal lucroCassino= new BigDecimal("1.00");
		//aposta.setScale(2);
		XYPlot plot = freeChart.getXYPlot();
		double xBound = plot.getDomainAxis().getUpperBound();
		double yBound = plot.getRangeAxis().getUpperBound();
		
		//Define se o grafico irá pra cima ou para baixo.
		if(constY<=(luck/100)) {
			partidas++;
			vitorias++;
			lucroCassino= lucroCassino.add(aposta);
			System.out.println("Ganhou: "+aposta);
			System.out.println("Total de partidas: "+ partidas);
			System.out.println("Lucro do cassino: "+ lucroCassino);
			series.add(eixoX+1, eixoY+1);
		}else{
			partidas++;
			derrotas++;
			lucroCassino= lucroCassino.subtract(aposta);
			System.out.println("Perdeu: -"+aposta);
			System.out.println("Total de partidas: "+ partidas);
			System.out.println("Lucro do cassino: "+ lucroCassino);
			 series.add(eixoX+1, eixoY-1);
		}
		//Ajuste dinamico dos eixos apos chegar o limite
		if (eixoY > yBound) {
		    plot.getRangeAxis().setRange(1, eixoY);
		}
		if (eixoX > xBound) {
		    plot.getDomainAxis().setRange(2, eixoX); 
		}
	}
	 
	public static JFreeChart createJChart() {
		XYSeriesCollection dataset = createDataSet();

		JFreeChart chart = ChartFactory.createXYLineChart("Probabilities", "", "", dataset);

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
		
		// Centraliza o eixo no meio do chart.
		double centerYRange = Math.max(Math.abs(plot.getRangeAxis().getLowerBound()),
				Math.abs(plot.getRangeAxis().getUpperBound()));
		plot.getRangeAxis().setRange(-centerYRange, centerYRange);
		
		
		return chart;
	}
}
