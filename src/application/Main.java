package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.stage.Stage;


// The Main class
public class Main extends Application {
	
	
	InterfaceGraphique ihm = new InterfaceGraphique();
	Backtracking BT = new Backtracking();
	ForwardChecking FC = new ForwardChecking();
	MRV minRemValue = new MRV();
	
	int[][] sudokuInit =  { { 9, 0, 0, 1, 0, 0, 0, 0, 5 }, { 0, 0, 5, 0, 9, 0, 2, 0, 1 }, { 8, 0, 0, 0, 4, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 8, 0, 0, 0, 0 }, { 0, 0, 0, 7, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 2, 6, 0, 0, 9 },
			{ 2, 0, 0, 3, 0, 0, 0, 0, 6 }, { 0, 0, 0, 2, 0, 0, 9, 0, 0 }, { 0, 0, 1, 9, 0, 4, 5, 7, 0 } };
	
	@Override
	public void start(Stage primaryStage) {
		
		desactiverButtons();
		
		// lambda expression to handle the fileLoader button click
		ihm.fileLoader.setOnAction(e -> {
			Load(sudokuInit);
			ihm.initialiser(sudokuInit);
			activerButtons();
		});
		
		// lambda expression to handle the initialisation button click
		ihm.initialisation.setOnAction(e -> {
			cacherLesLabels();
			activerButtons();
			renitialiser();
			ihm.initialisation.setText("reinitialiser");
		});
		
		
		// lambda expression to handle the backTracking button click
		ihm.backTracking.setOnAction(e -> {
			afficherLesLabels();
			long start = System.currentTimeMillis();
			int[][] sudokuResolu = BT.backtrackingSimple(sudokuInit);
			ihm.resolution(sudokuResolu);
			long time = System.currentTimeMillis() - start;
			ihm.nombreIteration.setText(" Nombre d'iterations :  " + BT.nombreIteration);	
			ihm.tempsExecution.setText("Temps d'execution : " + time + " MilliS");
		});
		
		// lambda expression to handle the forwardChecking button click
		ihm.forwardChecking.setOnAction(e ->{
			afficherLesLabels();
			long start = System.currentTimeMillis();
			int[][] sudokuResolu = FC.forwardChecking(sudokuInit);
			ihm.resolution(sudokuResolu);
			long time = System.currentTimeMillis() - start;
			ihm.nombreIteration.setText(" Nombre d'iterations :  " + FC.nombreIteration);	
			ihm.tempsExecution.setText("Temps d'execution: " + time + " MilliS");
		});
		
		
		
		// lambda expression to handle the MRV button click
		ihm.MRV.setOnAction(e -> {
			afficherLesLabels();
			long start = System.currentTimeMillis();
			int[][] sudokuResolu = minRemValue.MRVSearch(sudokuInit);
			ihm.resolution(sudokuResolu);
			long time = System.currentTimeMillis() - start;
			ihm.nombreIteration.setText(" Nombre d'iterations :  " + minRemValue.nombreIteration);	
			ihm.tempsExecution.setText("Temps d'execution : " + time + " MilliS");
		});
		
	}
	


	// renitialize the sudoku with the values above
	public void renitialiser(){
		cacherLesLabels();
		int sudoku[][] = { { 9, 0, 0, 1, 0, 0, 0, 0, 5 }, { 0, 0, 5, 0, 9, 0, 2, 0, 1 }, { 8, 0, 0, 0, 4, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 8, 0, 0, 0, 0 }, { 0, 0, 0, 7, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 2, 6, 0, 0, 9 },
				{ 2, 0, 0, 3, 0, 0, 0, 0, 6 }, { 0, 0, 0, 2, 0, 0, 9, 0, 0 }, { 0, 0, 1, 9, 0, 4, 5, 7, 0 } };
		sudokuInit = sudoku;
		ihm.initialiser(sudokuInit);
	}
	
	
	// Method to activate all the buttons when the user chose one sudoku file to solve
	public void activerButtons(){
		ihm.backTracking.setDisable(false);
		ihm.forwardChecking.setDisable(false);
		ihm.MRV.setDisable(false);
	}
	
	// Method to desactivate all the buttons when no sudoku file has been selected
	public void desactiverButtons(){
		ihm.backTracking.setDisable(true);
		ihm.forwardChecking.setDisable(true);
		ihm.MRV.setDisable(true);
	}
	
	// Method for hiding the labels
	public void cacherLesLabels(){
		ihm.nombreIteration.setVisible(false);
		ihm.tempsExecution.setVisible(false);
	}
	
	// Method for showing the labels
	public void afficherLesLabels(){
		ihm.nombreIteration.setVisible(true);
		ihm.tempsExecution.setVisible(true);
	}

	
	//  Method that allows us to chose one soduku to resolve from the files in the "sudokuFiles" directory
	 public int[][] Load(int[][] sudoku){
	        
	        JFileChooser fs = new JFileChooser(new File("./src/sudokuFiles"));
	        fs.setDialogTitle("Choisir Un Fichier");
	        int result = fs.showOpenDialog(null);
	        if(result == JFileChooser.APPROVE_OPTION){
	            try {
	                File fi = fs.getSelectedFile();
	                BufferedReader br = new BufferedReader(new FileReader(fi.getPath()));
	                String s="";
	                int i=0;
	                String [] tab=null;
	                while ((s= br.readLine())!=null) {
	                    tab=s.split(",");
	                        for (int k = 0; k < 9; k++) {
	                            sudoku[i][k]=Integer.parseInt(tab[k]);
	                        }
	                    i++;
	                }
	            } catch (Exception ex) {
	                System.out.println("erroooooooooooooor de chargement de fichier");
	                JOptionPane.showMessageDialog(null, ex.getMessage());
	            }
	        }
	        return sudoku;
	    }
	
	
	//  the main method
	public static void main(String[] args) {
		launch(args);
	}
}
