import java.util.Random;

public class SudokuBoard {
	SudokuCell[][] board;
	
	SudokuBoard() {
		board = new SudokuCell[9][9];
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				board[x][y] = new SudokuCell(new Coordinate(x, y));
			}
		}
		printBoard();
	}

	void printBoard() {
		for (SudokuCell[] row : board) {
				for (SudokuCell cell : row) {
				System.out.print(cell.value + " ");
			}
			System.out.println();
		}
	}

	public static void generate(String[] args) {
		// write your code here
		Random rand = new Random();
		//r.ints()
		int[][] board;
		//   int[][] ans;
		boolean repeated=false;
		board= new int[9][9];
		for(int i=0;i<9;i++)
		{
			board[0][i]=rand.nextInt(9) + 1;;
			repeated=false;
			while(true)
			{
				if(repeated==true)
				{
					board[0][i]=rand.nextInt(9) + 1;
				}
				repeated=false;
				for(int j=0;j<i;j++)
				{
					if(board[0][i]==board[0][j])
					{
						repeated=true;
						break;
					}
				}
				if(repeated==false)
				{
					break;
				}
			}
		}

		for(int i=1;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				board[i][j]=board[i-1][j]+3;
				if(board[i][j]>9)
				{
					board[i][j]-=9;
				}
			}
		}
        /*for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                ans[i][j]=board[i][j];
            }
        }*/
		for(int i=0;i<30;i++)
		{
			board[rand.nextInt(8) + 0][rand.nextInt(8) + 0]=0;
		}
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
}
