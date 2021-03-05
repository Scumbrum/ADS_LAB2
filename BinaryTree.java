
public class BinaryTree<E> {
	
	
private Element<E> start;


public void add(E element) {
	Element<E> newElem = new Element<>(element);
	if(start==null) {
		start=newElem;
		return;
	}
	Element<E> current = this.start;
	while(true) {
		Comparable<E> first = (Comparable<E>) current.getObject();
		if(first.compareTo(element)==1) {
			if(current.getSmaller()==null) {
				newElem.setParent(current);
				current.setSmaller(newElem);
				return;
			}
			current=(Element<E>) current.getSmaller();
		} else if(first.compareTo(element)==-1) {
			if(current.getGreater()==null) {
				newElem.setParent(current);
				current.setGreater(newElem);
				return;
			}
			current=(Element<E>) current.getSmaller();
		} else {
			current.setObject(element);
		}
	}
}
@Override
	public String toString() {
		// TODO Auto-generated method stub
	return this.start.getGreater().getObject().toString();
	}
}
