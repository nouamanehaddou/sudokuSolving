package application;

import java.util.LinkedList;

// solving the sudoku using the the forward checking algorithm 
public class ForwardChecking {

	// the variable that counts the number of Iterations
	public int nombreIteration = 0;
	
	// the object that contains constraints
	public Contraints m = new Contraints();

	// constructor
	public ForwardChecking(){
	}
		
	
	// the principal Method 
	public int[][] forwardChecking(int[][] sudoku){
		int[][] sudokuResolu = sudoku;
		estValide(sudokuResolu, 0);
		return sudokuResolu;
	}


	// Method that returns a list of possible values for a cell 
    public LinkedList<Integer> GetPossibleValues(int sudoku[][], int i,int j){
            LinkedList<Integer> list = new LinkedList<Integer>();
            for(int k=1;k<=9;k++){
				if(!m.existeSurLigne(sudoku, i, k) && !m.existeSurColonne(sudoku, j, k) && !m.existeSurBloc(sudoku, i, j, k) ){
					list.add(k);
				}
            }
            return list;
        }
    
	// Method that iterate our sudoku in a recusive way
	public  boolean estValide(int sudoku[][],int position){
             
		if(position == 9*9){
			return true;
		}
		int i = position / 9;
		int j = position % 9;
		
		if( sudoku[i][j] != 0 ){
                       
			return estValide(sudoku, position+1);
		}

		for(int k : GetPossibleValues(sudoku,i,j)){
            nombreIteration++;
			sudoku[i][j] = k;
				if( estValide(sudoku, position+1) ){
					return true;
				}
		}
		sudoku[i][j] = 0;
		return false;
	}


}