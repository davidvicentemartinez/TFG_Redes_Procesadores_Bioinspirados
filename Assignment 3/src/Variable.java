import java.util.HashSet;
import java.util.Set;

public class Variable extends Expresion {
	private String name;
	private Expresion exp;
	private Set<String> lit;
	
	public Variable(String name, Expresion exp) {
		this.name = name;
		this.exp = exp;
		this.lit = new HashSet<String>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Expresion getExp() {
		return exp;
	}

	public void setExp(Expresion exp) {
		this.exp = exp;
	}
	
	public Set<String> getLit() {
		return lit;
	}
	
	public void setLit(Set<String> lit) {
		this.lit = lit;
	}
	
	@Override
	public Set<String> getLiterals() {
		Set<String> l = exp.getLiterals();
		l.addAll(lit);	
		return l;
	}

}
