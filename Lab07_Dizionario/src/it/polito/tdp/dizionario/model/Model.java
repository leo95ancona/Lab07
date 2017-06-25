package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Multigraph;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionario.db.WordDAO;

public class Model {
	
	WordDAO wordDao = new WordDAO();
	List<String> parole = new ArrayList<String>();
	UndirectedGraph<String, DefaultEdge> grafo = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
	HashSet <String> vicini;
	

	public List<String> createGraph(int numeroLettere) {

		parole = wordDao.getAllWordsFixedLength(numeroLettere);

		for (String s : parole){
			for (int j=0; j<parole.size(); j++){
				int cont=0;
				String parola = parole.get(j);
				if (!s.equals(parola)){
					char a[] = s.toCharArray();
					char b[] = parola.toCharArray();
				
					for (int i=0 ; i<s.length(); i++){
						if (a[i]!=b[i]){
							cont++;
						}
					}
					if (cont==1){
						grafo.addVertex(s);
						grafo.addVertex(parola);
						grafo.addEdge(s, parola);
					}
				}
			}
		}
		System.out.println(grafo);
		return parole;
	}

	public List<String> displayNeighbours(String parolaInserita) {
		/*UndirectedGraph<String, DefaultEdge> grafoParola = new Multigraph<String, DefaultEdge>(DefaultEdge.class);
		
		List<String> vicini = new ArrayList<String>();
		
		
		for (String s : parole){
			int cont=0;
			if (!s.equals(parolaInserita)){
				char a[] = s.toCharArray();
				char b[] = parolaInserita.toCharArray();
				
				for (int i=0 ; i<parolaInserita.length(); i++){
					if (a[i]!=b[i]){
						cont++;
					}
				}
				if (cont==1){
					grafoParola.addVertex(s);
					grafoParola.addVertex(parolaInserita);
					grafoParola.addEdge(parolaInserita,s);
					vicini.add(s);
				}
			}
		}
		System.out.println(grafoParola);
		*/
		List<String> lista = Graphs.neighborListOf(grafo, parolaInserita);
		return lista;
	}

	public String findMaxDegree() {
		int best=0;
		String verticeBest=null;
		for (String s : grafo.vertexSet()){
			int cont=0;
			cont = grafo.degreeOf(s);
			/*
			for (DefaultEdge arc : grafo.edgesOf(s)){
				if (grafo.getEdgeSource(arc)==s){
					cont++;
				}
				//System.out.println(cont);
				if (cont>best){
					best=cont;
					verticeBest = s;
				}
			}
			*/
			if (cont>best){
				best=cont;
				verticeBest = s;
			}
		}
		
		return "Il nodo --"+verticeBest+"-- ha il grado massimo del grafo: "+best+" degree";
	}

	public List<String> getParole() {
		return parole;
	}
	
	
	public UndirectedGraph<String, DefaultEdge> getGrafo() {
		return grafo;
	}

	public void resetModel(){
		this.getParole().clear();
		
	}
	
	public HashSet <String>  trovaTuttiVicini(String parolaInserita){
		
		vicini =  new HashSet<String>();
		
		
		recursive(vicini,parolaInserita);
		
		
		return vicini;

	}

	private void recursive(HashSet<String> vicini, String parola) {
		
		
		for (String s : Graphs.neighborListOf(grafo, parola)){
			
			if (vicini.add(s)){
				recursive(vicini,s);
			}
		}
		
	}

}
