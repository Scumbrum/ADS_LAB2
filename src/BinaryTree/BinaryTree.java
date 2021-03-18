package src.BinaryTree;

import java.util.Iterator;

public class BinaryTree<E> implements Iterable<E>{


	private Element<E> root;

	@Override
	public Iterator<E> iterator() {
		return new TreeIterator<E>(this.root);
	}

	class TreeIterator<E> implements Iterator<E>{
		private Element<E> next;

		public TreeIterator(Element<E> root) {
			next = root;
			if(next == null)
				return;

			while (((Element<E>) next).getLeft() != null)
				next = next.getLeft();
		}

		public boolean hasNext(){
			return next != null;
		}

		public E next(){
			if(!hasNext()) throw new IndexOutOfBoundsException();
			Element<E> r = next;

			if(next.getRight() != null) {
				next = next.getRight();
				while (next.getRight() != null)
					next = next.getRight();
				return r.getObject();
			}

			while(true) {
				if(next.getParent() == null) {
					next = null;
					return r.getObject();
				}
				if(next.getParent().getLeft() == next) {
					next = next.getParent();
					return r.getObject();
				}
				next = next.getParent();
			}
		}
	}


	public void add(E element) {
		Element<E> newElem = new Element<>(element);
		if(root==null) {
			root=newElem;
			return;
		}
		Element<E> current = this.root;
		while(true) {
			Comparable<E> first = (Comparable<E>) current.getObject();
			if(first.compareTo(element) > 0) {
				if(current.getLeft()==null) {
					newElem.setParent(current);
					current.setLeft(newElem);
					return;
				}
				current=(Element<E>) current.getLeft();
			} else if(first.compareTo(element) < 0) {
				if(current.getRight()==null) {
					newElem.setParent(current);
					current.setRight(newElem);
					return;
				}
				current=(Element<E>) current.getLeft();
			} else {
				current.setObject(element);
			}
		}
	}

	public int sum(){
		int result = 0;
		try{
			Iterator<E> iter = this.iterator();
			while (iter.hasNext()){
				result += (Integer) iter.next();
			}
		} catch (ClassCastException e){
			throw new IllegalArgumentException("Elements must be Integer", e);
		}

		return result;
	}

	public E find(E findElement){
		Iterator<E> iter = this.iterator();
		while (iter.hasNext()){
			Comparable<E> currentElement = (Comparable<E>) iter.next();
			if(currentElement.compareTo(findElement) == 0){
				return (E) currentElement;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		String result = "[";
		Iterator<E> iter = this.iterator();
		while (iter.hasNext()){
			result += (iter.next().toString() + ", ");
		}
		result = result.substring(0, result.length() - 2);
		result += "]";
		return result;
	}
}
