import java.util.Set;

public class Union extends Expresion {
	private Expresion e1;
	private Expresion e2;
	
	public Union(Expresion e1, Expresion e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	
	@Override
	public Set<String> getLiterals() {
		Set<String> l1 = e1.getLiterals();
		Set<String> l2 = e2.getLiterals();
		l1.addAll(l2);
		
		return l1;
	}
}
