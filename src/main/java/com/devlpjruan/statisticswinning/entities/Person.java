package com.devlpjruan.statisticswinning.entities;

public class Person {

	private int vitorias;
	private double dinheiro;
	private int qtdJogadas;
	
	public Person() {
	}
	public Person(int vitorias, double dinheiro, int qtdJogadas) {
		super();
		this.vitorias = vitorias;
		this.dinheiro = dinheiro;
		this.qtdJogadas = qtdJogadas;
	}
	
	public int getVitorias() {
		return vitorias;
	}
	public void setVitorias(int vitorias) {
		this.vitorias = vitorias;
	}
	public double getDinheiro() {
		return dinheiro;
	}
	public void setDinheiro(double dinheiro) {
		this.dinheiro = dinheiro;
	}
	public int getQtdJogadas() {
		return qtdJogadas;
	}
	public void setQtdJogadas(int qtdJogadas) {
		this.qtdJogadas = qtdJogadas;
	}
	
}
