import java.io.*;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Postfix {
	
	public int evaluate(String pfx){
		Stack<Double> stack = new Stack<>();
		String[] c = pfx.split(" ");
		c = removeEmptyElements(c);
		int result = 0;
		double ls = 0;
		double rs = 0;
		for(int i = 0; i < c.length; i++){
			double temp = 0.;
			if(isInt(c[i])){
				stack.push(Double.parseDouble(c[i]));
			}
			else{
				rs = stack.top();
				stack.pop();
				ls = stack.top();
				stack.pop();
				switch (c[i]){
					case "*": temp = ls*rs; break;
					case "/": temp = ls/rs; break;
					case "+": temp = ls+rs; break;
					case "-": temp = ls-rs; break;
					case "^": temp = Math.pow(ls, rs); break;
					default : System.out.println("Invalid input:"+c[i]);
				}
				stack.push((temp));
			}
		}
		result = (int)Math.round(stack.top());
		return result;
	}
	
	public String infixToPostfix (String ifx){
		char[] c = ifx.toCharArray();
		Stack<Character> stack = new Stack<>();
		String s = "";
		for(int i = 0; i < c.length; i++){
			if(!Character.isDigit(c[i])&& !(c[i]==')') && !(c[i]=='(')){
				s += " ";
			}
			if(Character.isDigit(c[i])){
				s += Character.toString(c[i]);
			} else if (c[i]=='('){
				stack.push(c[i]);
			} else if (c[i]==')'){
				while (stack.top()!='('){
					s += " "+stack.top()+" ";
					stack.pop();
				}
				stack.pop();
			} else{
				while (!stack.isEmpty() && !(precedenceLevel(stack.top())< precedenceLevel(c[i]))){
					s += " "+stack.top()+" ";
					stack.pop();
				}
				stack.push(c[i]);
			}
		}
		while(!stack.isEmpty()){
			s += " "+stack.top()+ " ";
			stack.pop();
		}
		return s;
		
	}
	
	public int precedenceLevel(char op) throws RuntimeException{
	    switch (op) {
	        case '+':
	        case '-':
	            return 0;
	        case '*':
	        case '/':
	            return 1;
	        case '^':
	            return 2;
	        case '(':
	        case ')':
	            return -1;
	        default:
	        	throw new RuntimeException ("Invalid input detected: " + op);
	    }
	}
	
	private boolean isInt(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(Exception e) { 
	        return false;
	    }
	    return true;
	}
	
	private String[] removeEmptyElements(String[] sA){
		ArrayList<String> list = new ArrayList<String>();

	    for(String s : sA){
	       if(s != null && !s.equals(" ") && !s.equals("")) {
	          list.add(s);
	       }
	    }
	    String[] r = new String[list.size()];
	    r = list.toArray(r);
	    return r;
	}
	
	public void run() throws IOException {
		BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
		String msg=null;
		while((msg = re.readLine()) != null){
			String pfx = infixToPostfix(msg);
			System.out.println(pfx);
			System.out.println(evaluate(pfx));
		}
	}
}