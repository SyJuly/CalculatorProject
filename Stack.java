
public class Stack<T> {
	
	public class Node{
		
		public Node next;
		public T obj;
		
		/*public Node(T obj, Node next){
			this.next = next;
			this.obj = obj;
			
		}*/
		
		public Node(T obj){
			this.next = null;
			this.obj = obj;
		}
	}
	
	private Node first;
	private int size;
	
	public Stack() {
		first = null;
		size = 0;
	}

	public boolean isEmpty(){
		return (first == null);
	}
	
	public void push(T o) {
		Node nN = new Node(o);
		if (isEmpty())
			first = nN;
		else {
			nN.next = first;
			first = nN;
		}
		size++;
	}
	
	public void pop() throws RuntimeException {
		if (isEmpty()){
			throw new RuntimeException("StackUnderflow!");
		} else {
			first = first.next;
			size--;
		}
	}
	
	public T top() throws RuntimeException {
		if (isEmpty()){
			throw new RuntimeException("StackUnderflow!");
		} else {
			return first.obj;
		}
	}
	
	public int getSize(){
		return size;
	}
	
	@Override
	public String toString(){
		String[] sA = new String[size];
		String s = "";
		Node curr = first;
		for (int i=size-1; i>=0; i--){
			sA[i] = curr.obj.toString();
			curr = curr.next;
		}
		for (int i=0; i<size; i++){
			s += sA[i];
		}
		return s;	
	}
	
	
	public static void main (String [] args) {
		Stack<Integer> stack = new Stack<>();
		System.out.println("Should be empty: "+stack.isEmpty());
		stack.push(1);
		System.out.println("Stack-Top after pushing 1: "+stack.top());
		stack.push(2);
		System.out.println("Stack-Top after pushing 2: "+stack.top());
		System.out.println("Should not be empty: "+!stack.isEmpty());
		System.out.println("Stack: ["+stack.toString()+"]");
		System.out.println("Size before poping: "+stack.getSize());
		stack.pop();
		System.out.println("Size after poping: "+stack.getSize());
		System.out.println("Stack after poping: ["+stack.toString()+"]");
	}
}
