import java.util.HashSet;
import java.util.Set;

public class EmptySet extends Expresion {

	@Override
	public Set<String> getLiterals() {
		return new HashSet<String>();
	}

}
