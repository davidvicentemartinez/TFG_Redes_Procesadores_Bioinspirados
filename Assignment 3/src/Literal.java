import java.util.HashSet;
import java.util.Set;

public class Literal extends Expresion {
	private Set<String> literal;
	
	public Literal(Set<String> literal) {
		this.literal = literal;
	}
	
	@Override
	public Set<String> getLiterals() {
		return this.literal;
	}
}
