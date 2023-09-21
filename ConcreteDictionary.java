import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class ConcreteDictionary extends Dictionary {

	public DicTree root;

	public ConcreteDictionary() {
		root = new DicTree();
	}

	@Override
	public void add(String mot) {
		DicTree current = root;
		for (int i = 0; i < mot.length(); i++) {
			char c = mot.charAt(i);
			DicTree child = current.getLeft();
			DicTree parent = null;
			while (child != null) {
				if (child.getChar() == c) {
					current = child;
					break;
				} else if (child.getChar() > c) {
					DicTree newChild = new DicTree(c);
					newChild.setRight(child);
					if (parent == null) {
						current.setLeft(newChild);
					} else {
						parent.setRight(newChild);
					}
					current = newChild;
					break;
				} else {
					parent = child;
					child = child.getRight();
				}
			}
			if (child == null) {
				DicTree newChild = new DicTree(c);
				if (parent == null) {
					current.setLeft(newChild);
				} else {
					parent.setRight(newChild);
				}
				current = newChild;
			}
		}
		current.setWord(true);
	}

	@Override
	public boolean find(String mot) {
		DicTree current = root;
		for (int i = 0; i < mot.length(); i++) {
			char c = mot.charAt(i);
			DicTree child = current.getLeft();
			while (child != null) {
				if (child.getChar() == c) {
					current = child;
					break;
				} else if (child.getChar() > c) {
					return false;
				} else {
					child = child.getRight();
				}
			}
			if (child == null) {
				return false;
			}
		}
		return current.isWord();
	}

	public Iterator<Character> prefixedDepthFirstIterator() {
		ArrayList<Character> list = new java.util.ArrayList<Character>();
		root.prefix(root, list);
		list.remove(0);
		return list.iterator();
	}
	
	public Iterator<Character> infixedDepthFirstIterator() {
		ArrayList<Character> list = new java.util.ArrayList<Character>();
		root.infix(root, list);
		list.remove(list.size() - 1);
		return list.iterator();
	}
	
	public Iterator<Character> postfixedDepthFirstIterator() {
		ArrayList<Character> list = new java.util.ArrayList<Character>();
		root.postfix(root, list);
		//delete the last element
		list.remove(list.size() - 1);
		return list.iterator();
	}
	public Iterator<Character> widthIterator() {
		ArrayList<Character> list = new java.util.ArrayList<Character>();
		ArrayList<DicTree> queue = new java.util.ArrayList<DicTree>();
		queue.add(root);
		while (!queue.isEmpty()) {
			DicTree current = queue.remove(0); 
			DicTree child = current.getLeft();
			while (child != null) {
				list.add(child.getChar());
				queue.add(child);
				child = child.getRight();
			}
		}
		return list.iterator();
	}
	public Iterator<String> wordIterator() {
		ArrayList<String> list = new java.util.ArrayList<String>();
		root.word(root, list, "");
		return list.iterator();
	}

}