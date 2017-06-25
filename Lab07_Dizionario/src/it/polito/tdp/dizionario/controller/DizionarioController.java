package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.dizionario.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {
	
	Model model;

	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private TextArea txtResult;
	@FXML
	private TextField inputNumeroLettere;
	@FXML
	private TextField inputParola;
	@FXML
	private Button btnGeneraGrafo;
	@FXML
	private Button btnTrovaVicini;
	@FXML
	private Button btnTrovaGradoMax;
	@FXML
	private Button btnTTT;


	@FXML
	void doReset(ActionEvent event) {
		txtResult.setText("Reset!");
		txtResult.clear();
		model.resetModel();
		inputNumeroLettere.clear();
		inputParola.clear();
	}

	@FXML
	void doGeneraGrafo(ActionEvent event) {

		try {
			String risultato=null;
			int numero = Integer.parseInt(inputNumeroLettere.getText());
			txtResult.appendText(model.createGraph(numero).toString());
			if (txtResult!=null){
				txtResult.appendText("\n**GENERATO GRAFO CORRETTAMENTE\n - Trovate "+model.createGraph(numero).size()+" parole con questa lunghezza\n");
			}
			
		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void doTrovaGradoMax(ActionEvent event) {
		
		try {
			txtResult.appendText("\nCerco il vertice con grado massimo...  \n");
			txtResult.appendText(model.findMaxDegree());
			if (model.findMaxDegree()==null){
				txtResult.appendText("\nERRORE CARICAMENTO GRAFO\n");
			}else{
				txtResult.appendText("\nGRADO TROVATO CORRETTAMENTE\n");
			}

		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void doTrovaVicini(ActionEvent event) {
		
		try {
				txtResult.appendText("\nVicini: \n");
				txtResult.appendText(model.displayNeighbours(inputParola.getText()).toString());
				if (model.displayNeighbours(inputParola.getText())==null){
					txtResult.appendText("\nNON TROVAT0 NESSUN VICINO\n");
				}else{
					txtResult.appendText("\nVICINI TROVATI CORRETTAMENTE\n");
				}
			

		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}
	@FXML
    void doTTT(ActionEvent event) {
		
		try {
			txtResult.appendText("\nTutti i vicini: \n");
			txtResult.appendText(model.trovaTuttiVicini(inputParola.getText()).toString());
			
			if (model.trovaTuttiVicini(inputParola.getText())==null){
				txtResult.appendText("\nNON TROVAT0 NESSUN VICINO\n");
			}else{
				txtResult.appendText("\nVICINI TROVATI CORRETTAMENTE\nNUMERO PAROLE TROVATE "+model.trovaTuttiVicini(inputParola.getText()).size()+"\n");
			}
		

	} catch (RuntimeException re) {
		txtResult.setText(re.getMessage());
	}
		
    }

	@FXML
	void initialize() {
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputNumeroLettere != null : "fx:id=\"inputNumeroLettere\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputParola != null : "fx:id=\"inputParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaGradoMax != null : "fx:id=\"btnTrovaTutti\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTTT != null : "fx:id=\"btnTTT\" was not injected: check your FXML file 'Dizionario.fxml'.";
	}
	
	

	public void setModel(Model model) {
		this.model=model;
		
	}
}