import java.util.ArrayList;
import java.util.Scanner;

public class Chess extends Game{

	private Board board;
	private int roundNumber;
	private ArrayList<Piece> alivePieces = new ArrayList<>();
	private ArrayList<Piece> deadPieces = new ArrayList<>();
	private Team white;
	private Team black;
	private String[] columnNames = new String[] {"a","b","c","d","e","f","g","h"};
	
	public Chess(Team white, Team black)
	{
		board = new Board(8,8);
		roundNumber= 0;
		this.white = white;
		this.black = black;
		//forward 1 for positive y and -1 for negative y
		this.white.setForward(1);
		this.black.setForward(-1);
		newBoard();
	}
	
	public Chess()
	{
		board = new Board(8,8);
		roundNumber = 0;
		white = new Team("white");
		black = new Team("black");
		//1 for forward direction being positive y and -1 for forward direction being negative y
		this.white.setForward(1);
		this.white.setForward(-1);
		newBoard();
	}
	
	public void newBoard()
	{
		board.freshBoard();
		nameTiles();
		alivePieces.clear();
		deadPieces.clear();
		generatePieces("white");
		generatePieces("black");
	}
	public void newGame()
	{
		newBoard();
	
		boolean win = false;
		while(!win)
		{
			win = newRound();
		}
	}
	
	public void emptyBoard()
	{
		for(Tile tile : board.getBoard())
		{
			tile.removePiece();
		}
	}

	private boolean newRound()
	{
		boolean win = false;
		roundNumber++;
		System.out.println("\t\tRound: " + roundNumber);
		System.out.println(toString());
		movePiece(white);
		System.out.println("\t\tRound: " + roundNumber);
		System.out.println(toString());
		movePiece(black);
		System.out.println("\t\tRound: " + roundNumber);
		System.out.println(toString());
		return win;
	}

	/**
	 * Prompts team to select tile to move from and then what tile to move it to
	 * @param team Team that will be making the move
	 */
	public void movePiece(Team team)
	{
		Scanner in = new Scanner(System.in);
		Tile start = new Tile();
		Tile end = new Tile();
		System.out.println(team + " make your move:\n");
		boolean done = false;
		while(!done)
		{
			System.out.print("Enter tile to move piece from: ");
			String input = in.nextLine();
			if(checkTileName(input))
			{
				start = getTile(input);
				if(!start.isEmpty())
				{
					if(start.getPiece().getTeam() == team)
					{
						if(getMovements(start).isEmpty())
						{
							System.out.println("\nError: tile \'" + start.getName() + "\' does not have any valid moves available.\n");
						}
						else
						{
							done = true;
						}
					}
					else
					{
						System.out.println("\nError: Piece in tile \'" + start.getName() + "\' is not on your team.\n");
					}
				}
				else
				{
					System.out.println("\nError: Tile selected is empty.\n");
				}
			}
		}
		
		ArrayList<Tile> movements = getMovements(start);
		done = false;
		while(!done)
		{
			String input = "";
			System.out.println();
			System.out.print("Enter tile to move to: ");
			input = in.nextLine();
			if(checkTileName(input))
			{
				end = getTile(input);
				if(movements.contains(end))
				{
					done = true;
				}
				else
				{
					System.out.println("\nError: Tile " + end.getName() + " is not a valid move\n");
				}
			}
		}
		
		movePieceFromTo(start,end);	
	}

	/**
	 * Takes string input of column(a-g) + row number
	 * @param name name of tile (ex. a4)
	 * @return Tile object with given name
	 */
	public Tile getTile(String name)
	{
		String charOne = name.substring(0,1);
		String charTwo = name.substring(1,2);
		int temp = Integer.parseInt(charTwo);
		int y = temp - 1;
		//-1 so if column name is incorrect it will not return anything
		int x = -1;
		for(int i = 0; i < board.getX(); i++)
		{
			if(columnNames[i].equals(charOne))
			{
				x = i;
			}
		}
		
		return board.getTile(x,y);
	}
	
	public void nameTiles()
	{
		for(int i = 0; i < board.getY(); i++)
		{
			for(int j = 0; j < board.getX(); j++)
			{
				int row = i + 1;
				board.getTile(j,i).setName(columnNames[j]+row);
			}
		}
	}
	
	/**
	 * Takes an ArrayList of movements and trims them to possible movements for piece in given tile
	 * @param movements a list of tiles 
	 * @param tile tile containing piece that should be moving
	 * @return an ArrayList of tiles that the piece within in the given tile can possible move to
	 */
	private ArrayList<Tile> trimMovements(ArrayList<Tile> movements, Tile tile)
	{
		return trimMovements(movements, tile.getPiece());
	}
	
	/**
	 * Takes an ArrayList of movements and trims them to possible movements for piece in given tile
	 * @param movements a list of tiles 
	 * @param piece that should be moving
	 * @return an ArrayList of tiles that the piece within in the given tile can possible move to
	 */
	private ArrayList<Tile> trimMovements (ArrayList<Tile> movements, Piece piece)
	{
		ArrayList<Tile> output = new ArrayList<>();
		for(Tile tile : movements)
		{
			if(tile.isEmpty()) 
			{
				output.add(tile);
			}
			else if(!tile.getPiece().getTeam().equals(piece.getTeam()))
			{
				output.add(tile);
			}
		}
		
		return output;
	}
	
