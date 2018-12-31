package tiiehenry.code.language.php;

import tiiehenry.code.language.*;

public class PHPLanguage extends Language {
	private static PHPLanguage _theOne = null;

	private final static String[] keywords = {
		"abstract", "and", "array", "as", "break", "case", "catch",	"class",
		"clone", "const", "continue", "declare", "default", "do", "else",
		"elseif", "enddeclare", "endfor", "endforeach", "endif", "endswitch",
		"endwhile", "extends", "final", "for", "foreach", "function", "global",
		"goto", "if", "implements", "interface", "instanceof", "namespace",
		"new", "or", "private", "protected", "public", "static", "switch",
		"throw", "try", "use", "var", "while", "xor",
		"die", "echo", "empty", "exit", "eval", "include", "include_once",
		"isset", "list", "require", "require_once", "return", "print", "unset",
		"self", "static", "parent", "true", "TRUE", "false", "FALSE", "null", "NULL"
	};

	private final static char[] operators = {
		'(', ')', '{', '}', '.', ',', ';', '=', '+', '-',
		'/', '*', '&', '!', '|', ':', '[', ']', '<', '>',
		'?', '~', '%', '^', '`', '@'
	};


	public static PHPLanguage getInstance(){
		if(_theOne == null){
			_theOne = new PHPLanguage();
		}
		return _theOne;
	}

	private PHPLanguage(){
		super.setKeywords(keywords);
		super.setOperators(operators);
	}

	@Override
	public boolean isLineAStart(char c){
		return false;
	}

	@Override
	public boolean isWordStart(char c){
		return (c == '$');
	}

}
