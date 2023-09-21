import java.io.PrintStream;
import java.util.Iterator;

public abstract class Dictionary {
	abstract public void add(String word);
	abstract public boolean find(String word);
	public Iterator<Character> prefixedDepthFirstIterator() {
		throw new UnsupportedOperationException();
	}
	public Iterator<Character> infixedDepthFirstIterator() {
		throw new UnsupportedOperationException();
	}
	public Iterator<Character> postfixedDepthFirstIterator() {
		throw new UnsupportedOperationException();
	}
	public Iterator<Character> widthIterator() {
		throw new UnsupportedOperationException();
	}
	public Iterator<String> wordIterator() {
		throw new UnsupportedOperationException();
	}
}
