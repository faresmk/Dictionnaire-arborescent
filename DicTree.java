import java.util.List;
import java.util.ArrayList;
public class DicTree {

    char c;
    DicTree Left; //the left child indicates the passage to the next letter
    DicTree Right; //The right child indicates another letter for the current letter.(replace the current letter) and all right children are greater than the current letter
    Boolean isWord;

    public DicTree(char c) {
        this.c = c;
        this.Left = null;
        this.Right = null;
        this.isWord = false;
    }

    public DicTree() {
        this.c = 0;
        this.Left = null;
        this.Right = null;
        this.isWord = false;
    }

    public void setC(char c) {
        this.c = c;
    }

    public void setLeft(DicTree left) {
        this.Left = left;
    }

    public void setRight(DicTree right) {
        this.Right = right;
    }

    public void setWord(Boolean isWord) {
        this.isWord = isWord;
    }

    public char getChar() {
        return this.c;
    }

    public DicTree getLeft() {
        return this.Left;
    }

    public DicTree getRight() {
        return this.Right;
    }

    public Boolean isWord() {
        return this.isWord;
    }

    public void prefix(DicTree root, ArrayList<Character> list){
        if(root == null){
            return;
        }
        list.add(root.getChar());
        prefix(root.getLeft(), list);
        prefix(root.getRight(), list);
    }
    public void infix(DicTree root, ArrayList<Character> list){
        if(root == null){
            return;
        }
        infix(root.getLeft(), list);
        list.add(root.getChar());
        infix(root.getRight(), list);
    }
    public void postfix(DicTree root, ArrayList<Character> list){
        if(root == null){
            return;
        }
        postfix(root.getLeft(), list);
        postfix(root.getRight(), list);
        list.add(root.getChar());
    }

	public void word(DicTree node, ArrayList<String> list, String word) {
		if (node.isWord()) {
			list.add(word);
		}
		DicTree child = node.getLeft();
		while (child != null) {
			word(child, list, word + child.getChar());
			child = child.getRight();
		}
	}


}
