package tiiehenry.code.language.java;

import java.util.*;
import tiiehenry.code.*;
import tiiehenry.code.language.*;

import static tiiehenry.code.Lexer.*;
import java.io.*;

//to do
public class JavaTokenizer extends LexerTokenizer {
	private static JavaTokenizer _theOne = null;
	//private LexerTokenizer() {}
	public static JavaTokenizer getInstance() {
		if (_theOne == null) {
			_theOne = new JavaTokenizer();
		}
		return _theOne;
	}
	public ArrayList<Pair> tokenize(DocumentProvider hDoc, Flag _abort) {
		int rowCount=hDoc.getRowCount();
		int maxRow=9999;
		ArrayList<Pair> tokens = new ArrayList<Pair>(8196);
		JavaLexer lexer = new JavaLexer(new CharSeqReader(hDoc));
		try {
			int idx = 0;

			JavaType lastType2 = null;

			Pair lastPair = null;
			int lastLen=0;
			while (!_abort.isSet()) {
				Pair pair = null;
				JavaType type = lexer.yylex();
				if (type == null)
					break;
				int len = lexer.yylength();

				if (lastType2 == type && lastPair != null) {
					lastPair.setFirst(lastLen += len);
					continue;
				}
				lastLen = len;
				lastType2 = type;

				switch (type) {
					case KEYWORD:
						tokens.add(new Pair(len, KEYWORD));
						break;
					case LPAREN://separators
					case RPAREN:
					case LBRACE:
					case RBRACE:
					case LBRACK:
					case RBRACK:
					case SEMICOLON:
					case COMMA:
					case DOT:
						/* operators */
					case EQ:
					case GT:
					case LT:
					case NOT:
					case COMP:
					case QUESTION:
					case COLON:
					case EQEQ:
					case LTEQ:
					case GTEQ:
					case NOTEQ:
					case ANDAND:
					case OROR:
					case PLUSPLUS:
					case MINUSMINUS:
					case PLUS:
					case MINUS:
					case MULT:
					case DIV:
					case AND:
					case OR:
					case XOR:
					case MOD:
					case LSHIFT:
					case RSHIFT:
					case URSHIFT:
					case PLUSEQ:
					case MINUSEQ:
					case MULTEQ:
					case DIVEQ:
					case ANDEQ:
					case OREQ:
					case XOREQ:
					case MODEQ:
					case LSHIFTEQ:
					case RSHIFTEQ:
					case URSHIFTEQ:
						//符号
						tokens.add(pair = new Pair(len, OPERATOR));
						break;
					case STRING ://"
					case CHARACTER_LITERAL://'
					case BOOLEAN_LITERAL://true false
					case NULL_LITERAL://null					
						//字符串
						tokens.add(pair = new Pair(len, SINGLE_SYMBOL_DELIMITED_A));
						if (rowCount > maxRow)
							break;

						break;
					case COMMENT :
						//注释
						tokens.add(pair = new Pair(len, DOUBLE_SYMBOL_LINE));
						break;
					case INTEGER_LITERAL:
					case FLOATING_POINT_LITERAL:
						//数字
						tokens.add(new Pair(len, NUMBER));
						break;
					default :
						tokens.add(pair = new Pair(len, NORMAL));
				}


				if (pair != null)
					lastPair = pair;
				idx += len;
			}
		} catch (IOException e) {
			//throw new RuntimeException(e);
			e.printStackTrace();
		}
		// return value cannot be empty
		if (tokens.isEmpty()) 
			tokens.add(new Pair(0, NORMAL));
		return tokens;
	}


}
