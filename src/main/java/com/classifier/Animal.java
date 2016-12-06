package com.classifier;

import java.util.LinkedList; 
import java.util.List;

public class Animal {
	/* Características dos animais */
	private List<String> habitat;
	private List<String> revestimento;
	private List<String> locomocao;
	private List<String> reproducao;
	private List<String> alimentacao;
	private List<String> respiracao;
	
	/* Grupos de animais */
	private int peixes;
	private int anfibios;
	private int repteis;
	private int aves;
	private int mamiferos;
	
	/* Nome do animal, caso seja possível determiná-lo */
	private String nome;
	
	
	public Animal()
	{
		initializeAttributes();
	}

	private void initializeAttributes() {
		/* Inicializa todas as listas */
		habitat = new LinkedList<String>();
		revestimento = new LinkedList<String>();
		locomocao = new LinkedList<String>();
		reproducao = new LinkedList<String>();
		alimentacao = new LinkedList<String>();
		respiracao = new LinkedList<String>();
		
		/* Seta todos os grupos para 0 */
		zeroPeixes();
		zeroAnfibios();
		zeroRepteis();
		zeroAves();
		zeroMamiferos();
	}

	/* Métodos responsáveis pela adição de características e por verificar a existência delas */
	public boolean getHabitat(String habitat) {
		return this.habitat.contains(habitat);
	}

	public void setHabitat(String habitat) {
		this.habitat.add(habitat);
	}

	public boolean getRevestimento(String revestimento) {
		return this.revestimento.contains(revestimento);
	}

	public void setRevestimento(String revestimento) {
		this.revestimento.add(revestimento);
	}

	public boolean getLocomocao(String locomocao) {
		return this.locomocao.contains(locomocao);
	}

	public void setLocomocao(String locomocao) {
		this.locomocao.add(locomocao);
	}

	public boolean getReproducao(String reproducao) {
		return this.reproducao.contains(reproducao);
	}

	public void setReproducao(String reproducao) {
		this.reproducao.add(reproducao);
	}

	public boolean getAlimentacao(String alimentacao) {
		return this.alimentacao.contains(alimentacao);
	}

	public void setAlimentacao(String alimentacao) {
		this.alimentacao.add(alimentacao);
	}

	public boolean getRespiracao(String respiracao) {
		return this.respiracao.contains(respiracao);
	}

	public void setRespiracao(String respiracao) {
		this.respiracao.add(respiracao);
	}
	
	public void votePeixes() {
		peixes++;
	}
	
	public void voteAnfibios() {
		anfibios++;
	}
	
	public void voteRepteis() {
		repteis++;
	}
	
	public void voteAves() {
		aves++;
	}
	
	public void voteMamiferos() {
		mamiferos++;
	}
	
	public void zeroPeixes()
	{
		peixes = 0;
	}
	
	public void zeroAnfibios()
	{
		anfibios = 0;
	}
	
	public void zeroRepteis()
	{
		repteis = 0;
	}
	
	public void zeroAves()
	{
		aves = 0;
	}
	
	public void zeroMamiferos()
	{
		mamiferos = 0;
	}
	
	public String getNome()
	{
		if(nome == null)
		{
			return "Sem nome";
		}
		else
		{
			return nome;
		}
	}
	
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	
	/* Exibe os resultados finais da classificação */
	public void showResults() {
		int total = peixes + anfibios + repteis + aves + mamiferos;
		System.out.println("Peixes: " + ( (float) peixes / total) * 100 + " %");
		System.out.println("Anfíbios: " +  ( (float) anfibios / total) * 100 + " %");
		System.out.println("Répteis: " +  ( (float) repteis / total) * 100 + " %");
		System.out.println("Aves: " +  ( (float) aves / total) * 100 + " %");
		System.out.println("Mamíferos: " +  ( (float) mamiferos / total) * 100 + " %");
		
		/* Exibe o nome do animal, caso seja possível */
		if(nome != null)
		{
			System.out.println("Nome do animal: " + nome + "\n");
		}
	}

}