	/**
	 * Puts piece from attackers tile into victim's tile, removes from attackers tile, adds victim to dead pieces
	 * @param attacker Tile that contains attackers piece
	 * @param victim Tile that contains victims piece
	 */
	private void capture(Tile attacker, Tile victim)
	{
		deadPieces.add(victim.getPiece());
		victim.setPiece(attacker.getPiece());
		attacker.removePiece();
	}
	
	
	/**
	 * Takes a tile with a piece in it and returns an ArrayList of all possible moves for that piece
	 * @param tile tile containing piece that is desired to move
	 * @return ArrayList of all possible moves
	 */
	public ArrayList<Tile> getMovements(Tile tile)
	{
		ArrayList<Tile> movements = new ArrayList<>();
		Piece piece = tile.getPiece();
		String[] moveList = piece.getMoveList();
		int forward = tile.getPiece().getTeam().getForward();
		int x = tile.getX();
		int y = tile.getY();
		
		for(String move : moveList)
		{
			if(move.equals("f2"))
			{
				Tile temp = board.getTile(x, y + forward * 2);
				movements.add(temp);
				
			}
			else if(move.equals("d1"))
			{
				Tile temp1 = board.getTile(x + 1, y + forward);
				Tile temp2 = board.getTile(x - 1, y + forward);
				
				if(!temp1.isEmpty())
				{
					movements.add(temp1);
				}
				if(!temp2.isEmpty()) {
					movements.add(temp1);
				}
			}
			else
			{
				movements.addAll(board.getDirection(move,tile));
			}
		}

		return movements;
	}
	
	public ArrayList<Tile> getTrimmedMovements(Tile tile)
	{
		ArrayList<Tile> movements = getMovements(tile);
		movements = trimMovements(movements, tile);	
		return movements;
	}
	

	
	public boolean checkTileName(String input)
	{
		int charTwo = -1;
		if(input.length()!=2)
		{
			System.out.println("Invalid tile name :" + input + ". Must be string length 2");
		}
		else
		{
			boolean first = false;
			for(String letter : columnNames)
			{
				if(input.substring(0,1).toLowerCase().equals(letter))
				{
					first = true;
				}
			}
			
			if(!first)
			{
				System.out.println("Invalid input for first character " + input.substring(0,1) + ". Must be a letter a-g");
			}
			else
			{
				try
				{
					charTwo = Integer.parseInt(input.substring(1,2));
					if(charTwo < 9 && charTwo > 0)
					{
						return true;
					}
					else
					{
						System.out.println("Invalid format for input " + charTwo + ". Must be an in 1-8");
					}
				}
				catch(NumberFormatException e)
				{
					System.out.println("Invalid format for input " + charTwo + ". Must be an integer.");
				}
			}
		}
		return false;
	}
	
	private void movePieceFromTo(Tile start, Tile end)
	{
		start.getPiece().setHasMoved(true);
		if(end.isEmpty())
		{
			end.setPiece(start.getPiece());
			start.removePiece();
		}
		else
		{
			capture(start,end);
		}
	}
	
	/**
	 * Creates a new set of Pieces in the correct position for the given team
	 * @param team Team to create pieces for
	 */
	private void generatePieces(String side)
	{
		int backRow, frontRow;
		Team team;
		if(side.toLowerCase().equals("white"))
		{
			backRow = 0;
			frontRow = 1;
			team = this.white;
		}
		else
		{
			backRow = 7;
			frontRow = 6;
			team = this.black;
		}
		
		//creating the back row pieces
		Queen queen = new Queen(team);
		King king = new King(team);
		Rook rook1 = new Rook(team);
		Rook rook2 = new Rook(team);
		Bishop bishop1 = new Bishop(team);
		Bishop bishop2 = new Bishop(team);
		Knight knight1 = new Knight(team);
		Knight knight2 = new Knight(team);
		alivePieces.add(queen);
		alivePieces.add(king);
		alivePieces.add(rook1);
		alivePieces.add(rook2);
		alivePieces.add(bishop1);
		alivePieces.add(bishop2);
		alivePieces.add(knight1);
		alivePieces.add(knight2);
		
		
		//setting the back row Pieces
		board.getTile(0,backRow).setPiece(rook1);
		board.getTile(7,backRow).setPiece(rook2);
		board.getTile(1,backRow).setPiece(knight1);
		board.getTile(6,backRow).setPiece(knight2);
		board.getTile(2,backRow).setPiece(bishop1);
		board.getTile(5,backRow).setPiece(bishop2);
		board.getTile(3,backRow).setPiece(queen);
		board.getTile(4,backRow).setPiece(king);
		
		//setting the front row AKA pawns
		for(int i = 0; i < board.getX(); i++)
		{
			Pawn pawn = new Pawn(team);
			alivePieces.add(pawn);
			board.getTile(i,frontRow).setPiece(pawn);
		}		
	}
	
	public String toString()
	{
		String output = "";
		
		for(int i = board.getY() - 1; i >= 0; i --)
		{
			int row = i + 1;
			output = output + row + " " + board.rowToString(i) + "\n";
		}
		
		output = output + "  ";
		
		for(int i = 0; i < board.getX(); i++)
		{
			//want this to be in the middle of each column
			output = output + " " + columnNames[i] + "  ";
		}
		return output;
	}

	/**
	 * sets the round number
	 * @param roundNumber number to set round number to
	 */
	public void setRoundNumber(int roundNumber)
	{
		this.roundNumber = roundNumber;
	}
	
	public Board getBoard()
	{
		return board;
	}
	
	public Team getWhite()
	{
		return white;
	}
	
	public void setWhite(Team team)
	{
		white = team;
	}
	
	public Team getBlack()
	{
		return black;
	}
	
	public void setBlack(Team team)
	{
		black = team;
	}
	
	public String[] getColumnNames()
	{
		return columnNames;
	}
	
	
	/**
	 * gets the current round number
	 * @return the current round number
	 */
	public int getRoundNumber()
	{
		return roundNumber;
	}
	
	
	
}
