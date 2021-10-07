/* The following code was generated by JFlex 1.4.3 on 6/10/21 07:42 PM */

package codigo;
import java_cup.runtime.Symbol;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 6/10/21 07:42 PM from the specification file
 * <tt>C:/ANADAN/src/codigo/LexerCup.flex</tt>
 */
class LexerCup implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = {
     0,  0,  0,  0,  0,  0,  0,  0,  0,  5,  5,  0,  0,  5,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     5, 38,  7,  0,  0, 37, 39,  0, 43, 44, 36,  3, 34,  4,  8,  6, 
     2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  0, 49, 41, 35, 42,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 47,  0, 48,  0,  1, 
     0, 15, 32, 21, 23, 10, 12, 22, 30, 13, 31, 33, 14, 16, 17, 25, 
    19,  1, 20,  9, 11, 27, 29, 24, 26, 18, 28, 45, 40, 46,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0
  };

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7"+
    "\1\1\1\10\17\2\1\11\1\12\1\13\1\14\1\15"+
    "\2\1\1\16\1\17\1\20\1\21\1\22\1\23\1\24"+
    "\1\25\1\26\1\0\1\27\1\30\1\0\1\6\1\0"+
    "\1\31\1\32\14\2\1\33\16\2\1\34\6\2\1\35"+
    "\1\36\1\37\1\40\1\41\1\42\7\2\1\43\5\2"+
    "\1\44\3\2\1\45\3\2\1\46\1\2\1\47\15\2"+
    "\1\50\1\2\1\51\1\52\1\2\1\53\1\54\1\2"+
    "\1\55\2\2\1\56\3\2\1\57\4\2\1\60\3\2"+
    "\1\61\1\2\1\62\1\63\5\2\1\64\1\65\3\2"+
    "\1\66\1\2\1\67\1\2\1\70\1\71\7\2\1\72"+
    "\1\73\3\2\1\74\3\2\1\75\4\2\1\76\1\77"+
    "\3\2\1\100\50\2\1\101\6\2\1\102\4\2\1\103"+
    "\2\2\1\104\4\2\1\105\1\2\1\106\1\107\1\110"+
    "\6\2\1\111\2\2\1\112\1\113\1\2\1\114\1\115"+
    "\1\2\1\116\1\117";

  private static int [] zzUnpackAction() {
    int [] result = new int[279];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\62\0\144\0\226\0\310\0\372\0\62\0\u012c"+
    "\0\u015e\0\u0190\0\u01c2\0\u01f4\0\u0226\0\u0258\0\u028a\0\u02bc"+
    "\0\u02ee\0\u0320\0\u0352\0\u0384\0\u03b6\0\u03e8\0\u041a\0\u044c"+
    "\0\u047e\0\62\0\u04b0\0\u012c\0\62\0\u04e2\0\u0514\0\u0546"+
    "\0\u0578\0\u05aa\0\62\0\62\0\62\0\62\0\62\0\62"+
    "\0\62\0\u0190\0\62\0\62\0\u012c\0\u012c\0\u015e\0\62"+
    "\0\u0190\0\u05dc\0\u060e\0\u0640\0\u0672\0\u06a4\0\u06d6\0\u0708"+
    "\0\u073a\0\u076c\0\u079e\0\u07d0\0\u0802\0\144\0\u0834\0\u0866"+
    "\0\u0898\0\u08ca\0\u08fc\0\u092e\0\u0960\0\u0992\0\u09c4\0\u09f6"+
    "\0\u0a28\0\u0a5a\0\u0a8c\0\u0abe\0\144\0\u0af0\0\u0b22\0\u0b54"+
    "\0\u0b86\0\u0bb8\0\u0bea\0\62\0\62\0\62\0\62\0\62"+
    "\0\62\0\u0c1c\0\u0c4e\0\u0c80\0\u0cb2\0\u0ce4\0\u0d16\0\u0d48"+
    "\0\144\0\u0d7a\0\u0dac\0\u0dde\0\u0e10\0\u0e42\0\144\0\u0e74"+
    "\0\u0ea6\0\u0ed8\0\144\0\u0f0a\0\u0f3c\0\u0f6e\0\144\0\u0fa0"+
    "\0\u0fd2\0\u1004\0\u1036\0\u1068\0\u109a\0\u10cc\0\u10fe\0\u1130"+
    "\0\u1162\0\u1194\0\u11c6\0\u11f8\0\u122a\0\u125c\0\144\0\u128e"+
    "\0\144\0\144\0\u12c0\0\144\0\144\0\u12f2\0\144\0\u1324"+
    "\0\u1356\0\144\0\u1388\0\u13ba\0\u13ec\0\144\0\u141e\0\u1450"+
    "\0\u1482\0\u14b4\0\144\0\u14e6\0\u1518\0\u154a\0\144\0\u157c"+
    "\0\144\0\144\0\u15ae\0\u15e0\0\u1612\0\u1644\0\u1676\0\144"+
    "\0\144\0\u16a8\0\u16da\0\u170c\0\144\0\u173e\0\144\0\u1770"+
    "\0\144\0\144\0\u17a2\0\u17d4\0\u1806\0\u1838\0\u186a\0\u189c"+
    "\0\u18ce\0\144\0\144\0\u1900\0\u1932\0\u1964\0\144\0\u1996"+
    "\0\u19c8\0\u19fa\0\144\0\u1a2c\0\u1a5e\0\u1a90\0\u1ac2\0\144"+
    "\0\144\0\u1af4\0\u1b26\0\u1b58\0\144\0\u1b8a\0\u1bbc\0\u1bee"+
    "\0\u1c20\0\u1c52\0\u1c84\0\u1cb6\0\u1ce8\0\u1d1a\0\u1d4c\0\u1d7e"+
    "\0\u1db0\0\u1de2\0\u1e14\0\u1e46\0\u1e78\0\u1eaa\0\u1edc\0\u1f0e"+
    "\0\u1f40\0\u1f72\0\u1fa4\0\u1fd6\0\u2008\0\u203a\0\u206c\0\u209e"+
    "\0\u20d0\0\u2102\0\u2134\0\u2166\0\u2198\0\u21ca\0\u21fc\0\u222e"+
    "\0\u2260\0\u2292\0\u22c4\0\u22f6\0\u2328\0\144\0\u235a\0\u238c"+
    "\0\u23be\0\u23f0\0\u2422\0\u2454\0\144\0\u2486\0\u24b8\0\u24ea"+
    "\0\u251c\0\144\0\u254e\0\u2580\0\u25b2\0\u25e4\0\u2616\0\u2648"+
    "\0\u267a\0\144\0\u26ac\0\144\0\144\0\144\0\u26de\0\u2710"+
    "\0\u2742\0\u2774\0\u27a6\0\u27d8\0\144\0\u280a\0\u283c\0\144"+
    "\0\144\0\u286e\0\144\0\144\0\u28a0\0\144\0\144";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[279];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\16\1\17\2\3\1\20"+
    "\1\21\1\3\1\22\1\3\1\23\1\24\1\25\1\26"+
    "\5\3\1\27\1\30\1\31\1\3\1\32\1\33\1\34"+
    "\1\35\1\36\1\37\1\40\1\41\1\42\1\43\1\44"+
    "\1\45\1\46\1\47\1\50\1\51\63\0\2\3\6\0"+
    "\31\3\22\0\1\4\5\0\1\52\53\0\1\4\1\53"+
    "\4\0\1\52\53\0\1\4\1\0\1\54\3\0\1\52"+
    "\51\0\6\55\1\56\35\55\1\56\15\55\7\57\1\60"+
    "\52\57\2\0\1\61\60\0\2\3\6\0\1\3\1\62"+
    "\1\63\2\3\1\64\1\65\22\3\21\0\2\3\6\0"+
    "\5\3\1\66\1\3\1\67\1\70\20\3\21\0\2\3"+
    "\6\0\13\3\1\71\15\3\21\0\2\3\6\0\4\3"+
    "\1\72\1\73\1\74\11\3\1\75\10\3\21\0\2\3"+
    "\6\0\3\3\1\76\25\3\21\0\2\3\6\0\1\3"+
    "\1\77\4\3\1\100\11\3\1\101\10\3\21\0\2\3"+
    "\6\0\1\3\1\102\27\3\21\0\2\3\6\0\13\3"+
    "\1\103\15\3\21\0\2\3\6\0\5\3\1\104\1\105"+
    "\13\3\1\106\2\3\1\107\3\3\21\0\2\3\6\0"+
    "\1\3\1\110\2\3\1\111\13\3\1\112\10\3\21\0"+
    "\2\3\6\0\1\3\1\113\11\3\1\114\4\3\1\115"+
    "\10\3\21\0\2\3\6\0\20\3\1\116\4\3\1\117"+
    "\3\3\21\0\2\3\6\0\20\3\1\120\10\3\21\0"+
    "\2\3\6\0\22\3\1\121\6\3\21\0\2\3\6\0"+
    "\1\3\1\122\11\3\1\123\15\3\63\0\1\124\61\0"+
    "\1\125\65\0\1\126\62\0\1\127\54\0\1\130\61\0"+
    "\1\131\17\0\2\3\6\0\2\3\1\132\2\3\1\133"+
    "\23\3\21\0\2\3\6\0\20\3\1\134\10\3\21\0"+
    "\2\3\6\0\1\3\1\135\27\3\21\0\2\3\6\0"+
    "\7\3\1\136\21\3\21\0\2\3\6\0\1\137\30\3"+
    "\21\0\2\3\6\0\12\3\1\140\16\3\21\0\2\3"+
    "\6\0\16\3\1\141\12\3\21\0\2\3\6\0\6\3"+
    "\1\142\13\3\1\143\6\3\21\0\2\3\6\0\5\3"+
    "\1\144\23\3\21\0\2\3\6\0\6\3\1\145\22\3"+
    "\21\0\2\3\6\0\5\3\1\146\23\3\21\0\2\3"+
    "\6\0\13\3\1\147\15\3\21\0\2\3\6\0\2\3"+
    "\1\150\26\3\21\0\2\3\6\0\4\3\1\151\24\3"+
    "\21\0\2\3\6\0\16\3\1\152\12\3\21\0\2\3"+
    "\6\0\17\3\1\153\11\3\21\0\2\3\6\0\4\3"+
    "\1\154\24\3\21\0\2\3\6\0\6\3\1\155\22\3"+
    "\21\0\2\3\6\0\1\156\30\3\21\0\2\3\6\0"+
    "\2\3\1\157\26\3\21\0\2\3\6\0\1\3\1\160"+
    "\27\3\21\0\2\3\6\0\2\3\1\161\26\3\21\0"+
    "\2\3\6\0\24\3\1\162\4\3\21\0\2\3\6\0"+
    "\27\3\1\163\1\3\21\0\2\3\6\0\3\3\1\164"+
    "\25\3\21\0\2\3\6\0\6\3\1\165\22\3\21\0"+
    "\2\3\6\0\13\3\1\166\15\3\21\0\2\3\6\0"+
    "\4\3\1\167\24\3\21\0\2\3\6\0\7\3\1\170"+
    "\21\3\21\0\2\3\6\0\1\171\30\3\21\0\2\3"+
    "\6\0\15\3\1\172\13\3\21\0\2\3\6\0\20\3"+
    "\1\173\10\3\21\0\2\3\6\0\3\3\1\174\4\3"+
    "\1\175\20\3\21\0\2\3\6\0\1\3\1\176\27\3"+
    "\21\0\2\3\6\0\12\3\1\177\16\3\21\0\2\3"+
    "\6\0\1\3\1\200\27\3\21\0\2\3\6\0\1\3"+
    "\1\201\27\3\21\0\2\3\6\0\1\3\1\202\27\3"+
    "\21\0\2\3\6\0\2\3\1\203\26\3\21\0\2\3"+
    "\6\0\12\3\1\204\16\3\21\0\2\3\6\0\1\3"+
    "\1\205\27\3\21\0\2\3\6\0\5\3\1\206\23\3"+
    "\21\0\2\3\6\0\15\3\1\207\13\3\21\0\2\3"+
    "\6\0\1\210\30\3\21\0\2\3\6\0\25\3\1\211"+
    "\3\3\21\0\2\3\6\0\10\3\1\212\20\3\21\0"+
    "\2\3\6\0\1\3\1\213\27\3\21\0\2\3\6\0"+
    "\10\3\1\214\20\3\21\0\2\3\6\0\1\215\30\3"+
    "\21\0\2\3\6\0\1\3\1\216\27\3\21\0\2\3"+
    "\6\0\14\3\1\217\14\3\21\0\2\3\6\0\1\3"+
    "\1\220\1\221\1\222\25\3\21\0\2\3\6\0\1\3"+
    "\1\223\27\3\21\0\2\3\6\0\6\3\1\224\22\3"+
    "\21\0\2\3\6\0\1\3\1\225\27\3\21\0\2\3"+
    "\6\0\17\3\1\226\11\3\21\0\2\3\6\0\16\3"+
    "\1\227\12\3\21\0\2\3\6\0\5\3\1\230\23\3"+
    "\21\0\2\3\6\0\1\3\1\231\27\3\21\0\2\3"+
    "\6\0\2\3\1\232\26\3\21\0\2\3\6\0\4\3"+
    "\1\233\24\3\21\0\2\3\6\0\30\3\1\234\21\0"+
    "\2\3\6\0\4\3\1\235\24\3\21\0\2\3\6\0"+
    "\1\3\1\236\27\3\21\0\2\3\6\0\14\3\1\237"+
    "\14\3\21\0\2\3\6\0\12\3\1\240\16\3\21\0"+
    "\2\3\6\0\11\3\1\241\17\3\21\0\2\3\6\0"+
    "\2\3\1\242\10\3\1\243\1\244\14\3\21\0\2\3"+
    "\6\0\1\3\1\245\27\3\21\0\2\3\6\0\20\3"+
    "\1\246\10\3\21\0\2\3\6\0\5\3\1\247\23\3"+
    "\21\0\2\3\6\0\2\3\1\250\26\3\21\0\2\3"+
    "\6\0\1\251\30\3\21\0\2\3\6\0\30\3\1\252"+
    "\21\0\2\3\6\0\21\3\1\253\7\3\21\0\2\3"+
    "\6\0\1\3\1\254\27\3\21\0\2\3\6\0\4\3"+
    "\1\255\24\3\21\0\2\3\6\0\14\3\1\256\14\3"+
    "\21\0\2\3\6\0\14\3\1\257\14\3\21\0\2\3"+
    "\6\0\13\3\1\260\1\261\14\3\21\0\2\3\6\0"+
    "\1\3\1\262\27\3\21\0\2\3\6\0\10\3\1\263"+
    "\20\3\21\0\2\3\6\0\1\3\1\264\27\3\21\0"+
    "\2\3\6\0\5\3\1\265\23\3\21\0\2\3\6\0"+
    "\17\3\1\266\11\3\21\0\2\3\6\0\2\3\1\267"+
    "\26\3\21\0\2\3\6\0\13\3\1\270\15\3\21\0"+
    "\2\3\6\0\1\3\1\271\27\3\21\0\2\3\6\0"+
    "\4\3\1\272\24\3\21\0\2\3\6\0\16\3\1\273"+
    "\12\3\21\0\2\3\6\0\1\3\1\274\27\3\21\0"+
    "\2\3\6\0\2\3\1\275\26\3\21\0\2\3\6\0"+
    "\7\3\1\276\21\3\21\0\2\3\6\0\5\3\1\277"+
    "\23\3\21\0\2\3\6\0\30\3\1\300\21\0\2\3"+
    "\6\0\2\3\1\301\26\3\21\0\2\3\6\0\1\3"+
    "\1\302\1\303\26\3\21\0\2\3\6\0\4\3\1\304"+
    "\24\3\21\0\2\3\6\0\10\3\1\305\20\3\21\0"+
    "\2\3\6\0\6\3\1\306\22\3\21\0\2\3\6\0"+
    "\3\3\1\307\25\3\21\0\2\3\6\0\4\3\1\310"+
    "\24\3\21\0\2\3\6\0\14\3\1\311\14\3\21\0"+
    "\2\3\6\0\13\3\1\312\15\3\21\0\2\3\6\0"+
    "\13\3\1\313\15\3\21\0\2\3\6\0\13\3\1\314"+
    "\15\3\21\0\2\3\6\0\12\3\1\315\16\3\21\0"+
    "\2\3\6\0\6\3\1\316\22\3\21\0\2\3\6\0"+
    "\14\3\1\317\14\3\21\0\2\3\6\0\13\3\1\320"+
    "\15\3\21\0\2\3\6\0\13\3\1\321\15\3\21\0"+
    "\2\3\6\0\7\3\1\322\21\3\21\0\2\3\6\0"+
    "\4\3\1\323\24\3\21\0\2\3\6\0\6\3\1\324"+
    "\22\3\21\0\2\3\6\0\2\3\1\325\26\3\21\0"+
    "\2\3\6\0\14\3\1\326\14\3\21\0\2\3\6\0"+
    "\12\3\1\327\16\3\21\0\2\3\6\0\22\3\1\330"+
    "\6\3\21\0\2\3\6\0\1\3\1\331\27\3\21\0"+
    "\2\3\6\0\7\3\1\332\21\3\21\0\2\3\6\0"+
    "\2\3\1\333\26\3\21\0\2\3\6\0\4\3\1\334"+
    "\24\3\21\0\2\3\6\0\14\3\1\335\14\3\21\0"+
    "\2\3\6\0\1\3\1\336\27\3\21\0\2\3\6\0"+
    "\5\3\1\337\23\3\21\0\2\3\6\0\10\3\1\340"+
    "\20\3\21\0\2\3\6\0\6\3\1\341\22\3\21\0"+
    "\2\3\6\0\5\3\1\342\23\3\21\0\2\3\6\0"+
    "\20\3\1\343\10\3\21\0\2\3\6\0\1\344\30\3"+
    "\21\0\2\3\6\0\13\3\1\345\15\3\21\0\2\3"+
    "\6\0\1\3\1\346\27\3\21\0\2\3\6\0\6\3"+
    "\1\347\22\3\21\0\2\3\6\0\6\3\1\350\22\3"+
    "\21\0\2\3\6\0\5\3\1\351\23\3\21\0\2\3"+
    "\6\0\10\3\1\352\20\3\21\0\2\3\6\0\6\3"+
    "\1\353\22\3\21\0\2\3\6\0\15\3\1\354\13\3"+
    "\21\0\2\3\6\0\10\3\1\355\20\3\21\0\2\3"+
    "\6\0\1\3\1\356\27\3\21\0\2\3\6\0\13\3"+
    "\1\357\15\3\21\0\2\3\6\0\20\3\1\360\10\3"+
    "\21\0\2\3\6\0\6\3\1\361\22\3\21\0\2\3"+
    "\6\0\10\3\1\362\20\3\21\0\2\3\6\0\10\3"+
    "\1\363\20\3\21\0\2\3\6\0\10\3\1\364\20\3"+
    "\21\0\2\3\6\0\1\3\1\365\27\3\21\0\2\3"+
    "\6\0\2\3\1\366\26\3\21\0\2\3\6\0\7\3"+
    "\1\367\21\3\21\0\2\3\6\0\5\3\1\370\23\3"+
    "\21\0\2\3\6\0\15\3\1\371\13\3\21\0\2\3"+
    "\6\0\2\3\1\372\26\3\21\0\2\3\6\0\13\3"+
    "\1\373\15\3\21\0\2\3\6\0\2\3\1\374\26\3"+
    "\21\0\2\3\6\0\2\3\1\375\26\3\21\0\2\3"+
    "\6\0\15\3\1\376\13\3\21\0\2\3\6\0\15\3"+
    "\1\377\13\3\21\0\2\3\6\0\2\3\1\u0100\26\3"+
    "\21\0\2\3\6\0\1\3\1\u0101\27\3\21\0\2\3"+
    "\6\0\1\3\1\u0102\27\3\21\0\2\3\6\0\5\3"+
    "\1\u0103\23\3\21\0\2\3\6\0\11\3\1\u0104\7\3"+
    "\1\u0105\1\3\1\u0106\5\3\21\0\2\3\6\0\22\3"+
    "\1\u0107\6\3\21\0\2\3\6\0\2\3\1\u0108\26\3"+
    "\21\0\2\3\6\0\5\3\1\u0109\23\3\21\0\2\3"+
    "\6\0\5\3\1\u010a\23\3\21\0\2\3\6\0\11\3"+
    "\1\u010b\17\3\21\0\2\3\6\0\10\3\1\u010c\20\3"+
    "\21\0\2\3\6\0\1\3\1\u010d\27\3\21\0\2\3"+
    "\6\0\13\3\1\u010e\15\3\21\0\2\3\6\0\11\3"+
    "\1\u010f\17\3\21\0\2\3\6\0\1\3\1\u0110\27\3"+
    "\21\0\2\3\6\0\1\3\1\u0111\27\3\21\0\2\3"+
    "\6\0\12\3\1\u0112\16\3\21\0\2\3\6\0\2\3"+
    "\1\u0113\26\3\21\0\2\3\6\0\1\3\1\u0114\27\3"+
    "\21\0\2\3\6\0\12\3\1\u0115\16\3\21\0\2\3"+
    "\6\0\1\3\1\u0116\27\3\21\0\2\3\6\0\1\3"+
    "\1\u0117\27\3\20\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[10450];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\4\1\1\11\22\1\1\11\2\1\1\11"+
    "\5\1\7\11\1\0\2\11\1\0\1\1\1\0\1\11"+
    "\43\1\6\11\276\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[279];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  LexerCup(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  LexerCup(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          zzR = false;
          break;
        case '\r':
          yyline++;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
          }
          break;
        default:
          zzR = false;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 40: 
          { return new Symbol(sym.STOP, yychar, yyline, yytext());
          }
        case 80: break;
        case 68: 
          { return new Symbol(sym.GETFILAMENT, yychar, yyline, yytext());
          }
        case 81: break;
        case 16: 
          { return new Symbol(sym.PARENTESIS_ABRE, yychar, yyline, yytext());
          }
        case 82: break;
        case 4: 
          { return new Symbol(sym.SIGNO_DE_SUMA, yychar, yyline, yytext());
          }
        case 83: break;
        case 35: 
          { return new Symbol(sym.END, yychar, yyline, yytext());
          }
        case 84: break;
        case 8: 
          { return new Symbol(sym.PUNTO, yychar, yyline, yytext());
          }
        case 85: break;
        case 5: 
          { return new Symbol(sym.SIGNO_DE_RESTA, yychar, yyline, yytext());
          }
        case 86: break;
        case 39: 
          { return new Symbol(sym.GET, yychar, yyline, yytext());
          }
        case 87: break;
        case 36: 
          { return new Symbol(sym.FOR, yychar, yyline, yytext());
          }
        case 88: break;
        case 34: 
          { return new Symbol(sym.MAYOR_O_IGUAL_QUE, yychar, yyline, yytext());
          }
        case 89: break;
        case 48: 
          { return new Symbol(sym.GIVE, yychar, yyline, yytext());
          }
        case 90: break;
        case 27: 
          { return new Symbol(sym.IF, yychar, yyline, yytext());
          }
        case 91: break;
        case 49: 
          { return new Symbol(sym.WORD, yychar, yyline, yytext());
          }
        case 92: break;
        case 54: 
          { return new Symbol(sym.FALSE, yychar, yyline, yytext());
          }
        case 93: break;
        case 72: 
          { return new Symbol(sym.GETEXTRUSORZ, yychar, yyline, yytext());
          }
        case 94: break;
        case 30: 
          { return new Symbol(sym.DIFERENTE_QUE, yychar, yyline, yytext());
          }
        case 95: break;
        case 2: 
          { return new Symbol(sym.IDENTIFICADOR, yychar, yyline, yytext());
          }
        case 96: break;
        case 12: 
          { return new Symbol(sym.SIGNO_DE_RESIDUO, yychar, yyline, yytext());
          }
        case 97: break;
        case 28: 
          { return new Symbol(sym.DO, yychar, yyline, yytext());
          }
        case 98: break;
        case 43: 
          { return new Symbol(sym.TRAP, yychar, yyline, yytext());
          }
        case 99: break;
        case 6: 
          { /*Ignore*/
          }
        case 100: break;
        case 64: 
          { return new Symbol(sym.BROKEN, yychar, yyline, yytext());
          }
        case 101: break;
        case 58: 
          { return new Symbol(sym.WHILE, yychar, yyline, yytext());
          }
        case 102: break;
        case 9: 
          { return new Symbol(sym.COMA, yychar, yyline, yytext());
          }
        case 103: break;
        case 11: 
          { return new Symbol(sym.SIGNO_DE_MULTIPLICACIÓN, yychar, yyline, yytext());
          }
        case 104: break;
        case 46: 
          { return new Symbol(sym.MAIN, yychar, yyline, yytext());
          }
        case 105: break;
        case 7: 
          { return new Symbol(sym.SIGNO_DE_DIVISIÓN, yychar, yyline, yytext());
          }
        case 106: break;
        case 22: 
          { return new Symbol(sym.PUNTOYCOMA, yychar, yyline, yytext());
          }
        case 107: break;
        case 24: 
          { return new Symbol(sym.MENOS_MENOS, yychar, yyline, yytext());
          }
        case 108: break;
        case 3: 
          { return new Symbol(sym.NUMERO, yychar, yyline,new Integer(yytext()));
          }
        case 109: break;
        case 50: 
          { return new Symbol(sym.HOME, yychar, yyline, yytext());
          }
        case 110: break;
        case 17: 
          { return new Symbol(sym.PARENTESIS_CIERRA, yychar, yyline, yytext());
          }
        case 111: break;
        case 53: 
          { return new Symbol(sym.EMPTY, yychar, yyline, yytext());
          }
        case 112: break;
        case 1: 
          { return new Symbol(sym.ERROR, yychar, yyline, yytext());
          }
        case 113: break;
        case 18: 
          { return new Symbol(sym.LLAVE_ABRE, yychar, yyline, yytext());
          }
        case 114: break;
        case 21: 
          { return new Symbol(sym.CORCHETE_CIERRA, yychar, yyline, yytext());
          }
        case 115: break;
        case 67: 
          { return new Symbol(sym.PRINTERPORT, yychar, yyline, yytext());
          }
        case 116: break;
        case 13: 
          { return new Symbol(sym.NOT, yychar, yyline, yytext());
          }
        case 117: break;
        case 14: 
          { return new Symbol(sym.MENOR_QUE, yychar, yyline, yytext());
          }
        case 118: break;
        case 73: 
          { return new Symbol(sym.FILLRECTANGLE, yychar, yyline, yytext());
          }
        case 119: break;
        case 51: 
          { return new Symbol(sym.JUST, yychar, yyline, yytext());
          }
        case 120: break;
        case 45: 
          { return new Symbol(sym.FLAG, yychar, yyline, yytext());
          }
        case 121: break;
        case 44: 
          { return new Symbol(sym.TRUE, yychar, yyline, yytext());
          }
        case 122: break;
        case 75: 
          { return new Symbol(sym.DRAWTRIANGLE, yychar, yyline, yytext());
          }
        case 123: break;
        case 23: 
          { return new Symbol(sym.MAS_MAS, yychar, yyline, yytext());
          }
        case 124: break;
        case 52: 
          { return new Symbol(sym.SLEEP, yychar, yyline, yytext());
          }
        case 125: break;
        case 79: 
          { return new Symbol(sym.GETFILAMENTTYPE, yychar, yyline, yytext());
          }
        case 126: break;
        case 47: 
          { return new Symbol(sym.CASE, yychar, yyline, yytext());
          }
        case 127: break;
        case 42: 
          { return new Symbol(sym.ELSE, yychar, yyline, yytext());
          }
        case 128: break;
        case 37: 
          { return new Symbol(sym.NEW, yychar, yyline, yytext());
          }
        case 129: break;
        case 69: 
          { return new Symbol(sym.FILLTRIANGLE, yychar, yyline, yytext());
          }
        case 130: break;
        case 31: 
          { return new Symbol(sym.AND, yychar, yyline, yytext());
          }
        case 131: break;
        case 25: 
          { return new Symbol(sym.CADENA_TEXTO,      yychar, yyline, yytext());
          }
        case 132: break;
        case 55: 
          { return new Symbol(sym.MODEL, yychar, yyline, yytext());
          }
        case 133: break;
        case 78: 
          { return new Symbol(sym.SETFILAMENTTYPE, yychar, yyline, yytext());
          }
        case 134: break;
        case 77: 
          { return new Symbol(sym.GETTEMPERATURE, yychar, yyline, yytext());
          }
        case 135: break;
        case 26: 
          { return new Symbol(sym.DECIMAL, yychar, yyline, new Double(yytext()));
          }
        case 136: break;
        case 74: 
          { return new Symbol(sym.DRAWRECTANGLE, yychar, yyline, yytext());
          }
        case 137: break;
        case 71: 
          { return new Symbol(sym.GETEXTRUSORX, yychar, yyline, yytext());
          }
        case 138: break;
        case 61: 
          { return new Symbol(sym.METHOD, yychar, yyline, yytext());
          }
        case 139: break;
        case 19: 
          { return new Symbol(sym.LLAVE_CIERRA, yychar, yyline, yytext());
          }
        case 140: break;
        case 62: 
          { return new Symbol(sym.GOBACK, yychar, yyline, yytext());
          }
        case 141: break;
        case 10: 
          { return new Symbol(sym.SIGNO_DE_IGUAL, yychar, yyline, yytext());
          }
        case 142: break;
        case 41: 
          { return new Symbol(sym.SAME, yychar, yyline, yytext());
          }
        case 143: break;
        case 63: 
          { return new Symbol(sym.DEFECT, yychar, yyline, yytext());
          }
        case 144: break;
        case 76: 
          { return new Symbol(sym.SETNEWFILAMENT, yychar, yyline, yytext());
          }
        case 145: break;
        case 29: 
          { return new Symbol(sym.IGUAL_QUE, yychar, yyline, yytext());
          }
        case 146: break;
        case 65: 
          { return new Symbol(sym.FILLCIRCLE, yychar, yyline, yytext());
          }
        case 147: break;
        case 20: 
          { return new Symbol(sym.CORCHETE_ABRE, yychar, yyline, yytext());
          }
        case 148: break;
        case 15: 
          { return new Symbol(sym.MAYOR_QUE, yychar, yyline, yytext());
          }
        case 149: break;
        case 70: 
          { return new Symbol(sym.GETEXTRUSORY, yychar, yyline, yytext());
          }
        case 150: break;
        case 32: 
          { return new Symbol(sym.OR, yychar, yyline, yytext());
          }
        case 151: break;
        case 56: 
          { return new Symbol(sym.CLASS, yychar, yyline, yytext());
          }
        case 152: break;
        case 38: 
          { return new Symbol(sym.CUT, yychar, yyline, yytext());
          }
        case 153: break;
        case 60: 
          { return new Symbol(sym.SELECT, yychar, yyline, yytext());
          }
        case 154: break;
        case 66: 
          { return new Symbol(sym.DRAWCIRCLE, yychar, yyline, yytext());
          }
        case 155: break;
        case 33: 
          { return new Symbol(sym.MENOR_O_IGUAL_QUE, yychar, yyline, yytext());
          }
        case 156: break;
        case 57: 
          { return new Symbol(sym.CHECK, yychar, yyline, yytext());
          }
        case 157: break;
        case 59: 
          { return new Symbol(sym.BEGIN, yychar, yyline, yytext());
          }
        case 158: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return new java_cup.runtime.Symbol(sym.EOF); }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
