/**
 * This file is to be completed by you.
 *
 * @author s2058159
 */
public final class TextView
{
	public TextView()
	{
	
	}
	
	public final void displayNewGameMessage()
	{
		System.out.println("WELCOME TO CONNECT 4!");
		System.out.println("---- NEW GAME STARTED ----");
		System.out.println("Enter 1 to play on default board\nEnter 2 to give dimensions of choice");
	}

	public final void NPCMessage()
	{
		System.out.println("Enter 1 to pass and play\nEnter 2 to play against computer");
	}
	public final int askForMove(int player,int columns)
	{
		System.out.printf("Player %d Select a free column between 0 and %d: ",player,(columns-1));
		return InputUtil.readIntFromUser();
	}
	
	public final void displayBoard(Model model)
	{
		int nrRows = model.getNrRows();
		int nrCols = model.getNrCols();
		// Get your board representation.
		
		// This can be used to print a line between each row.
		// You may need to vary the length to fit your representation.
		String rowDivider = "-".repeat(4 * nrCols + 1);
		
		// A StringBuilder is used to assemble longer Strings more efficiently.
		StringBuilder sb = new StringBuilder();


		for(int i=0;i<=nrRows-1;i++){
			for(int j=0;j<=nrCols-1;j++) {
				if(model.board[i][j]==0) {
					sb.append("  " +"." + " ");
				}
				else {

					sb.append("  " + model.board[i][j] + " ");
				}
			}
			sb.append("\n"+rowDivider+"\n");
		}


		// Then print out the assembled String.
		System.out.println(sb);
	}

	public final void gameOverMessage()
	{
		System.out.println("GAME OVER");
	}

	public final void winnerMessage(int player){System.out.println("PLAYER "+ player +" WON. CONGRATULATIONS!");};

	public final String askContinue()
	{
		System.out.println("Do you want to play further?(Yes/No)");
		return InputUtil.readStringFromUser();

	}

	public final void invalidInputMessage()
	{
		System.out.println("INVALID INPUT:PLEASE ENTER VALID CHOICE");
	}

	public final void invalidDimensionsMessage(){System.out.println("INVALID DIMENSIONS: BOARD NOT PLAYABLE, PLEASE ENTER NEW SPECIFICATIONS FOR BOARD");}

	public final String playAgain()
	{
		System.out.println("Do you want to play again?(Yes/No)");
		return InputUtil.readStringFromUser();
	}

	public final int askChoice()
	{
		return InputUtil.readIntFromUser();
	}

	public final int askRows()
	{
		System.out.println("Enter number of rows");
		return InputUtil.readIntFromUser();
	}

	public final int askCols()
	{
		System.out.println("Enter number of columns");
		return InputUtil.readIntFromUser();
	}

	public final int askConnect()
	{
		System.out.println("Enter number of discs to connect");
		return InputUtil.readIntFromUser();
	}



}
