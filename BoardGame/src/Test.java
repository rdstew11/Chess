import java.util.Arrays;

public class Test {
	
	
	
	public static void main(String[] args) 
	{
		//testChessTileNames();
		//testBoardToString(8,8);
		//Team red = new Team("red");
		//Team blue = new Team("blue");
		//testChessToString();
		//testChessGetTile();
		//testChessCheckTileNames();	
		//testChessGetTileGetTeam();
		testCastling();
		//testGetRow();
		//testGame();
		//testGetCol();
		//testGetS();
		//testGetN();
		//testGetE();
		//testBishop();
	}
	
	public static void testGetW()
	{
		String[] letters = new String[] {"a","b","c","d","e","f","g","h","i","j"};
		Chess test = new Chess();
		for(int i = 0; i < test.getBoard().getY(); i++)
		{
			
		}
	}
	
	public static void testBishop()
	{
		Team white = new Team("white");
		Team black = new Team("black");
		Chess test = new Chess(white,black);
		test.emptyBoard();
		Bishop bishop = new Bishop(white);
		Pawn bp1 = new Pawn(black);
		Pawn bp2 = new Pawn(black);
		Pawn bp3 = new Pawn(black);
		Pawn bp4 = new Pawn(black);
		Pawn wp1 = new Pawn(white);
		Pawn wp2 = new Pawn(white);
		Pawn wp3 = new Pawn(white);
		Pawn wp4 = new Pawn(white);
		
		while(true)
		{
			test.getTile("d4").setPiece(bishop);
			test.getTile("d5").setPiece(wp1);
			test.getTile("d3").setPiece(wp2);
			test.getTile("e4").setPiece(wp3);
			test.getTile("c4").setPiece(wp4);
			
			test.getTile("a7").setPiece(bp1);
			test.getTile("h8").setPiece(bp2);
			test.getTile("g1").setPiece(bp3);
			test.getTile("a1").setPiece(bp4);
			test.movePiece(white);
		}
		
	}
	
	public static void testGetE()
	{
		String[] letters = new String[] {"a","b","c","d","e","f","g","h","i","j"};
		Chess test = new Chess();
		for(int i = 0; i < test.getBoard().getY(); i++)
		{
			for(int j = 0; j < test.getBoard().getX(); j++)
			{
				int row = i + 1;
				Tile temp = test.getTile(letters[j]+row);
				System.out.println("Test for tile " + temp.getName());
				System.out.println(test.getBoard().getE(temp));
			}
		}
	}
	public static void testGetS()
	{
		String[] letters = new String[] {"a","b","c","d","e","f","g","h","i","j"};
		Chess test = new Chess();
		for(int i = 0; i < test.getBoard().getY(); i++)
		{
			for(int j = 0; j < test.getBoard().getX(); j++)
			{
				int row = i + 1;
				Tile temp = test.getTile(letters[i]+row);
				System.out.println("Testing for " + temp);
				System.out.println(test.getBoard().getS(temp));
			}
		}
	}
	
	public static void testGetN()
	{
		String[] letters = new String[] {"a","b","c","d","e","f","g","h","i","j"};
		Chess test = new Chess();
		for(int i = 0; i < test.getBoard().getY(); i++)
		{
			for(int j = 0; j < test.getBoard().getX(); j++)
			{
				int row = i + 1;
				Tile temp = test.getTile(letters[j] + row);
				System.out.println("testing for " + temp);
				System.out.println(test.getBoard().getN(temp));
			}
		}
	}
	
	public static void testGetCol()
	{
		Chess test = new Chess();
		for(int i = 0; i < test.getBoard().getX(); i++)
		{
			System.out.println(test.getBoard().getColumn(i));
		}
	}
	
	public static void testGame()
	{
		Chess test = new Chess();
		test.newGame();
	}
	
	public static void testCastling()
	{
		Team white = new Team("white");
		Team black = new Team("black");
		Chess test = new Chess(white,black);
		test.emptyBoard();
		King wKing = new King(white);
		Rook wRook1 = new Rook(white);
		Rook wRook2 = new Rook(white);
		test.getTile("e1").setPiece(wKing);
		test.getTile("h1").setPiece(wRook2);
		test.getTile("a1").setPiece(wRook1);
		
		King bKing = new King(black);
		Rook bRook1 = new Rook(black);
		Rook bRook2 = new Rook(black);
		test.getTile("e8").setPiece(bKing);
		test.getTile("h8").setPiece(bRook2);
		test.getTile("a8").setPiece(bRook1);
		test.movePiece(white);
		test.movePiece(black);
	}
	
