package it.polito.tdp.dizionario.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		System.out.println(String.format("**Grafo creato** - Trovate #%d parole di lunghezza 5\n",  model.createGraph(5).size()));
		
		List<String> vicini = model.displayNeighbours("astro");
		System.out.println("Vicini di casa: " + vicini);
		
		System.out.println();
		
		System.out.println("Cerco il vertice con grado massimo...");
		System.out.println(model.findMaxDegree());
		
		System.out.println(model.trovaTuttiVicini("astro"));
		System.out.println(model.trovaTuttiVicini("astro").size());
	}

}
