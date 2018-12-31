package tiiehenry.code.language.java;
import java.io.*;
import tiiehenry.code.*;
import tiiehenry.code.language.*;

public class JavaFormatter extends DefFormatter
{
	private static JavaFormatter _theOne = null;
	public static JavaFormatter getInstance() {
		if (_theOne == null) {
			_theOne = new JavaFormatter();
		}
		return _theOne;
	}


	@Override
	public int createAutoIndent(CharSequence text) {
		JavaLexer lexer = new JavaLexer(new CharSeqReader(text));
		int idt = 0;
		try {
			while (true) {
				JavaType type = lexer.yylex();
				if (type == null)
					break;
				idt += indent(type);
			}
		} catch (IOException e) {
            e.printStackTrace();
        }
		return idt;
	}
	private int indent(JavaType t) {
        switch (t) {
		    case LBRACE://{
			case LPAREN://(
			case COLON://:
				return 1;
            case RBRACE:
			case RPAREN:
			    return -1;
            default:
                return 0;
        }
    }

	@Override
	public CharSequence format(CharSequence text, int width) {
		StringBuilder builder=new StringBuilder();
		boolean isNewLine=true;
		JavaLexer lexer = new JavaLexer(new CharSeqReader(text));
		try {
			int idt = 0;

			while (true) {
				JavaType type = lexer.yylex();
				if (type == null)
					break;
				if (type == JavaType.STRING && lexer.yytext().equals("\n")) {
					isNewLine = true;
					builder.append('\n');
					idt = Math.max(0, idt);

				} else if (isNewLine) {
					if (type == JavaType.RBRACE) {
						idt--;
						builder.append(createIndent(idt * width));
						builder.append(lexer.yytext());
						idt++;
						isNewLine = false;
					} else {
						builder.append(createIndent(idt * width));
						builder.append(lexer.yytext());
						idt += indent(type);
						isNewLine = false;
					}
				} else {
					builder.append(lexer.yytext());
					idt += indent(type);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return builder;
	}

	private char[] createIndent(int n) {
		if (n < 0)
			return new char[0];
		char[] idts= new char[n];
		for (int i=0;i < n;i++)
			idts[i] = indentChar;
		return idts;
	}

	
}
