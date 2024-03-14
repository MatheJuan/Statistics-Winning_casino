package com.devlpjruan.statisticswinning.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import javax.swing.JLabel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import com.devlpjruan.statisticswinning.entities.Person;

public class JChart {

	public JChart() {
		createJChart();
	}

	public static Random random = new Random();

	public static XYSeriesCollection createDataSet() {
		XYSeries series = new XYSeries("");
		series.add(0, 0);

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		return dataset;
	}

	// Responsavel pela atualização e randomizacao da coordenada X
	public void updateChart(JFreeChart freeChart, Person person, JLabel label1, JLabel label2, JLabel label3,
			JLabel label4, JLabel label5,JLabel label6) {

		XYSeriesCollection dataset = (XYSeriesCollection) freeChart.getXYPlot().getDataset();
		XYSeries series = dataset.getSeries(0);

		int item = series.getItemCount();
		double eixoX = (double) series.getX(item - 1);
		double eixoY = (double) series.getY(item - 1);
		double randomVar = random.nextDouble();
		double luck = person.getSorte();
		XYPlot plot = freeChart.getXYPlot();
		double xBound = plot.getDomainAxis().getUpperBound();
		double yBound = plot.getRangeAxis().getUpperBound();
		double saldoPlayer = person.getDinheiro().doubleValue();
		BigDecimal aposta = person.getAposta();	
		//							10/4= 2,5
		double margemSegurança = saldoPlayer/4;
		
		if(margemSegurança<2.00) {
			margemSegurança=3.00;
		}										
		if(aposta.intValue()==0 ) {
			aposta = BigDecimal.valueOf(random.nextDouble()*margemSegurança).setScale(2, RoundingMode.UP);
		}
		
		BigDecimal saldoCassino = person.getLucroCassino();
		// Define se o grafico irá pra cima ou para baixo.
		if (randomVar <= (luck / 100)) { // Player ganha
			person.setpartidas(person.getpartidas() + 1);
			person.setVitorias(person.getVitorias() + 1);
			person.addDinheiro(aposta);
			person.setLucroCassino(saldoCassino.subtract(aposta));
			series.add(eixoX + 1, eixoY + 3);
			
	
		} else {// Cassino ganha

			person.setpartidas(person.getpartidas() + 1);
			person.setDerrotas(person.getDerrotas() + 1);
			person.subtractDinheiro(aposta);
			person.setLucroCassino(saldoCassino.add(aposta));
			series.add(eixoX + 1, eixoY - 3);
		
		}

		// Ajuste dinamico dos eixos apos chegar o limite
		if (eixoY > yBound) {
			plot.getRangeAxis().setRange(1, eixoY);
		}
		if (eixoX > xBound) {
			plot.getDomainAxis().setRange(2, eixoX);
		}
		label1.setText("Vitorias: " + person.getVitorias());
		label2.setText("Derrotas: " + person.getDerrotas());
		label3.setText("Partidas: " + person.getpartidas());
		label4.setText("Lucro cassino " + person.getLucroCassino());
		label5.setText("Dinheiro: " + person.getDinheiro());
		label6.setText("Sorte: "+ person.getSorte());
	}

	public JFreeChart createJChart() {
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
		plot.setDomainGridlinePaint(Color.black);//
		plot.setDomainMinorGridlinesVisible(true);

		plot.getRangeAxis().setRange(1, 400);
		plot.getDomainAxis().setRange(2, 600);

		// Centraliza o eixo no meio do chart.
		double centerYRange = Math.max(Math.abs(plot.getRangeAxis().getLowerBound()),
				Math.abs(plot.getRangeAxis().getUpperBound()));
		plot.getRangeAxis().setRange(-centerYRange, centerYRange);

		return chart;
	}
}
