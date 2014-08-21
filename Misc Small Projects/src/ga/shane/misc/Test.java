package ga.shane.misc;

/** @author http://www.shane.ga */
public class Test {
	/**
	 * Converts a {@link Integer} array to its primitive equivalent ({@link int})[]
	 */
	public static int[] objToPrim(Integer[] array) {
		int[] clone = new int[array.length];

		for(int i = 0; i < array.length; i++)
			clone[i] = array[i];

		return clone;
	}

	/**
	 * Converts a {@link Float} array to its primitive equivalent ({@link float[]})
	 */
	public static float[] objToPrim(Float[] array) {
		float[] clone = new float[array.length];

		for(int i = 0; i < array.length; i++)
			clone[i] = array[i];

		return clone;
	}

	/**
	 * Converts a {@link Boolean} array to its primitive equivalent ({@link boolean})[]
	 */
	public static boolean[] objToPrim(Boolean[] array) {
		boolean[] clone = new boolean[array.length];

		for(int i = 0; i < array.length; i++)
			clone[i] = array[i];

		return clone;
	}

	/**
	 * Converts a {@link Double} array to its primitive equivalent ({@link double})[]
	 */
	public static double[] objToPrim(Double[] array) {
		double[] clone = new double[array.length];

		for(int i = 0; i < array.length; i++)
			clone[i] = array[i];

		return clone;
	}

	/**
	 * Converts a {@link Character} array to its primitive equivalent ({@link char})[]
	 */
	public static char[] objToPrim(Character[] array) {
		char[] clone = new char[array.length];

		for(int i = 0; i < array.length; i++)
			clone[i] = array[i];

		return clone;
	}

	/**
	 * Converts a {@link Byte} array to its primitive equivalent ({@link byte})[]
	 */
	public static byte[] objToPrim(Byte[] array) {
		byte[] clone = new byte[array.length];

		for(int i = 0; i < array.length; i++)
			clone[i] = array[i];

		return clone;
	}


}