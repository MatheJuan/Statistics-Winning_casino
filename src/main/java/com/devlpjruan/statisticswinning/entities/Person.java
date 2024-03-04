package com.devlpjruan.statisticswinning.entities;

import java.math.BigDecimal;
import java.util.Objects;

public class Person {

	private int vitorias;
	private BigDecimal dinheiro;
	private int derrotas;
	private int partidas;
	private int sorte;

	public Person() {}

	public Person(String dinheiro, int sorte) {
		this.dinheiro = new BigDecimal(dinheiro).setScale(2);
		this.sorte = sorte;
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
		 this.dinheiro.add(dinheiro);
	 
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

	public int getSorte() {
		return sorte;
	}

	public void setSorte(int sorte) {
		this.sorte = sorte;
	}

	@Override
	public String toString() {
		return "Person [vitorias=" + vitorias + ", dinheiro=" + dinheiro + ", derrotas=" + derrotas + ", partidas="
				+ partidas + ", sorte=" + sorte + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(derrotas, dinheiro, partidas, sorte, vitorias);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return derrotas == other.derrotas && Objects.equals(dinheiro, other.dinheiro) && partidas == other.partidas
				&& sorte == other.sorte && vitorias == other.vitorias;
	}
	
}
