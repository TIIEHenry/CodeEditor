package tiiehenry.code.language.java;
import tiiehenry.code.language.*;

public class JavaLanguage extends Language {

	private static JavaLanguage _theOne = null;

	private final static String[] keywords = {
		"void", "boolean", "byte", "char", "short", "int", "long", "float", "double", "strictfp",
		"import", "package", "new", "class", "interface", "extends", "implements", "enum",
		"public", "private", "protected", "static", "abstract", "final", "native", "volatile",
		"assert", "try", "throw", "throws", "catch", "finally", "instanceof", "super", "this",
		"if", "else", "for", "do", "while", "switch", "case", "default",
		"continue", "break", "return", "synchronized", "transient",
		"true", "false", "null"
	};


	public static JavaLanguage getInstance() {
		if (_theOne == null) {
			_theOne = new JavaLanguage();
		}
		return _theOne;
	}
	
	@Override
	public JavaTokenizer getTokenizer(){
		return JavaTokenizer.getInstance();
	}
	
	@Override
	public JavaFormatter getFormatter(){
		return JavaFormatter.getInstance();
	}
	
	
	private JavaLanguage() {
		super.setKeywords(keywords);
	}

	/**
	 * Java has no preprocessors. Override base class implementation
	 */
	public boolean isLineAStart(char c) {
		return false;
	}
}
