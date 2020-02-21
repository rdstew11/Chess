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
		this.black.setForward(-1);
		this.white.setForward(1);
		newBoard();
	}
	
	public Chess()
	{
		board = new Board(8,8);
		roundNumber = 0;
		white = new Team("white");
		black = new Team("black");
		//1 for forward direction being positive y and -1 for forward direction being negative y
		this.black.setForward(-1);
		this.white.setForward(1);
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
		play();
	}
	
	public void play()
	{
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
		movePiece(white);
		movePiece(black);
		return win;
	}

	/**
	 * Prompts team to select tile to move from and then what tile to move it to
	 * @param team Team that will be making the move
	 */
	
	public void movePiece(Team team)
	{
		Scanner in = new Scanner(System.in);
		Tile start = null;
		Tile end = null;
		System.out.println(toString());
		boolean done = false;
		while(!done)
		{
			System.out.print("[" + team + "] enter tile to move piece from: ");
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
							System.out.println(toString());
							System.out.println("\nError: tile \'" + start.getName() + "\' does not have any valid moves available.\n");
						}
						else
						{
							done = true;
						}
					}
					else
					{
						System.out.println(toString());
						System.out.println("\nError: Piece in tile \'" + start.getName() + "\' is not on your team.\n");
					}
				}
				else
				{
					System.out.println(toString());
					System.out.println("\nError: Tile selected is empty.\n");
				}
			}
		}
		
		ArrayList<Tile> movements = getMovements(start);
		done = false;
		System.out.println(toString());
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
					System.out.println(toString());
					System.out.println("\nError: Tile " + end.getName() + " is not a valid move\n");
				}
			}
		}
		
		if(checkCastle(start,end))
		{
			castle(start,end);
		}
		else 
		{
			movePieceFromTo(start,end);
		}
	}
	
	/**
	 * Checks to see if two pieces within the two tiles are capable of castling each other
	 * @param one King or Rook
	 * @param two King or Rook
	 * @return true if its possible, false if not
	 */
	public boolean checkCastle(Tile one, Tile two)
	{
		ArrayList<Tile> east = board.getE(one);
		ArrayList<Tile> west = board.getW(one);
		
		if(one == two)
		{
			return false;
		}	
		
		boolean oneHasCastle = false;
		boolean twoHasCastle = false;
		if(!one.isEmpty() && !two.isEmpty())
		{
			if(one.getPiece().getType().equals(two.getPiece().getType()))
			{
				return false;
			}
			for(String move : one.getPiece().getMoveList())
			{
				if(move.equals("castle"))
				{
					oneHasCastle = true;
				}
			}
			
			for(String move : two.getPiece().getMoveList())
			{
				if(move.equals("castle"))
				{
					twoHasCastle = true;
				}
			}
		}
		if(oneHasCastle && twoHasCastle)
		{
			for(Tile tile : east)
			{
				if(!tile.isEmpty())
				{
					if(tile == two)
					{
						return true;
					}
					else
					{
						break;
					}
				}
			}
			for(Tile tile : west)
			{
				if(!tile.isEmpty())
				{
					if(tile == two)
					{
						return true;
					}
					else
					{
						break;
					}
				}
			}
		}
		return false;
	}
	

	
	/**
	 * looks for the Team's king on the board
	 * @param team Team who's king to look for
	 * @return the tile containing the Team's king
	 */
	public Tile findKing(Team team)
	{
		ArrayList<Tile> teamTiles = getTeamTiles(team);
		for(Tile tile : teamTiles)
		{
			Piece piece = tile.getPiece();
			if(piece.getType().equals("king"))
			{
				return tile;
			}
		}
		return null;
	}
	
	/**
	 * Gets all the tiles of a given team containing a piece that has a possible movement
	 * @param team Team who's pieces you want to check for
	 * @return ArrayList<Tile> containing tiles with team's pieces that can make a movement
	 */
	public ArrayList<Tile> getMoveableTiles(Team team)
	{
		ArrayList<Tile> moveable = new ArrayList<>();
		ArrayList<Tile> tiles = getTeamTiles(team);
		for(Tile tile : tiles)
		{
			ArrayList<Tile> movements = getMovements(tile);
			if(!movements.isEmpty())
			{
				moveable.add(tile);
			}
		}
		return moveable;
	}
	
	/**
	 * Gets all the tiles containing a piece of the given team 
	 * @param team Team you want to find their tiles for
	 * @return ArrayList<Tile> containing every tile that has a piece of the given team
	 */
	public ArrayList<Tile> getTeamTiles(Team team)
	{
		ArrayList<Tile> tiles = new ArrayList<>();
		for(Tile tile : board.getBoard()) 
		{
			if(!tile.isEmpty()) 
			{
				if(tile.getPiece().getTeam() == team)
				{
					tiles.add(tile);
				}
			}
		}
		return tiles;
	}
	
	/**
	 * Retrieves the team of the opponent
	 * @param team Team opposite of the one you want to get
	 * @return opponents Team
	 */
	public Team getOpponent(Team team)
	{
		Team opponent = null;
		if(team == white)
		{
			opponent = black;
		}
		else
		{
			opponent = white;
		}
		return opponent;
	}
	
	public void checkCheckMate(Team team)
	{
		
	}
	
	/**
	 * Returns true if team's king is in check
	 * @param team Team potentially in check
	 * @return true if in check, false if not in check
	 */
	public boolean checkCheck(Team team, Tile tile)
	{
		Team opponent = getOpponent(team);
		ArrayList<Tile> moveableTiles = getMoveableTiles(opponent);
		for(Tile tempTile : moveableTiles)
		{
			ArrayList<Tile> movements = getMovements(tempTile);
			if(movements.contains(tile)) 
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Takes string input of column(a-g) + row number
	 * @param name name of tile (ex. a4)
	 * @return Tile object with given name
	 */
	public Tile getTile (String name)
	{
		//identifies x
		String charOne = name.substring(0,1);
		//identifies y
		String charTwo = name.substring(1,2);
		int temp = Integer.parseInt(charTwo);
		int y = temp - 1;
		//-1 so if column name is incorrect it will not return anything
		int x = -1;
		// i = y coordinate
		for(int i = 0; i < board.getX(); i++)
		{
			if(columnNames[i].equals(charOne))
			{
				x = i;
			}
		}
		
		return board.getTile(x,y);
	}
	
	/**
	 * Takes the board and assigns each tile a name like "a1"
	 */
	public void nameTiles()
	{
		for(int i = 0; i < board.getY(); i++)
		{
			for(int j = 0; j < board.getX(); j++)
			{
				int y = i + 1;
				String x = columnNames[j];
				board.getTile(j,i).setName(x+y);
			}
		}
	}
	
	/**
	 * Takes an ArrayList of movements and eliminates any tiles that contain alike teams
	 * @param movements a list of tiles 
	 * @param tile tile containing piece that should be moving
	 * @return an ArrayList of tiles that the piece within in the given tile can possible move to
	 */
	private ArrayList<Tile> removeLikeTeams(ArrayList<Tile> movements, Tile tile)
	{
		ArrayList<Tile> output = new ArrayList<>();
		Piece piece = tile.getPiece();
		for(Tile tempTile : movements)
		{
			if(tempTile.isEmpty()) 
			{
				output.add(tempTile);
			}
			else if(tempTile.getPiece().getTeam() != piece.getTeam())
			{
				output.add(tempTile);
			}
			else if(tempTile.getPiece().getTeam() == piece.getTeam() && checkCastle(tile,tempTile))
			{
				output.add(tempTile);
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
	
	public void castle(Tile one, Tile two)
	{
		Tile kingTile = null;
		Tile rookTile = null;
		
		//identify which tile holds king and which holds rook
		if(one.getPiece().getType().equals("king") && two.getPiece().getType().equals("rook"))
		{
			kingTile = one;
			rookTile = two;
		}
		else if(one.getPiece().getType().equals("rook") && two.getPiece().getType().equals("king"))
		{
			kingTile = two;
			rookTile = one;
		}
		
		Piece king = kingTile.getPiece();
		Piece rook = rookTile.getPiece();
		int kingX = kingTile.getX();
		int rookX = rookTile.getX();
		int y = kingTile.getY();
		String dir = "";
		
		if(rookX > kingX)
		{
			dir = "east";
		}
		else if(rookX < kingX)
		{
			dir = "west";
		}
		
		dir = dir.toLowerCase();
		int x = 0;
		if(dir.equals("east"))
		{
			x = 1;
		}
		else if(dir.equals("west"))
		{
			x = -1;
		}
		

		Tile kingNewTile = board.getTile(kingX + 2 * x, y);
		Tile rookNewTile = board.getTile(kingNewTile.getX() - x, y);
		kingNewTile.setPiece(king);
		kingTile.removePiece();
		rookNewTile.setPiece(rook);
		rookTile.removePiece();
		
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
		Team team = piece.getTeam();
		String[] moveList = piece.getMoveList();
		int forward = team.getForward();
		int x = tile.getX();
		int y = tile.getY();
		
		for(String move : moveList)
		{
			//pawn forward 2
			if(move.equals("pf2"))
			{
				if(board.getTile(x,y + forward).isEmpty())
				{
					Tile temp = board.getTile(x, y + forward * 2);
					movements.add(temp);
				}
			}
			//pawn attack
			else if(move.equals("pa"))
			{				
				if(board.checkInbounds(x + 1, y + forward))
				{
					Tile temp = board.getTile(x + 1, y + forward);
					if(!temp.isEmpty())
					{
						movements.add(temp);
					}
				}
				if(board.checkInbounds(x - 1, y + forward))
				{
					Tile temp = board.getTile(x - 1, y + forward);
					if(!temp.isEmpty()) 
					{
						movements.add(temp);
					}
				}
			}
			else if(move.equals("castle"))
			{
				ArrayList<Tile> row = board.getRow(y);
				for(Tile tempTile : row)
				{
					if(checkCastle(tile,tempTile))
					{
						movements.add(tempTile);
					}
				}
			}
			//pawn forward
			else if(move.equals("pf"))
			{
				Tile temp = board.getTile(x, y + forward);
				if(temp.isEmpty()) 
				{
					movements.add(temp);
				}
			}
			else if(move.equals("a"))
			{
				movements.addAll(board.getDirection(move,tile));
			}
			else if(move.equals("r"))
			{
				ArrayList<Tile> east = board.getE(tile);
				ArrayList<Tile> west = board.getW(tile);
				movements.addAll(removeJumps(east, tile));
				movements.addAll(removeJumps(west,tile));
			}
			else if(move.equals("c"))
			{
				ArrayList<Tile> north = board.getN(tile);
				ArrayList<Tile> south =	board.getS(tile);
				movements.addAll(removeJumps(north,tile));
				movements.addAll(removeJumps(south,tile));
				
			}
			else if(move.equals("L"))
			{
				/**
				 * move: [ ][X][ ][X][ ]
				 * 		 [X][ ][ ][ ][X]
				 * 		 [ ][ ][P][ ][ ]
				 * 		 [X][ ][ ][ ][X]
				 * 		 [ ][X][ ][X][ ]
				 * 
				 * X:	 [  ][-1][  ][+1][  ]
				 *Start->[-2][  ][  ][  ][+2]
				 * 		 [  ][  ][XX][  ][  ]
				 * 		 [-2][  ][  ][  ][+2]
				 * 		 [  ][-1][  ][+1][  ]
				 * 
				 * Y:	 [  ][+2][  ][+2][  ]
				 *Start->[+1][  ][  ][  ][+1]
				 * 		 [  ][  ][XX][  ][  ]
				 * 		 [-1][  ][  ][  ][-1]
				 * 		 [  ][-2][  ][-2][  ]
				 */
				
				int[] xIncrement = new int[] {-2,-1,1,2,2,1,-1,-2};
				int[] yIncrement = new int[] {1,2,2,1,-1,-2,-2,-1};
				
				for(int i = 0; i < 8; i++)
				{
					int newX = x + xIncrement[i];
					int newY = y + yIncrement[i];
					if(board.checkInbounds(newX,newY))
					{
						Tile temp = board.getTile(newX,newY);
						movements.add(temp);
					}
				}
			}
			else if(move.equals("d"))
			{
				ArrayList<Tile> ne = board.getDirection("ne",tile);
				ArrayList<Tile> nw = board.getDirection("nw",tile);
				ArrayList<Tile> se = board.getDirection("se",tile);
				ArrayList<Tile> sw= board.getDirection("sw",tile);
				
				movements.addAll(removeJumps(ne,tile));
				movements.addAll(removeJumps(nw,tile));
				movements.addAll(removeJumps(se,tile));
				movements.addAll(removeJumps(sw,tile));
				
			}
		}
		
		if(tile.getPiece().getType().equals("king"))
		{
			ArrayList<Tile> tempList = new ArrayList<>();
			for(Tile temp : movements)
			{
				if(!checkCheck(team,temp))
				{
					tempList.add(temp);
				}
			}
			movements = tempList;
		}
		movements = removeLikeTeams(movements, tile);
		return movements;
	}
	
	/**
	 * Takes a linear list of moves and adds each tile until it comes to an unpassable tile
	 * @param list linear list of moves (tiles must be in order)
	 * @param origin location of start tile
	 * @return
	 */
	public ArrayList<Tile> removeJumps(ArrayList<Tile> list, Tile origin)
	{
		ArrayList<Tile> noJumps = new ArrayList<>();
		Piece piece = origin.getPiece();
		
		for(Tile tile : list)
		{
			if(tile.isEmpty()) 
			{
				noJumps.add(tile);
			}
			else if(tile.getPiece().getTeam() != piece.getTeam())
			{
				noJumps.add(tile);
				break;
			}
			else
			{
				break;
			}
		}
		
		return noJumps;
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
	
	public void upgradePawn(Tile tile)
	{
		Team team = tile.getPiece().getTeam();
		Queen queen = new Queen(team);
		tile.setPiece(queen);
	}
	
	public String toString()
	{
		String output = "\t    Round: " + roundNumber + "\n";
		
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
