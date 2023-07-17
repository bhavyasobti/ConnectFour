/**
 * This file is to be completed by you.
 *
 * @author s2058159
 */
public final class Controller {
	private final Model model;
	private final TextView view;

	public Controller(Model model, TextView view) {
		this.model = model;
		this.view = view;
	}

	public void startSession() {
		view.displayNewGameMessage();
		int choice = view.askChoice();

		switch (choice) {
			case 1: //choice to play on default board
				model.setDimensions(model.DEFAULT_NR_ROWS, model.DEFAULT_NR_COLS, model.DEFAULT_NR_CONNECT);
				break;
			case 2: //choice to enter dimensions
				while (true) {
					int r = view.askRows();
					int c = view.askCols();
					int d = view.askConnect();
					if (model.isPlayable(r, c, d)) {
						model.setDimensions(r, c, d);
						break;
					} else
						view.invalidDimensionsMessage();
				}
				break;
			default:
				view.invalidInputMessage();
				startSession();
		}

		while(true) {
			//asks if user wants t play against computer
			view.NPCMessage();
			choice = view.askChoice();
			if(choice==1||choice==2)
				break;
			else
				view.invalidInputMessage();
		}

		view.displayBoard(model);
		int move = 0;
		while(true) {
			move=view.askForMove(model.player,model.getNrCols());
			if(model.isMoveValid(move)) {
				model.makeMove(move, 1);//first move of the game
				break;
			}
			else
				view.invalidInputMessage();
		}
		view.displayBoard(model);
		model.nextPlayer();
		gameLoop(choice);
		
	}

		public void gameLoop (int comp) {
			int move = 0;
			while (true) {

				if (model.isBoardFull()) {
					endSession();
				}

				//asking user if they want to play further on every move
				String choice = view.askContinue();
				if (choice.equalsIgnoreCase("No")) {
					endSession();
				} else if (choice.equalsIgnoreCase("Yes")) {
				} else {
					view.invalidInputMessage();
					continue;
				}
                //pass and play
				if(comp==1) {
					move = view.askForMove(model.player,model.getNrCols());
					if (!model.isMoveValid(move)) {
						view.invalidInputMessage();
						continue;
					}
				}
				//against computer
				if(comp==2)
				{
					if(model.player==1) {
						move = view.askForMove(model.player, model.getNrCols());
						if (!model.isMoveValid(move)) {
							view.invalidInputMessage();
							continue;
						}
					}
						if(model.player==2)
							move=model.makeMoveNPC();

				}
				int row = model.makeMove(move, model.player);
				view.displayBoard(model);
				if (model.checkWin(row, move)) {
					view.winnerMessage(model.player);
					endSession();
				}

				model.nextPlayer();

			}
		}

		public void endSession()
		{
			view.gameOverMessage();
			String choice = view.playAgain();
			if (choice.equalsIgnoreCase("no"))
				System.exit(0);
			else if (choice.equalsIgnoreCase("yes")) {
				model.reset();
				startSession();
			} else {
				view.invalidInputMessage();
				endSession();
			}


		}
	}