	public static void testChessCheckTileNames()
	{
		String[] letters = new String[] {"a","b","c","d","e","f","g","h","i","j"};
		Chess test = new Chess();
		System.out.println("test Chess checkTileNames()...\n");
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				String name = letters[i] + j;
				System.out.println("Checking for " + name);
				System.out.println(test.checkTileName(name));
				System.out.println();
			}
		}
	}
	
	public static void testGetRow()
	{
		Chess test = new Chess();
		for(int i = test.getBoard().getX() - 1; i >= 0; i--)
		{
			System.out.println(test.getBoard().getRow(i));
		}
	}
	
	public static void testChessTileNames()
	{
		System.out.println("test chess tileNames... \n");
		Chess chess = new Chess();
		int x = chess.getBoard().getX();
		int y = chess.getBoard().getY();
		for(int i = y - 1; i >= 0; i--)
		{
			for(int j = 0; j < x; j++)
			{
				System.out.print(chess.getBoard().getTile(j,i).getName() + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void testBoardGetDirection()
	{
		System.out.println("testing board getDirection...\n");
		Board test = new Board(5,5);
		Team red = new Team("red");
		Team blue = new Team("blue");
		
		Piece one = new Piece("1",red);
		Piece two = new Piece("2" ,blue);
		Piece three = new Piece("3",red);
		Piece four = new Piece("4",red);
		Piece five = new Piece("5", red);
		Piece x = new Piece("x", red);
		test.getTile(0,0).setPiece(one);
		test.getTile(4,4).setPiece(two);
		test.getTile(2,2).setPiece(three);
		test.getTile(0,4).setPiece(four);
		test.getTile(4,0).setPiece(five);
		test.getTile(1,1).setPiece(x);
		test.getTile(3,1).setPiece(x);
		test.getTile(1,3).setPiece(x);
		test.getTile(3,3).setPiece(x);
		
		System.out.println(test.getRow(0));
		System.out.println(test.getRow(1));
		System.out.println(test.getRow(2));
		System.out.println(test.getRow(3));
		System.out.println(test.getRow(4));
		System.out.println();
		System.out.println(test.getColumn(0));
		System.out.println(test.getColumn(1));
		System.out.println(test.getColumn(2));
		System.out.println(test.getColumn(3));
		System.out.println(test.getColumn(4));
		System.out.println();
		System.out.println(test);
		System.out.println();
	}
	
	public static void testChessGetTile()
	{
		String[] letters = new String[] {"a","b","c","d","e","f","g","h","i","j"};
		System.out.println("testing chess GetTile...\n");
		Chess test = new Chess();
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0 ; j < 8; j++)
			{
				int charTwo = j + 1;
				String name = letters[i] + charTwo;
				System.out.println("Getting tile " + name + "...");
				System.out.println(test.getTile(name));
				System.out.println();
			}
		}
	}
	
	public static void testChessToString(Team one, Team two)
	{
		System.out.println("testing chess toString()...\n");
		Chess test = new Chess(one,two);
		System.out.println(test);
		System.out.println();
	}
	
	public static void testChessGetTileGetTeam()
	{
		String[] letters = new String[] {"a","b","c","d","e","f","g","h","i","j"};
		Chess test = new Chess();
		System.out.println(test);
		for(int i = 0; i < 2; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				int two = i + 1;
				String name = letters[j] + two;
				System.out.println("Getting piece information for tile \'" + name + "\'.");
				System.out.println(test.getTile(name).getPiece());
				System.out.println(test.getTile(name).getPiece().getTeam());
				System.out.println();
			}
		}
		
		for(int i = 6; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				int two = i + 1;
				String name = letters[j] + two;
				System.out.println("Getting piece information for tile \'" + name + "\'.");
				System.out.println(test.getTile(name).getPiece());
				System.out.println(test.getTile(name).getPiece().getTeam());
				System.out.println();
			}
		}
	}
	
	public static void testChessToString()
	{
		System.out.println("testing chess toString()...\n");
		Chess test = new Chess();
		System.out.println(test);
		System.out.println();
	}
	public static void testBoardToString(int x, int y)
	{
		System.out.println("testing board toString()...\n");
		Board test = new Board(x,y);
		System.out.println(test);
		System.out.println();
	}
	
}
