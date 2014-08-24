package ga.shane.misc;

/** @author http://www.shane.ga */
public class ArrayUtilsMethodGenerator {
	public static void main(String[] args) {
		String[][] methods = {
			head(),
			primToObj(),
			objToPrim(), 
			containsObj(),
			containsObject(),
			containsPrim(),
			setObj(),
			setObject(),
			setPrim(),
			populateObj(),
			populateObject(),
			populatePrim(),
			populatedObj(),
			populatedObject(),
			populatedPrim(),
			isExclusivelyPopulatedObj(),
			isExclusivelyPopulatedObject(),
			isExclusivelyPopulatedPrim(),
			equalsdObj(),
			equalsObject(),
			equalsPrim(),
			foot()
		};
		
		for(String[] cur : methods) {
			for(String curm : cur) {
				if(curm.contains("\n")) {
					for(String line : curm.split("\n")) {
						System.out.println("	" + line);
					}
				} else
					System.out.println(curm);
				
				/*if(cur != methods[0] && cur != methods[methods.length - 1])
					curm = "	f" + curm;
				
				System.out.println(curm);*/
			}
		}
		
		/*for(String[] cur : methods) {
			for(String curm : cur)
				System.out.println(curm);
		}*/
	}
		
	private static String[] head() {
		String[] head = new String[] {
			"",
			"	/*",
			"	 ################################################################",
			"	################ Auto generated code #############################",
			"	 ################################################################",
			"	*/",
			"",
			"package ga.shane.utilities;",
			"",
			"import java.util.ArrayList;",
			"",
			"/** @author http://www.shane.ga */",
			"public class ArrayUtils {"
		};
		
		return head;
	}
	
