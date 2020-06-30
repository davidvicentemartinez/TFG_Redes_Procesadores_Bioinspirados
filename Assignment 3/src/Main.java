
public class Main {
	public static void main(String[] args) {
		Variable Out4 = new Variable("Out4",new EmptySet());
		Variable In4 = new Variable("In4", new Difference(Out4,new Literal("x")));
		Variable Out3 = new Variable("Out3", In4);
		Variable In3 = new Variable("In3", new Union(Out3, new Literal("x")));
		Variable Out2 = new Variable("Out2", new Union(In3,In4));
		Variable In2 = new Variable("In2", new Union(Out2, new Literal("y")));
		Variable Out1 = new Variable("Out1", In2);
		Variable In1 = new Variable("In1", new Difference(Out1, new Literal("x")));
		
		
		Variable[] equations = {Out4, In4, Out3, In3, Out2, In2, Out1, In1};
		
		EquationSystem sys = new EquationSystem(equations);
		System.out.println("Entrada: " + sys.toString());
		EquationSystem resul = sys.solve();
		System.out.println("Salida: " + resul.toString() + "\n");
		
		
		/*
		Para el segundo sistema definimos que:
			x -> a + b
			y -> a * b
			z -> a + 1
			
		 */
		
		In1 = new Variable("In1", new EmptySet());
		Out1 = new Variable("Out1", new Union(In1,new Literal("x")));
		In2 = new Variable("In2", Out1);
		Out2 = new Variable("Out2", new Union(In1,new Literal("y")));
		Variable In5 = new Variable("In5", Out4);
		Variable Out5 = new Variable("Ou5", new Union(In5,new Literal("x")));
		In3 = new Variable("In3", new Intersection(Out2,Out5));
		Out3 = new Variable("Out3", new Union(In3, new Literal("x")));
		In4 = new Variable("In4", Out3);
		Out4 = new Variable("Out4", new Difference(In4, new Literal("xz")));
		
		Variable[] equations2 = {In1, Out1, In2, Out2, In3, Out3, In4, Out4, In5, Out5};
		
		EquationSystem sys2 = new EquationSystem(equations2);
		System.out.println("Entrada: " + sys2.toString());
		EquationSystem resul2 = sys2.solve();
		System.out.println("Salida: " + resul2.toString());
		
		
		
	}
}
