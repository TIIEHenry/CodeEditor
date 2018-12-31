package tiiehenry.code.language.csharp;

import tiiehenry.code.language.*;

public class CSharpLanguage extends Language {
	private static CSharpLanguage _theOne = null;

	private final static String[] keywords = {
		"abstract",	"as", "base", "bool", "break", "byte", "case", "catch",
		"char", "checked", "class", "const", "continue", "decimal", "default",
		"delegate", "do", "double", "else", "enum", "event", "explicit",
		"extern", "false", "finally", "fixed", "float", "for", "foreach",
		"goto", "if", "implicit", "in", "int", "interface", "internal", "is",
		"lock", "long", "namespace", "new", "null", "object", "operator",
		"out", "override", "params", "private", "protected", "public",
		"readonly", "ref", "return", "sbyte", "sealed", "short", "sizeof",
		"stackalloc", "static", "string", "struct", "switch", "this", "throw",
		"true", "try", "typeof", "uint", "ulong", "unchecked", "unsafe",
		"ushort", "using", "virtual", "void", "volatile", "while",
		"dynamic", "get", "set", "add", "remove", "global", "value", "var",
		"yield", "alias", "partial",
		"from", "where", "join", "on", "equals", "into", "let", "orderby",
		"ascending", "descending", "select", "group", "by"
	};

	public static CSharpLanguage getInstance(){
		if(_theOne == null){
			_theOne = new CSharpLanguage();
		}
		return _theOne;
	}

	private CSharpLanguage(){
		super.setKeywords(keywords);
	}

}
