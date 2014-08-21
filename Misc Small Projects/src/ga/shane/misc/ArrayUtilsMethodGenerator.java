package ga.shane.misc;

/** @author http://www.shane.ga */
public class ArrayUtilsMethodGenerator {
	public static void main(String[] args) {
		String[][] methods = {
			primToObj(),
			objToPrim()
		};
		
		for(String[] cur : methods) {
			for(String curm : cur)
				System.out.println(curm);
		}
	}
	
	private static void insertDoc(String[] methods, int index, String... doc) {
		methods[index] = "/**\n";
		
		for(String line : doc)
			methods[index]+= " * " + line.replaceAll("%o", types[index][1]).replaceAll("%p", types[index][0]) + "\n";
		
		methods[index]+= " */\n";
	}
	
	private static String[] primToObj() {
		String[] methods = new String[types.length];
		
		for(int i  = 0; i < methods.length; i++) {
			String prim = types[i][0];
			String obj = types[i][1];
			
			insertDoc(methods, i, "Converts a %p array to its object equivalent ({@link %o})[]");
			methods[i]+= "public static " + obj + "[] primToObj(" + prim + "[] array) {\n";
			methods[i]+= "	" + obj + "[] clone = new " + obj + "[array.length];\n";
			methods[i]+= "\n	for(int i = 0; i < array.length; i++)\n";
			methods[i]+= "		clone[i] = array[i];\n";
			methods[i]+= "\n	return clone;";
			methods[i]+= "\n}\n";
		}
		
		return methods;
	}
	
	private static String[] objToPrim() {
		String[] methods = new String[types.length];
				
		for(int i = 0; i < methods.length; i++) {
			String prim = types[i][0];
			String obj = types[i][1];
			
			insertDoc(methods, i, "Converts a {@link %o} array to its primitive equivalent (%p[])");
			methods[i]+= "public static " + prim + "[] objToPrim(" + obj + "[] array) {\n";
			methods[i]+= "	" + prim + "[] clone = new " + prim + "[array.length];\n";
			methods[i]+= "\n	for(int i = 0; i < array.length; i++)\n";
			methods[i]+= "		clone[i] = array[i];\n";
			methods[i]+= "\n	return clone;";
			methods[i]+= "\n}\n";
		}
		
		return methods;
	}
	
	
	
	private final static String[][] types = {
		{	
			"int", "Integer"
		}, {
			"float", "Float"
		}, {
			"boolean", "Boolean"
		}, {
			"double", "Double"
		}, {
			"char", "Character"
		}, {
			"byte", "Byte"
		}
	};
}
