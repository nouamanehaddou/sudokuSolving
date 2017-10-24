package application;


//  class that contains all the constraints to respect in our sudoku
public class Contraints {



	// Method for testing if a value exsist in the line "i"
	public  boolean existeSurLigne(int[][] sudoku, int i, int numero) {
		for (int j = 0; j < sudoku[i].length; j++) {
			if (sudoku[i][j] == numero) {
				return true;
			}
		}
		return false;
	}


	// Method for testing if a value exsist in the column "j"
	public  boolean existeSurColonne(int sudoku[][], int j, int numero) {
		for (int i = 0; i < sudoku.length; i++) {
			if (sudoku[i][j] == numero) {
				return true;
			}
		}
		return false;
	}

	// Method for testing if a value exsist in the bloc "i,j"
	public  boolean existeSurBloc(int sudoku[][], int i, int j, int numero) {
		int sudokuBloc = (int) Math.sqrt(sudoku.length);
		int iBloc = sudokuBloc * (i / sudokuBloc);
		int jBloc = sudokuBloc * (j / sudokuBloc);

		for (i = iBloc; i < iBloc + sudokuBloc; i++) {
			for (j = jBloc; j < jBloc + sudokuBloc; j++) {
				if (sudoku[i][j] == numero) {
					return true;
				}
			}
		}
		return false;

	}
	
}
