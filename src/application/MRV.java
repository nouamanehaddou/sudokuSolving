package application;

import java.util.LinkedList;


public class MRV {
	
	// the variable that counts the number of Iterations
	public int nombreIteration=0;
	// the object that contains constraints
	Contraints m = new Contraints();


	// constructor
	public MRV(){
	}

	// the principal Method 
	public int[][] MRVSearch(int[][] sudoku){
		estValide(sudoku);
		return sudoku;
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


	// Method that iterate our sudoku
	public  boolean estValide(int sudoku[][]){
		int i=0,j=0;
		int min=10;
		int tmp;
		for (int k = 0; k < 9; k++) {
			for (int l = 0; l < 9; l++) {
				nombreIteration++;
				if(sudoku[k][l]==0){
					tmp=GetPossibleValues(sudoku, k, l).size();
					if(tmp < min){
						min=tmp;
						i=k;j=l;
					}
				}
			}
		}
		if(min==10){
			return true;
		}
		for(int k : GetPossibleValues(sudoku,i,j)){
			nombreIteration++;
			sudoku[i][j] = k;
			if( estValide(sudoku) ){
				return true;
			}
			else 
				sudoku[i][j] = 0;
		}
		sudoku[i][j] = 0;
		return false;
	}

}
