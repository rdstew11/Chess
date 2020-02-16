import java.util.ArrayList;

public class Chess extends Game{

	private Board board;
	private int nRound;
	
	public Chess()
	{
		board = new Board(10,10);
		nRound = 0;
	}
	
	/**
	 * Moves piece into a new tile
	 * @param piece desired piece to move
	 * @param tile desired end location to move to
	 * @return true if it sucessfully moved the piece and false if it did not
	 */

	
	private ArrayList<Tile> trimMovements(ArrayList<Tile> movements, Tile tile)
	{
		return trimMovements(movements, tile.getPiece());
	}
	
	private ArrayList<Tile> trimMovements (ArrayList<Tile> movements, Piece piece)
	{
		ArrayList<Tile> output = new ArrayList<>();
		for(Tile tile : movements)
		{
			if(tile.checkEmpty()) 
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
	
	private void capture(Piece attacker, Tile tile)
	{
		board.getDeadPieces().add(tile.getPiece());
		tile.setPiece(attacker);
	}
	
	private ArrayList<Tile> getMovements(Tile tile)
	{
		ArrayList<Tile> movements = new ArrayList<>();
		Piece piece = tile.getPiece();
		String[] moveList = piece.getMoveList();
		
		for(String move : moveList)
		{
			board.getDirection(move,tile);
		}
		
		movements = trimMovements(movements, tile);
		
		return movements;
	}

	
	
	
}
