import java.util.HashMap;
import java.util.Map;

public class EquationSystem {
	private Map<String,String> map;
	private Variable[] equations;
	
	public EquationSystem(Variable[] equations) {
		this.equations = equations;
		this.map = new HashMap<String,String>();
		for(int i=0; i<equations.length; i++) {
			this.map.put(equations[i].getName(), equations[i].getLit());
		}
	}
	
	public EquationSystem(Variable[] equations, Map<String,String> map) {
		this.equations = equations;
		this.map = map;
	}
	
	
	private EquationSystem iterateOnce() {
		Map<String,String> newMap = new HashMap<String,String>();
		newMap.putAll(this.map);
		Variable[] newEquations = this.equations.clone();
		
		for(int i=0; i<newEquations.length; i++) {
			String name = newEquations[i].getName();
			String lit = newEquations[i].getLiterals();
			newEquations[i].setLit(lit);
			newMap.put(name, lit);
		}
		return new EquationSystem(newEquations,newMap);
	}
	
	public EquationSystem solve() {
		EquationSystem ant = new EquationSystem(this.getEquations(),this.getMap());
		EquationSystem act = this.iterateOnce();
		
		while(!ant.getMap().equals(act.getMap())) {
			ant = act;
			act = act.iterateOnce();
		}
		return act;		
	}
	
	
	public String toString() {
		return map.toString();
	}


	public Map<String, String> getMap() {
		return map;
	}


	public void setMap(Map<String, String> map) {
		this.map = map;
	}


	public Variable[] getEquations() {
		return equations;
	}


	public void setEquations(Variable[] equations) {
		this.equations = equations;
	}
	
}
