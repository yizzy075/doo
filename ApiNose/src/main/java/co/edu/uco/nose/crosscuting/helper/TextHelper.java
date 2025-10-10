package co.edu.uco.nose.crosscuting.helper;

public final class TextHelper {

	private static final String EMPTY = "";

	private TextHelper() {

	}

	public static String getDefault() {
		return EMPTY;
	}
	
	public static String getDefault(final String value) {
		return ObjectHelper.getDefault(value, getDefault());
	}
	
	public static String getDefaultWithTrim(final String value) {
		return getDefault(value).trim();
	}
}