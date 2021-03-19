package src.BinaryTree;

class Element<E extends Comparable<E>> {
	private E object;
	private Element<E> left;
	private Element<E> right;
	private Element<E> parent;

	public Element(E object){
		this.object = object;
	}

	public E getObject() {
		return object;
	}
	public void setObject(E object) {
		this.object = object;
	}

	public Element<E> getLeft() {
		return left;
	}
	public void setLeft(Element<E> smaller) {
		this.left = smaller;
	}

	public Element<E> getRight() {
		return right;
	}
	public void setRight(Element<E> greater) {
		this.right = greater;
	}

	public Element<E> getParent() { return parent;}
	public void setParent(Element<E> parent) { this.parent = parent;}

}
