package com.devlpjruan.statisticswinning.entities;

import java.math.BigDecimal;

public class Person {

	private int vitorias;
	private BigDecimal dinheiro;
	private int derrotas;
	private int partidas;
	
	public Person() {
	}
	public Person(int vitorias, String dinheiro, int partidas, int derrotas) {
		this.derrotas= derrotas;
		this.vitorias = vitorias;
		this.dinheiro = new BigDecimal(dinheiro);
		this.partidas = partidas;
	}
	
	public int getVitorias() {
		return vitorias;
	}
	public void setVitorias(int vitorias) {
		this.vitorias = vitorias;
	}
	public BigDecimal getDinheiro() {
		return dinheiro;
	}
	public void setDinheiro(BigDecimal dinheiro) {
		this.dinheiro = dinheiro;
	}
	public int getpartidas() {
		return partidas;
	}
	public void setpartidas(int partidas) {
		this.partidas = partidas;
	}
	public int getDerrotas() {
		return derrotas;
	}
	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}
	public int getPartidas() {
		return partidas;
	}
	public void setPartidas(int partidas) {
		this.partidas = partidas;
	}
	
}
