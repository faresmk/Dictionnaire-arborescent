import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public abstract class TestDico {
	<E> void traiteNoeud(E n) {
		System.out.print(n + " ");
	}
	<E> void traversal(String message, Iterator<E> it) {
		System.out.print("Parcours " + message + " : ");
		int i=0;
		while (it.hasNext()) {
			E n = it.next();
			traiteNoeud(n);
			i++;
		}
		System.out.println();
		System.out.println("Nombre d'itérations : " + i);
	}

	abstract Dictionary creerDico();

	void run(String[] args) {
		final int FIND = 1;
		final int WORDS = 2;
		final int WIDTH = 4;
		final int DEPTHPRE = 8;
		final int DEPTHINF = 16;
		final int DEPTHPOST = 32;
		final int MEM = 64;

		Dictionary dict = creerDico();
		int tests = 0;
		int seed = 42;
		Scanner s = null;

		try {
			s = new Scanner(new FileInputStream(args[0]));
			tests = Integer.parseInt(args[1]);
			if (args.length > 2)
				seed = Integer.parseInt(args[2]);
		} catch (FileNotFoundException e) {
			System.err.println("Impossible d'ouvrir le fichier " + args[0]);
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Ce programme nécessite au moins deux arguments :\n" +
					"- le fichier contenant le dictionnaire\n" +
					"- l'ensemble des tests à effectuer codé comme la somme des valeurs de test souhaitées :\n" +
					"  - 1 - recherche des mots donnés sur l'entrée standard\n" +
					"  - 2 - parcours des mots à l'aide de l'itérateur\n" +
					"  - 4 - parcours en largeur à l'aide de l'itérateur\n" +
					"  - 8 - parcours en profondeur préfixé à l'aide de l'itérateur\n" +
					"  - 16 - parcours en profondeur infixé à l'aide de l'itérateur\n" +
					"  - 32 - parcours en profondeur postfixé à l'aide de l'itérateur");
			System.exit(1);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		while (s.hasNextLine()) {
			dict.add(s.nextLine());
		}
		if ((tests & FIND) > 0) {
			boolean fin = false;
			s = new Scanner(System.in);
			System.err.println("Saisissez un mot à rechercher (ou un mot vide pour terminer) :");
			while (!fin && s.hasNextLine()) {
				String word = s.nextLine();
				if (word.length() > 0) {
					System.out.println("Recherche de [" + word + "] : " + dict.find(word));
					System.err.println("Saisissez un mot à rechercher (ou un mot vide pour terminer) :");
				} else
					fin = true;
			}
		}
		if ((tests & WORDS) > 0)
			traversal("des mots", dict.wordIterator());
		if ((tests & WIDTH) > 0)
			traversal("en largeur", dict.widthIterator());
		if ((tests & DEPTHPRE) > 0)
			traversal("en profondeur préfixé", dict.prefixedDepthFirstIterator());
		if ((tests & DEPTHINF) > 0)
			traversal("en profondeur infixé", dict.infixedDepthFirstIterator());
		if ((tests & DEPTHPOST) > 0)
			traversal("en profondeur postfixé", dict.postfixedDepthFirstIterator());
		if ((tests & MEM) > 0) {
			Random r = new Random(seed);
			int size = r.nextInt(10)+10;
			Iterator [] it = new Iterator[size];
			for (int i=0; i<it.length; i++) {
				Iterator<Character> newit = null;
				switch (r.nextInt(3)) {
					case 0:
						newit = dict.prefixedDepthFirstIterator();
						break;
					case 1:
						newit = dict.infixedDepthFirstIterator();
						break;
					case 2:
						newit = dict.postfixedDepthFirstIterator();
						break;
				}
				it[i] = newit;
			}
			int rounds = r.nextInt(1000)+1000;
			System.out.print("Multiples parcours en concurrence : ");
			for (int i=0; i<rounds; i++) {
				int choix = r.nextInt(it.length);
				if (it[choix].hasNext()) {
					traiteNoeud(it[choix].next());
				}
			}
			System.out.println();
		}
		System.out.println("Exécution terminée, arguments : " + java.util.Arrays.toString(args));
	}
}
