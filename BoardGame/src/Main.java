import java.util.Arrays;
public class Main {

	public static void main(String[] args)
	{
		Board test = new Board(1,5);
		test.getBoard()[0][0] = new Tile("x");
		System.out.println(test.toString());
		System.out.println(Arrays.toString(test.getRow(0)));
		System.out.println(Arrays.toString(test.getColumn(0)));
	}
}
