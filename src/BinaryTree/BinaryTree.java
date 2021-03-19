package src.BinaryTree;

import java.util.Iterator;

public class BinaryTree<E extends Comparable<E>> implements Iterable<E>{


	private Element<E> root;

	@Override
	public Iterator<E> iterator() {
		return new TreeIterator<E>(this.root);
	}

	class TreeIterator<E extends Comparable<E>> implements Iterator<E>{
		private Element<E> next;

		public TreeIterator(Element<E> root) {
			next = root;
			if(next == null)
				return;

			while (next.getLeft() != null)
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
				while (next.getLeft() != null)
					next = next.getLeft();
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
			Comparable<E> first = current.getObject();
			if(first.compareTo(element) > 0) {
				if(current.getLeft()==null) {
					newElem.setParent(current);
					current.setLeft(newElem);
					return;
				}
				current= current.getLeft();
			} else if(first.compareTo(element) < 0) {
				if(current.getRight()==null) {
					newElem.setParent(current);
					current.setRight(newElem);
					return;
				}
				current= current.getRight();
			} else {
				current.setObject(element);
				return;
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
		if(this.root == null){
			return null;
		}
		return findFrom(this.root, findElement);
	}

	private E findFrom(Element start, E findElement){
		if(start == null){
			return null;
		}
		switch (findElement.compareTo((E) start.getObject())){
			case 0:
				return (E) start.getObject();
			case -1:
				return findFrom(start.getLeft(), findElement);
			case 1:
				return findFrom(start.getRight(), findElement);
		}
		return null;
	}

        // NOT recursive searching
    private Element[] searchWithParent(Element searched) {
//		Comparable<E> currentValue = root.getObject();
		Element currentElement = root;
		Comparable<E> currentValue = currentElement.getObject();
		Element parent = null;


		while (currentElement != null) {
			// value of searched node > current
			if ((searched.getObject().compareTo(currentValue)) > 0) {

				parent = currentElement;
				currentElement = currentElement.getRight();
				currentValue = currentElement.getObject();
				continue;
			}
			// value of searched node < current
			if ((searched.getObject().compareTo(currentValue)) < 0) {
				parent = currentElement;
				currentElement = currentElement.getLeft();
				currentValue = currentElement.getObject();
				continue;
			}
			// element is found, current != null
			if ((searched.getObject().compareTo(currentValue)) == 0){
				break;
			}
			return new Element[]{currentElement, parent};
		}
			return new Element[]{currentElement, parent};
	}

	    public boolean remove(E value) {
        Element removed = new Element(value);
        Element[] sp = searchWithParent(removed);      // sp[0] - searched, sp[1] - parent

        removed = sp[0];
        Element parent = sp[1];

        if (removed == null) {                      // node is not found
            return false;
        }


        // Case 1: if there are no children to the right, left child stands on removed place
        if(removed.getRight() == null){             // NO right branch
            if(parent.getObject() == null){                     // removed node is root
                root = removed.getLeft();
            }
            else{                                   // No right branch, not root
                int result = removed.getObject().compareTo(parent.getObject());

                if (result < 0) {                 // removed < parent
                    parent.setLeft(removed.getLeft()); // removed's left child becomes parent's left child
                }
                else if (result > 0) {             // removed > parent (removed - right child)
                    parent.setRight(removed.getLeft());
                }
            }
            return true;
        }


        // Case 2
        // 1) removed has right child - removed.rChild, and rChild doesn't havechildren to the left, so
        // 2) removed.lChild's left child stands o the place of removed.rChild's left child
        // 3) removed.rChild stands on removed's place
        // 4) removed was to the left side of parent => every removed's child is to the left side of parent


        if (removed.getRight().getLeft() == null) {              // 1)
            removed.getRight().setLeft(removed.getLeft());      // 2)

            int result = removed.getObject().compareTo(parent.getObject());
            if (result < 0) {                 // removed < parent
                parent.setLeft(removed.getRight()); // 4) removed.rChild becomes parent's left child
				removed.getRight().setParent(parent);
            } else if (result > 0) {             // removed > parent
                parent.setRight(removed.getRight()); //removed.rChild becomes parent's right child
				removed.getRight().setParent(parent);
            }
            return true;
        }

        // Case 3: removed.rChild has children to the left side
        // the youngest left child of removed.rChild (leftmost) becomes removed

        Element leftmost = removed.getRight().getLeft();
        Element leftmostParent = removed.getRight();

        while (leftmost.getLeft() != null) {        // поиск leftmost по левой ветке removed.rChild
            leftmostParent = leftmost;
            leftmost = leftmost.getLeft();
        }

        // right branch of leftmost becomes thge left branch of his parent
        leftmostParent.setLeft(leftmost.getRight());

        // leftmost stands on removed's place
        leftmost.setLeft(removed.getLeft());
        leftmost.setRight(removed.getRight());

        if (parent == null) {
            root = leftmost;
        } else {
            int result = removed.getObject().compareTo(parent.getObject());
            if (result < 0) {
                parent.setLeft(leftmost); // removed < parent
                // the youngest left child becomes parent's left child
            } else if (result > 0) {
                parent.setRight(leftmost);      // removed > parent
				// the youngest left child becomes parent's right child
            }
        }
        return true;
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