	private static String[] foot() {
		String[] foot = new String[] {
			"",
			"}",
			"	/*",
			"	 ################################################################",
			"	################ Auto generated code #############################",
			"	 ################################################################",
			"	*/"
		};
		
		return foot;
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
	
	private static void containsGen(String[] methods, int index, String type, boolean isObject) {
		insertDoc(methods, index, "Array equivalent of {@link List#contains(Object)}");
		methods[index]+= "public static boolean contains("+ type + "[] array, " + type + " find) {\n";
		methods[index]+= "	for(" + type + " cur : array) {\n";
		methods[index]+= (isObject ? "		if(cur.equals(find))" : "		if(cur == find)") + "\n";
		methods[index]+= "			return true;\n";
		methods[index]+= "	}\n";
		methods[index]+= "\n	return false;\n}\n";
	}
	
	private static String[] containsObj() {
		String[] methods = new String[types.length];
		
		for(int i = 0; i < methods.length; i++) {
			String obj = types[i][1];
			containsGen(methods, i, obj, true);
		}
		
		return methods;
	}
	
	private static String[] containsObject() {
		String[] methods = new String[1];
		containsGen(methods, 0, "Object", true);
		return methods;
	}
	
	private static String[] containsPrim() {
		String[] methods = new String[types.length];
		
		for(int i = 0; i < methods.length; i++) {
			String prim = types[i][0];
			containsGen(methods, i, prim, false);
		}
		
		return methods;
	}
	
	private static void setGen(String[] methods, int index, String type) {
		insertDoc(methods, index, "Set the contents of an array to that of another array");
		methods[index]+= "public static void set(" + type + "[] from, " + type + "[] to) {\n";
		methods[index]+= "	if(from.length != to.length)\n";
		methods[index]+= "		throw new RuntimeException(\"Can't set array -> to, because sizes aren't the same\");\n";
		methods[index]+= "\n	for(int i = 0; i < from.length; i++)\n";
		methods[index]+= "		from[i] = to[i];\n";
		methods[index]+= "}\n";
	}
	
	private static String[] setObj() {
		String[] methods = new String[types.length];
		
		for(int i = 0; i < methods.length; i++) {
			String obj = types[i][1];
			setGen(methods, i, obj);
		}
		
		return methods;
	}
	
	private static String[] setObject() {
		String[] methods = new String[1];
		setGen(methods, 0, "Object");
		return methods;
	}
	
	private static String[] setPrim() {
		String[] methods = new String[types.length];
		
		for(int i = 0; i < methods.length; i++) {
			String prim = types[i][0];
			setGen(methods, i, prim);
		}
		
		return methods;
	}
	
	private static void populateGen(String[] methods, int index, String type) {
		insertDoc(methods, index, "Populate all spaces in an array with one value");
		methods[index]+= "public static void populate(" + type + "[] array, " + type + " value) {\n";
		methods[index]+= "	for(int i = 0; i < array.length; i++)\n";
		methods[index]+= "		array[i] = value;\n";
		methods[index]+= "}\n";
	}
	
	private static String[] populateObj() {
		String[] methods = new String[types.length];
		
		for(int i = 0; i < methods.length; i++) {
			String obj = types[i][1];
			populateGen(methods, i, obj);
		}
		
		return methods;
	}
	
	private static String[] populateObject() {
		String[] methods = new String[1];
		populateGen(methods, 0, "Object");
		return methods;
	}
	
	private static String[] populatePrim() {
		String[] methods = new String[types.length];
		
		for(int i = 0; i < methods.length; i++) {
			String prim = types[i][0];
			populateGen(methods, i, prim);
		}
		
		return methods;
	}
	
	private static void populatedGen(String[] methods, int index, String type, boolean isObject) {
		insertDoc(methods, index, "Checks if an array is fully populated by one value");
		methods[index]+= "public static boolean populated(" + type + "[] array, " + type + " value) {\n";
		methods[index]+= "	for(" + type + " cur : array) {\n";
		methods[index]+= (isObject ? "		if(!cur.equals(value))" : "		if(cur != value)") + "\n";
		methods[index]+= "			return false;\n";
		methods[index]+= "	}\n";
		methods[index]+= "\n	return true;\n";
		methods[index]+= "}\n";
	}
	
	private static String[] populatedObj() {
		String[] methods = new String[types.length];
		
		for(int i = 0; i < methods.length; i++) {
			String obj = types[i][1];
			populatedGen(methods, i, obj, true);
		}
		
		return methods;
	}
	
	private static String[] populatedObject() {
		String[] methods = new String[1];
		populatedGen(methods, 0, "Object", true);
		return methods;
	}
	
	private static String[] populatedPrim() {
		String[] methods = new String[types.length];
		
		for(int i = 0; i < methods.length; i++) {
			String prim = types[i][0];
			populatedGen(methods, i, prim, false);
		}
		
		return methods;
	}
	
	private static void isExclusivelyPopulatedGen(String[] methods, int index, String type, boolean isObject) {
		insertDoc(methods, index, "Checks whether an array contains a value more than once or not");
		methods[index]+= "public static boolean isExclusivelyPopulated(" + type + "[] array) {\n";
		
		String listValue = "cur";
		String listType = type;
		
		if(!isObject) {
			for(String[] cur : types) {
				if(cur[0].equals(type)) {
					listValue = cur[1] + ".valueOf(cur)";
					listType = cur[1];
					break;
				}
			}
		}
		
		methods[index]+= "	ArrayList<" + listType + "> contents = new ArrayList<" + listType + ">();\n";
		methods[index]+= "\n	for(" + type + " cur : array) {\n";
		methods[index]+= "		if(contents.contains(" + listValue + "))\n";
		methods[index]+= "			return false;\n";
		methods[index]+= "\n		contents.add(cur);\n";
		methods[index]+= "	}\n";
		methods[index]+= "\n	return true;\n";
		methods[index]+= "}\n";
	}
	
	private static String[] isExclusivelyPopulatedObj() {
		String[] methods = new String[types.length];
		
		for(int i = 0; i < methods.length; i++) {
			String obj = types[i][1];
			isExclusivelyPopulatedGen(methods, i, obj, true);
		}
		
		return methods;
	}
	
	private static String[] isExclusivelyPopulatedObject() {
		String[] methods = new String[1];
		isExclusivelyPopulatedGen(methods, 0, "Object", true);
		return methods;
	}
	
	private static String[] isExclusivelyPopulatedPrim() {
		String[] methods = new String[types.length];
		
		for(int i = 0; i < methods.length; i++) {
			String prim = types[i][0];
			isExclusivelyPopulatedGen(methods, i, prim, false);
		}
		
		return methods;
	}
	
	private static void equalsGen(String[] methods, int index, String type, boolean isObject) {
		insertDoc(methods, index, "Checks if array1 is the same as array2");
		methods[index]+= "public static boolean equals(" + type + "[] array1, " + type + "[] array2) {\n";
		methods[index]+= "	if(array1.length != array2.length)\n";
		methods[index]+= "		return false;\n";
		methods[index]+= "\n	for(int i = 0; i < array1.length; i++) {\n";
		
		if(isObject) {
			methods[index]+= "		if(array1[i] == null || array2[i] == null) {\n";
			methods[index]+= "			if(array1[i] != array2[i])\n";
			methods[index]+= "				return false;\n";
			methods[index]+= "		} else if(!array1[i].equals(array2[i]))\n";
			methods[index]+= "			return false;\n";
		} else {
			methods[index]+= "		if(array1[i] != array2[i])\n";
			methods[index]+= "			return false;\n";
		}
		
		methods[index]+= "	}\n";
		methods[index]+= "\n	return true;\n";
		methods[index]+= "}\n";
		
		/*methods[index]+= "public static boolean equals(" + type + "[] array1, " + type + "[] array2) {\n";
		methods[index]+= "	if(array1.length != array2.length)\n";
		methods[index]+= "		return false;\n";
		methods[index]+= "\n	for(int i = 0; i < array1.length; i++) {\n";
		methods[index]+= (isObject ? "		if(array1[i] == null || array2[i] == null) {if(array1[i] != array2[i])if(!array1[i].equals(array2[i]))" : "		if(array1[i] != array2[i])") + "\n";
		methods[index]+= "			return false;\n";
		methods[index]+= "		}\n";
		methods[index]+= "\n	return true;\n";
		methods[index]+= "}\n";*/
	}

	private static String[] equalsdObj() {
		String[] methods = new String[types.length];
		
		for(int i = 0; i < methods.length; i++) {
			String obj = types[i][1];
			equalsGen(methods, i, obj, true);
		}
		
		return methods;
	}
	
	private static String[] equalsObject() {
		String[] methods = new String[1];
		equalsGen(methods, 0, "Object", true);
		return methods;
	}
	
	private static String[] equalsPrim() {
		String[] methods = new String[types.length];
		
		for(int i = 0; i < methods.length; i++) {
			String prim = types[i][0];
			equalsGen(methods, i, prim, false);
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
		}, {
			"short", "Short"
		}, {
			"long", "Long"
		}
	};
}
