import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class TestDicoCD extends TestDico {
	public static void main(String[] args) {
		new TestDicoCD().run(args);
	}

	Dictionary creerDico() {
		return new ConcreteDictionary();
	}
}
