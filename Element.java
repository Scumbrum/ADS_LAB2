
public class Element<E> {
private Element<E> parent;
public Element<E> getParent() {
	return parent;
}
public void setParent(Element<E> parent) {
	this.parent = parent;
}
private E object;
private Element<E> smaller;
private Element<E> greater;
public Element(E object){
	this.object = object;
}
public E getObject() {
	return object;
}
public void setObject(E object) {
	this.object = object;
}
public Element<E> getSmaller() {
	return smaller;
}
public void setSmaller(Element<E> smaller) {
	this.smaller = smaller;
}
public Element<E> getGreater() {
	return greater;
}
public void setGreater(Element<E> greater) {
	this.greater = greater;
}


}
