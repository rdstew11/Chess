
public interface Game {
	public Board board;
	
	public void movePiece(Piece piece, Tile tile);
	
	public void capture(Piece attacker, Piece victim);
	
	
	
	

}
