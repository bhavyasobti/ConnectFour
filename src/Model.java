/**
 * This file is to be completed by you.
 *
 * @author s2058159
 */
public final class Model
{
	// ===========================================================================
	// ================================ CONSTANTS ================================
	// ===========================================================================
	// The most common version of Connect Four has 7 rows and 6 columns.
	public static final int DEFAULT_NR_ROWS = 6;
	public static final int DEFAULT_NR_COLS = 7;
	public static final int DEFAULT_NR_CONNECT = 4;
	
	// ========================================================================
	// ================================ FIELDS ================================
	// ========================================================================
	// The size of the board.
	private int nrRows;
	private int nrCols;
	char[][] board;
	int player;
	int connect;

	// =============================================================================
	// ================================ CONSTRUCTOR ================================
	// =============================================================================
	public Model()
	{
		// Initialise the board size to its default values.
		nrRows = DEFAULT_NR_ROWS;
		nrCols = DEFAULT_NR_COLS;
		board = new char[nrRows][nrCols];
		player=1;
		connect=DEFAULT_NR_CONNECT;
	}

	//========== SETTER FUNCTION ==========
	public void setDimensions(int nrRows,int nrCols,int connect)
	{
		this.nrRows=nrRows;
		this.nrCols=nrCols;
		this.connect=connect;
		board = new char[nrRows][nrCols];
	}
	
	// ====================================================================================
	// ================================ MODEL INTERACTIONS ================================
	// ====================================================================================
	public boolean isMoveValid(int move)
	{
		if(move>nrCols-1)
			return false;
		if(isColumnFull(move))
			return false;

		return true;
	}

	public boolean isPlayable(int row,int col,int discs)
	{
		//This function checks if the board dimensions entered are playable
		//Returns false if 1) the board is too small or 2) the number of discs to be connected is impractical
		if(row<=2 || col <=2)
			return false;
		else if(discs>=row||discs>=col)
			return false;
			else if(discs<=1)
				return false;
			else
		return true;
	}

	public int makeMove(int move,int player)
	{
		int i;
		//loop runs backward till it can find an empty space to make move in preferred column
		for(i=nrRows-1; i>=0; i--) {
			if (board[i][move] == 0) {
				board[i][move] = (char) (player + 48);//48 is added to numeric value so as to reach the ascii reference of required number
				break;
			}
		}
		return i;
	}

	public int makeMoveNPC()
	{
		//implementation of play against computer feature which makes random moves
		int move= (int) (Math.random()*nrCols);
		if(!isMoveValid(move))
			makeMoveNPC();
		return move;
	}

	public boolean isBoardFull(){
		int count=0;
		for(int i=0;i<=nrRows-1;i++) {
			for (int j = 0; j <=nrCols-1; j++) { //loop counts the empty spaces on the board
				if (board[i][j]==0) {
					count++;
				}
			}
		}
		if(count==0){
			return true;}
		return false;
	}

	public boolean isColumnFull(int col){
		int count=0;
		for(int i=0;i<=nrRows-1;i++){ //loop counts the empty spaces in the column
			if(board[i][col]==0){
				count++;
			}
		}
		if(count==0){
			return true; }
		return false;
	}

	public void nextPlayer(){ //switching to next player
		if(player==1)
			player=2;
		else
			player=1;
	}

	public boolean checkWin(int row,int col) {
		int num=board[row][col];
		int count=0;
		int i=col;

		//checking for x in a row
		while(i<nrCols && board[row][i] == num){//loop goes forward from current move and counts till elements are identical
			count++;
			i++;
		}
		i=col-1;
		while(i>=0 && board[row][i] == num){//similar to previous loop but going backward from current move
			count++;
			i--;
		}
		if(count == connect)
			return true;

		//checking for x in a column
		count=0;
		int j=row;
		//goes downward to check column
		while(j<nrRows && board[j][col] == num){
			count++;
			j++;
		}
		if(count == connect)
			return true;

		//checking for x in diagonal
		count=0;
		i=row;
		j=col;
		while(i<nrRows && j<nrCols && board[i][j] == num){ //checking towards bottom right
			count++;
			i++;
			j++;
		}
		i=row-1;
		j=col-1;
		while(i>=0 && j>=0 && board[i][j] == num){ //checking towards upper left
			count++;
			i--;
			j--;
		}
		if(count == connect)
			return true;

		//checking for x in other diagonal
		count=0;
		i=row;
		j=col;
		while(i<nrRows && j>=0 && board[i][j] == num){ //checking towards bottom left
			count++;
			i++;
			j--;
		}
		i=row-1;
		j=col+1;
		while(i>=0 && j<nrCols && board[i][j] == num){ // checking towards upper right
			count++;
			i--;
			j++;
		}
		if(count == connect)
			return true;

		return false;

	}

	public void reset()
	{
		//clears the board for new game
		player=1;
		for(int i=0;i<nrRows;i++){
			for(int j=0;j<nrCols;j++){
				board[i][j]=0;
			}
		}
	}
	// =========================================================================
	// ================================ GETTERS ================================
	// =========================================================================
	public int getNrRows()
	{
		return nrRows;
	}
	
	public int getNrCols()
	{
		return nrCols;
	}
}
