//import java.util.Arrays;
public class Main {

	public static void main(String[] args)
	{
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
	}
}
