package src;

import src.BinaryTree.BinaryTree;

import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		BinaryTree<Integer> first = new BinaryTree<>();
		first.add(67);
		first.add(68);
		first.add(1);
		first.add(33);
		//first.add(53);
		first.remove(33);
		int k =6;
		System.out.println(first);


		Iterator<Integer> iter = first.iterator();
		while(iter.hasNext()){
			System.out.print(iter.next() + " ");
		}
	}
}
