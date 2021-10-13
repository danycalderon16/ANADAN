/* The following code was generated by JFlex 1.4.3 on 10/12/21 10:48 PM */

package codigo;
import java_cup.runtime.Symbol;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 10/12/21 10:48 PM from the specification file
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
     5, 39,  7,  0,  0, 38, 40,  0, 44, 45, 36,  3, 34,  4,  8,  6, 
     2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  0, 50, 42, 35, 43,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 48,  0, 49, 37,  1, 
     0, 15, 32, 21, 23, 10, 12, 22, 30, 13, 31, 33, 14, 16, 17, 25, 
    19,  1, 20,  9, 11, 27, 29, 24, 26, 18, 28, 46, 41, 47,  0,  0, 
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
    "\1\16\2\1\1\17\1\20\1\21\1\22\1\23\1\24"+
    "\1\25\1\26\1\27\1\0\1\30\1\31\1\0\1\6"+
    "\1\0\1\32\1\33\14\2\1\34\16\2\1\35\6\2"+
    "\1\36\1\37\1\40\1\41\1\42\1\43\7\2\1\44"+
    "\5\2\1\45\3\2\1\46\3\2\1\47\1\2\1\50"+
    "\15\2\1\51\1\2\1\52\1\53\1\2\1\54\1\55"+
    "\1\2\1\56\2\2\1\57\3\2\1\60\4\2\1\61"+
    "\3\2\1\62\1\2\1\63\1\64\5\2\1\65\1\66"+
    "\3\2\1\67\1\2\1\70\1\2\1\71\1\72\7\2"+
    "\1\73\1\74\3\2\1\75\3\2\1\76\4\2\1\77"+
    "\1\100\3\2\1\101\50\2\1\102\6\2\1\103\4\2"+
    "\1\104\2\2\1\105\4\2\1\106\1\2\1\107\1\110"+
    "\1\111\6\2\1\112\2\2\1\113\1\114\1\2\1\115"+
    "\1\116\1\2\1\117\1\120";

  private static int [] zzUnpackAction() {
    int [] result = new int[280];
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
    "\0\0\0\63\0\146\0\231\0\314\0\377\0\63\0\u0132"+
    "\0\u0165\0\u0198\0\u01cb\0\u01fe\0\u0231\0\u0264\0\u0297\0\u02ca"+
    "\0\u02fd\0\u0330\0\u0363\0\u0396\0\u03c9\0\u03fc\0\u042f\0\u0462"+
    "\0\u0495\0\63\0\u04c8\0\u0132\0\63\0\63\0\u04fb\0\u052e"+
    "\0\u0561\0\u0594\0\u05c7\0\63\0\63\0\63\0\63\0\63"+
    "\0\63\0\63\0\u0198\0\63\0\63\0\u0132\0\u0132\0\u0165"+
    "\0\63\0\u0198\0\u05fa\0\u062d\0\u0660\0\u0693\0\u06c6\0\u06f9"+
    "\0\u072c\0\u075f\0\u0792\0\u07c5\0\u07f8\0\u082b\0\146\0\u085e"+
    "\0\u0891\0\u08c4\0\u08f7\0\u092a\0\u095d\0\u0990\0\u09c3\0\u09f6"+
    "\0\u0a29\0\u0a5c\0\u0a8f\0\u0ac2\0\u0af5\0\146\0\u0b28\0\u0b5b"+
    "\0\u0b8e\0\u0bc1\0\u0bf4\0\u0c27\0\63\0\63\0\63\0\63"+
    "\0\63\0\63\0\u0c5a\0\u0c8d\0\u0cc0\0\u0cf3\0\u0d26\0\u0d59"+
    "\0\u0d8c\0\146\0\u0dbf\0\u0df2\0\u0e25\0\u0e58\0\u0e8b\0\146"+
    "\0\u0ebe\0\u0ef1\0\u0f24\0\146\0\u0f57\0\u0f8a\0\u0fbd\0\146"+
    "\0\u0ff0\0\u1023\0\u1056\0\u1089\0\u10bc\0\u10ef\0\u1122\0\u1155"+
    "\0\u1188\0\u11bb\0\u11ee\0\u1221\0\u1254\0\u1287\0\u12ba\0\146"+
    "\0\u12ed\0\146\0\146\0\u1320\0\146\0\146\0\u1353\0\146"+
    "\0\u1386\0\u13b9\0\146\0\u13ec\0\u141f\0\u1452\0\146\0\u1485"+
    "\0\u14b8\0\u14eb\0\u151e\0\146\0\u1551\0\u1584\0\u15b7\0\146"+
    "\0\u15ea\0\146\0\146\0\u161d\0\u1650\0\u1683\0\u16b6\0\u16e9"+
    "\0\146\0\146\0\u171c\0\u174f\0\u1782\0\146\0\u17b5\0\146"+
    "\0\u17e8\0\146\0\146\0\u181b\0\u184e\0\u1881\0\u18b4\0\u18e7"+
    "\0\u191a\0\u194d\0\146\0\146\0\u1980\0\u19b3\0\u19e6\0\146"+
    "\0\u1a19\0\u1a4c\0\u1a7f\0\146\0\u1ab2\0\u1ae5\0\u1b18\0\u1b4b"+
    "\0\146\0\146\0\u1b7e\0\u1bb1\0\u1be4\0\146\0\u1c17\0\u1c4a"+
    "\0\u1c7d\0\u1cb0\0\u1ce3\0\u1d16\0\u1d49\0\u1d7c\0\u1daf\0\u1de2"+
    "\0\u1e15\0\u1e48\0\u1e7b\0\u1eae\0\u1ee1\0\u1f14\0\u1f47\0\u1f7a"+
    "\0\u1fad\0\u1fe0\0\u2013\0\u2046\0\u2079\0\u20ac\0\u20df\0\u2112"+
    "\0\u2145\0\u2178\0\u21ab\0\u21de\0\u2211\0\u2244\0\u2277\0\u22aa"+
    "\0\u22dd\0\u2310\0\u2343\0\u2376\0\u23a9\0\u23dc\0\146\0\u240f"+
    "\0\u2442\0\u2475\0\u24a8\0\u24db\0\u250e\0\146\0\u2541\0\u2574"+
    "\0\u25a7\0\u25da\0\146\0\u260d\0\u2640\0\u2673\0\u26a6\0\u26d9"+
    "\0\u270c\0\u273f\0\146\0\u2772\0\146\0\146\0\146\0\u27a5"+
    "\0\u27d8\0\u280b\0\u283e\0\u2871\0\u28a4\0\146\0\u28d7\0\u290a"+
    "\0\146\0\146\0\u293d\0\146\0\146\0\u2970\0\146\0\146";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[280];
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
    "\1\45\1\46\1\47\1\50\1\51\1\52\64\0\2\3"+
    "\6\0\31\3\23\0\1\4\5\0\1\53\54\0\1\4"+
    "\1\54\4\0\1\53\54\0\1\4\1\0\1\55\3\0"+
    "\1\53\52\0\6\56\1\57\35\56\1\57\16\56\7\60"+
    "\1\61\53\60\2\0\1\62\61\0\2\3\6\0\1\3"+
    "\1\63\1\64\2\3\1\65\1\66\22\3\22\0\2\3"+
    "\6\0\5\3\1\67\1\3\1\70\1\71\20\3\22\0"+
    "\2\3\6\0\13\3\1\72\15\3\22\0\2\3\6\0"+
    "\4\3\1\73\1\74\1\75\11\3\1\76\10\3\22\0"+
    "\2\3\6\0\3\3\1\77\25\3\22\0\2\3\6\0"+
    "\1\3\1\100\4\3\1\101\11\3\1\102\10\3\22\0"+
    "\2\3\6\0\1\3\1\103\27\3\22\0\2\3\6\0"+
    "\13\3\1\104\15\3\22\0\2\3\6\0\5\3\1\105"+
    "\1\106\13\3\1\107\2\3\1\110\3\3\22\0\2\3"+
    "\6\0\1\3\1\111\2\3\1\112\13\3\1\113\10\3"+
    "\22\0\2\3\6\0\1\3\1\114\11\3\1\115\4\3"+
    "\1\116\10\3\22\0\2\3\6\0\20\3\1\117\4\3"+
    "\1\120\3\3\22\0\2\3\6\0\20\3\1\121\10\3"+
    "\22\0\2\3\6\0\22\3\1\122\6\3\22\0\2\3"+
    "\6\0\1\3\1\123\11\3\1\124\15\3\64\0\1\125"+
    "\62\0\1\126\67\0\1\127\63\0\1\130\54\0\1\131"+
    "\62\0\1\132\20\0\2\3\6\0\2\3\1\133\2\3"+
    "\1\134\23\3\22\0\2\3\6\0\20\3\1\135\10\3"+
    "\22\0\2\3\6\0\1\3\1\136\27\3\22\0\2\3"+
    "\6\0\7\3\1\137\21\3\22\0\2\3\6\0\1\140"+
    "\30\3\22\0\2\3\6\0\12\3\1\141\16\3\22\0"+
    "\2\3\6\0\16\3\1\142\12\3\22\0\2\3\6\0"+
    "\6\3\1\143\13\3\1\144\6\3\22\0\2\3\6\0"+
    "\5\3\1\145\23\3\22\0\2\3\6\0\6\3\1\146"+
    "\22\3\22\0\2\3\6\0\5\3\1\147\23\3\22\0"+
    "\2\3\6\0\13\3\1\150\15\3\22\0\2\3\6\0"+
    "\2\3\1\151\26\3\22\0\2\3\6\0\4\3\1\152"+
    "\24\3\22\0\2\3\6\0\16\3\1\153\12\3\22\0"+
    "\2\3\6\0\17\3\1\154\11\3\22\0\2\3\6\0"+
    "\4\3\1\155\24\3\22\0\2\3\6\0\6\3\1\156"+
    "\22\3\22\0\2\3\6\0\1\157\30\3\22\0\2\3"+
    "\6\0\2\3\1\160\26\3\22\0\2\3\6\0\1\3"+
    "\1\161\27\3\22\0\2\3\6\0\2\3\1\162\26\3"+
    "\22\0\2\3\6\0\24\3\1\163\4\3\22\0\2\3"+
    "\6\0\27\3\1\164\1\3\22\0\2\3\6\0\3\3"+
    "\1\165\25\3\22\0\2\3\6\0\6\3\1\166\22\3"+
    "\22\0\2\3\6\0\13\3\1\167\15\3\22\0\2\3"+
    "\6\0\4\3\1\170\24\3\22\0\2\3\6\0\7\3"+
    "\1\171\21\3\22\0\2\3\6\0\1\172\30\3\22\0"+
    "\2\3\6\0\15\3\1\173\13\3\22\0\2\3\6\0"+
    "\20\3\1\174\10\3\22\0\2\3\6\0\3\3\1\175"+
    "\4\3\1\176\20\3\22\0\2\3\6\0\1\3\1\177"+
    "\27\3\22\0\2\3\6\0\12\3\1\200\16\3\22\0"+
    "\2\3\6\0\1\3\1\201\27\3\22\0\2\3\6\0"+
    "\1\3\1\202\27\3\22\0\2\3\6\0\1\3\1\203"+
    "\27\3\22\0\2\3\6\0\2\3\1\204\26\3\22\0"+
    "\2\3\6\0\12\3\1\205\16\3\22\0\2\3\6\0"+
    "\1\3\1\206\27\3\22\0\2\3\6\0\5\3\1\207"+
    "\23\3\22\0\2\3\6\0\15\3\1\210\13\3\22\0"+
    "\2\3\6\0\1\211\30\3\22\0\2\3\6\0\25\3"+
    "\1\212\3\3\22\0\2\3\6\0\10\3\1\213\20\3"+
    "\22\0\2\3\6\0\1\3\1\214\27\3\22\0\2\3"+
    "\6\0\10\3\1\215\20\3\22\0\2\3\6\0\1\216"+
    "\30\3\22\0\2\3\6\0\1\3\1\217\27\3\22\0"+
    "\2\3\6\0\14\3\1\220\14\3\22\0\2\3\6\0"+
    "\1\3\1\221\1\222\1\223\25\3\22\0\2\3\6\0"+
    "\1\3\1\224\27\3\22\0\2\3\6\0\6\3\1\225"+
    "\22\3\22\0\2\3\6\0\1\3\1\226\27\3\22\0"+
    "\2\3\6\0\17\3\1\227\11\3\22\0\2\3\6\0"+
    "\16\3\1\230\12\3\22\0\2\3\6\0\5\3\1\231"+
    "\23\3\22\0\2\3\6\0\1\3\1\232\27\3\22\0"+
    "\2\3\6\0\2\3\1\233\26\3\22\0\2\3\6\0"+
    "\4\3\1\234\24\3\22\0\2\3\6\0\30\3\1\235"+
    "\22\0\2\3\6\0\4\3\1\236\24\3\22\0\2\3"+
    "\6\0\1\3\1\237\27\3\22\0\2\3\6\0\14\3"+
    "\1\240\14\3\22\0\2\3\6\0\12\3\1\241\16\3"+
    "\22\0\2\3\6\0\11\3\1\242\17\3\22\0\2\3"+
    "\6\0\2\3\1\243\10\3\1\244\1\245\14\3\22\0"+
    "\2\3\6\0\1\3\1\246\27\3\22\0\2\3\6\0"+
    "\20\3\1\247\10\3\22\0\2\3\6\0\5\3\1\250"+
    "\23\3\22\0\2\3\6\0\2\3\1\251\26\3\22\0"+
    "\2\3\6\0\1\252\30\3\22\0\2\3\6\0\30\3"+
    "\1\253\22\0\2\3\6\0\21\3\1\254\7\3\22\0"+
    "\2\3\6\0\1\3\1\255\27\3\22\0\2\3\6\0"+
    "\4\3\1\256\24\3\22\0\2\3\6\0\14\3\1\257"+
    "\14\3\22\0\2\3\6\0\14\3\1\260\14\3\22\0"+
    "\2\3\6\0\13\3\1\261\1\262\14\3\22\0\2\3"+
    "\6\0\1\3\1\263\27\3\22\0\2\3\6\0\10\3"+
    "\1\264\20\3\22\0\2\3\6\0\1\3\1\265\27\3"+
    "\22\0\2\3\6\0\5\3\1\266\23\3\22\0\2\3"+
    "\6\0\17\3\1\267\11\3\22\0\2\3\6\0\2\3"+
    "\1\270\26\3\22\0\2\3\6\0\13\3\1\271\15\3"+
    "\22\0\2\3\6\0\1\3\1\272\27\3\22\0\2\3"+
    "\6\0\4\3\1\273\24\3\22\0\2\3\6\0\16\3"+
    "\1\274\12\3\22\0\2\3\6\0\1\3\1\275\27\3"+
    "\22\0\2\3\6\0\2\3\1\276\26\3\22\0\2\3"+
    "\6\0\7\3\1\277\21\3\22\0\2\3\6\0\5\3"+
    "\1\300\23\3\22\0\2\3\6\0\30\3\1\301\22\0"+
    "\2\3\6\0\2\3\1\302\26\3\22\0\2\3\6\0"+
    "\1\3\1\303\1\304\26\3\22\0\2\3\6\0\4\3"+
    "\1\305\24\3\22\0\2\3\6\0\10\3\1\306\20\3"+
    "\22\0\2\3\6\0\6\3\1\307\22\3\22\0\2\3"+
    "\6\0\3\3\1\310\25\3\22\0\2\3\6\0\4\3"+
    "\1\311\24\3\22\0\2\3\6\0\14\3\1\312\14\3"+
    "\22\0\2\3\6\0\13\3\1\313\15\3\22\0\2\3"+
    "\6\0\13\3\1\314\15\3\22\0\2\3\6\0\13\3"+
    "\1\315\15\3\22\0\2\3\6\0\12\3\1\316\16\3"+
    "\22\0\2\3\6\0\6\3\1\317\22\3\22\0\2\3"+
    "\6\0\14\3\1\320\14\3\22\0\2\3\6\0\13\3"+
    "\1\321\15\3\22\0\2\3\6\0\13\3\1\322\15\3"+
    "\22\0\2\3\6\0\7\3\1\323\21\3\22\0\2\3"+
    "\6\0\4\3\1\324\24\3\22\0\2\3\6\0\6\3"+
    "\1\325\22\3\22\0\2\3\6\0\2\3\1\326\26\3"+
    "\22\0\2\3\6\0\14\3\1\327\14\3\22\0\2\3"+
    "\6\0\12\3\1\330\16\3\22\0\2\3\6\0\22\3"+
    "\1\331\6\3\22\0\2\3\6\0\1\3\1\332\27\3"+
    "\22\0\2\3\6\0\7\3\1\333\21\3\22\0\2\3"+
    "\6\0\2\3\1\334\26\3\22\0\2\3\6\0\4\3"+
    "\1\335\24\3\22\0\2\3\6\0\14\3\1\336\14\3"+
    "\22\0\2\3\6\0\1\3\1\337\27\3\22\0\2\3"+
    "\6\0\5\3\1\340\23\3\22\0\2\3\6\0\10\3"+
    "\1\341\20\3\22\0\2\3\6\0\6\3\1\342\22\3"+
    "\22\0\2\3\6\0\5\3\1\343\23\3\22\0\2\3"+
    "\6\0\20\3\1\344\10\3\22\0\2\3\6\0\1\345"+
    "\30\3\22\0\2\3\6\0\13\3\1\346\15\3\22\0"+
    "\2\3\6\0\1\3\1\347\27\3\22\0\2\3\6\0"+
    "\6\3\1\350\22\3\22\0\2\3\6\0\6\3\1\351"+
    "\22\3\22\0\2\3\6\0\5\3\1\352\23\3\22\0"+
    "\2\3\6\0\10\3\1\353\20\3\22\0\2\3\6\0"+
    "\6\3\1\354\22\3\22\0\2\3\6\0\15\3\1\355"+
    "\13\3\22\0\2\3\6\0\10\3\1\356\20\3\22\0"+
    "\2\3\6\0\1\3\1\357\27\3\22\0\2\3\6\0"+
    "\13\3\1\360\15\3\22\0\2\3\6\0\20\3\1\361"+
    "\10\3\22\0\2\3\6\0\6\3\1\362\22\3\22\0"+
    "\2\3\6\0\10\3\1\363\20\3\22\0\2\3\6\0"+
    "\10\3\1\364\20\3\22\0\2\3\6\0\10\3\1\365"+
    "\20\3\22\0\2\3\6\0\1\3\1\366\27\3\22\0"+
    "\2\3\6\0\2\3\1\367\26\3\22\0\2\3\6\0"+
    "\7\3\1\370\21\3\22\0\2\3\6\0\5\3\1\371"+
    "\23\3\22\0\2\3\6\0\15\3\1\372\13\3\22\0"+
    "\2\3\6\0\2\3\1\373\26\3\22\0\2\3\6\0"+
    "\13\3\1\374\15\3\22\0\2\3\6\0\2\3\1\375"+
    "\26\3\22\0\2\3\6\0\2\3\1\376\26\3\22\0"+
    "\2\3\6\0\15\3\1\377\13\3\22\0\2\3\6\0"+
    "\15\3\1\u0100\13\3\22\0\2\3\6\0\2\3\1\u0101"+
    "\26\3\22\0\2\3\6\0\1\3\1\u0102\27\3\22\0"+
    "\2\3\6\0\1\3\1\u0103\27\3\22\0\2\3\6\0"+
    "\5\3\1\u0104\23\3\22\0\2\3\6\0\11\3\1\u0105"+
    "\7\3\1\u0106\1\3\1\u0107\5\3\22\0\2\3\6\0"+
    "\22\3\1\u0108\6\3\22\0\2\3\6\0\2\3\1\u0109"+
    "\26\3\22\0\2\3\6\0\5\3\1\u010a\23\3\22\0"+
    "\2\3\6\0\5\3\1\u010b\23\3\22\0\2\3\6\0"+
    "\11\3\1\u010c\17\3\22\0\2\3\6\0\10\3\1\u010d"+
    "\20\3\22\0\2\3\6\0\1\3\1\u010e\27\3\22\0"+
    "\2\3\6\0\13\3\1\u010f\15\3\22\0\2\3\6\0"+
    "\11\3\1\u0110\17\3\22\0\2\3\6\0\1\3\1\u0111"+
    "\27\3\22\0\2\3\6\0\1\3\1\u0112\27\3\22\0"+
    "\2\3\6\0\12\3\1\u0113\16\3\22\0\2\3\6\0"+
    "\2\3\1\u0114\26\3\22\0\2\3\6\0\1\3\1\u0115"+
    "\27\3\22\0\2\3\6\0\12\3\1\u0116\16\3\22\0"+
    "\2\3\6\0\1\3\1\u0117\27\3\22\0\2\3\6\0"+
    "\1\3\1\u0118\27\3\21\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[10659];
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
    "\1\0\1\11\4\1\1\11\22\1\1\11\2\1\2\11"+
    "\5\1\7\11\1\0\2\11\1\0\1\1\1\0\1\11"+
    "\43\1\6\11\276\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[280];
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
        case 41: 
          { return new Symbol(sym.STOP, yychar, yyline, yytext());
          }
        case 81: break;
        case 69: 
          { return new Symbol(sym.GETFILAMENT, yychar, yyline, yytext());
          }
        case 82: break;
        case 17: 
          { return new Symbol(sym.PARENTESIS_ABRE, yychar, yyline, yytext());
          }
        case 83: break;
        case 4: 
          { return new Symbol(sym.SIGNO_DE_SUMA, yychar, yyline, yytext());
          }
        case 84: break;
        case 36: 
          { return new Symbol(sym.END, yychar, yyline, yytext());
          }
        case 85: break;
        case 8: 
          { return new Symbol(sym.PUNTO, yychar, yyline, yytext());
          }
        case 86: break;
        case 5: 
          { return new Symbol(sym.SIGNO_DE_RESTA, yychar, yyline, yytext());
          }
        case 87: break;
        case 40: 
          { return new Symbol(sym.GET, yychar, yyline, yytext());
          }
        case 88: break;
        case 37: 
          { return new Symbol(sym.FOR, yychar, yyline, yytext());
          }
        case 89: break;
        case 35: 
          { return new Symbol(sym.MAYOR_O_IGUAL_QUE, yychar, yyline, yytext());
          }
        case 90: break;
        case 49: 
          { return new Symbol(sym.GIVE, yychar, yyline, yytext());
          }
        case 91: break;
        case 28: 
          { return new Symbol(sym.IF, yychar, yyline, yytext());
          }
        case 92: break;
        case 50: 
          { return new Symbol(sym.WORD, yychar, yyline, yytext());
          }
        case 93: break;
        case 55: 
          { return new Symbol(sym.FALSE, yychar, yyline, yytext());
          }
        case 94: break;
        case 73: 
          { return new Symbol(sym.GETEXTRUSORZ, yychar, yyline, yytext());
          }
        case 95: break;
        case 31: 
          { return new Symbol(sym.DIFERENTE_QUE, yychar, yyline, yytext());
          }
        case 96: break;
        case 2: 
          { return new Symbol(sym.IDENTIFICADOR, yychar, yyline, yytext());
          }
        case 97: break;
        case 13: 
          { return new Symbol(sym.SIGNO_DE_RESIDUO, yychar, yyline, yytext());
          }
        case 98: break;
        case 29: 
          { return new Symbol(sym.DO, yychar, yyline, yytext());
          }
        case 99: break;
        case 44: 
          { return new Symbol(sym.TRAP, yychar, yyline, yytext());
          }
        case 100: break;
        case 6: 
          { /*Ignore*/
          }
        case 101: break;
        case 65: 
          { return new Symbol(sym.BROKEN, yychar, yyline, yytext());
          }
        case 102: break;
        case 59: 
          { return new Symbol(sym.WHILE, yychar, yyline, yytext());
          }
        case 103: break;
        case 9: 
          { return new Symbol(sym.COMA, yychar, yyline, yytext());
          }
        case 104: break;
        case 11: 
          { return new Symbol(sym.SIGNO_DE_MULTIPLICACIÓN, yychar, yyline, yytext());
          }
        case 105: break;
        case 47: 
          { return new Symbol(sym.MAIN, yychar, yyline, yytext());
          }
        case 106: break;
        case 7: 
          { return new Symbol(sym.SIGNO_DE_DIVISIÓN, yychar, yyline, yytext());
          }
        case 107: break;
        case 23: 
          { return new Symbol(sym.PUNTOYCOMA, yychar, yyline, yytext());
          }
        case 108: break;
        case 25: 
          { return new Symbol(sym.MENOS_MENOS, yychar, yyline, yytext());
          }
        case 109: break;
        case 3: 
          { return new Symbol(sym.NUMERO, yychar, yyline,new Integer(yytext()));
          }
        case 110: break;
        case 51: 
          { return new Symbol(sym.HOME, yychar, yyline, yytext());
          }
        case 111: break;
        case 18: 
          { return new Symbol(sym.PARENTESIS_CIERRA, yychar, yyline, yytext());
          }
        case 112: break;
        case 54: 
          { return new Symbol(sym.EMPTY, yychar, yyline, yytext());
          }
        case 113: break;
        case 1: 
          { return new Symbol(sym.ERROR, yychar, yyline, yytext());
          }
        case 114: break;
        case 19: 
          { return new Symbol(sym.LLAVE_ABRE, yychar, yyline, yytext());
          }
        case 115: break;
        case 22: 
          { return new Symbol(sym.CORCHETE_CIERRA, yychar, yyline, yytext());
          }
        case 116: break;
        case 68: 
          { return new Symbol(sym.PRINTERPORT, yychar, yyline, yytext());
          }
        case 117: break;
        case 14: 
          { return new Symbol(sym.NOT, yychar, yyline, yytext());
          }
        case 118: break;
        case 15: 
          { return new Symbol(sym.MENOR_QUE, yychar, yyline, yytext());
          }
        case 119: break;
        case 74: 
          { return new Symbol(sym.FILLRECTANGLE, yychar, yyline, yytext());
          }
        case 120: break;
        case 52: 
          { return new Symbol(sym.JUST, yychar, yyline, yytext());
          }
        case 121: break;
        case 46: 
          { return new Symbol(sym.FLAG, yychar, yyline, yytext());
          }
        case 122: break;
        case 45: 
          { return new Symbol(sym.TRUE, yychar, yyline, yytext());
          }
        case 123: break;
        case 76: 
          { return new Symbol(sym.DRAWTRIANGLE, yychar, yyline, yytext());
          }
        case 124: break;
        case 12: 
          { return new Symbol(sym.SIGNO_DE_EXPONENTE, yychar, yyline, yytext());
          }
        case 125: break;
        case 24: 
          { return new Symbol(sym.MAS_MAS, yychar, yyline, yytext());
          }
        case 126: break;
        case 53: 
          { return new Symbol(sym.SLEEP, yychar, yyline, yytext());
          }
        case 127: break;
        case 80: 
          { return new Symbol(sym.GETFILAMENTTYPE, yychar, yyline, yytext());
          }
        case 128: break;
        case 48: 
          { return new Symbol(sym.CASE, yychar, yyline, yytext());
          }
        case 129: break;
        case 43: 
          { return new Symbol(sym.ELSE, yychar, yyline, yytext());
          }
        case 130: break;
        case 38: 
          { return new Symbol(sym.NEW, yychar, yyline, yytext());
          }
        case 131: break;
        case 70: 
          { return new Symbol(sym.FILLTRIANGLE, yychar, yyline, yytext());
          }
        case 132: break;
        case 32: 
          { return new Symbol(sym.AND, yychar, yyline, yytext());
          }
        case 133: break;
        case 26: 
          { return new Symbol(sym.CADENA_TEXTO,      yychar, yyline, yytext());
          }
        case 134: break;
        case 56: 
          { return new Symbol(sym.MODEL, yychar, yyline, yytext());
          }
        case 135: break;
        case 79: 
          { return new Symbol(sym.SETFILAMENTTYPE, yychar, yyline, yytext());
          }
        case 136: break;
        case 78: 
          { return new Symbol(sym.GETTEMPERATURE, yychar, yyline, yytext());
          }
        case 137: break;
        case 27: 
          { return new Symbol(sym.DECIMAL, yychar, yyline, new Double(yytext()));
          }
        case 138: break;
        case 75: 
          { return new Symbol(sym.DRAWRECTANGLE, yychar, yyline, yytext());
          }
        case 139: break;
        case 72: 
          { return new Symbol(sym.GETEXTRUSORX, yychar, yyline, yytext());
          }
        case 140: break;
        case 62: 
          { return new Symbol(sym.METHOD, yychar, yyline, yytext());
          }
        case 141: break;
        case 20: 
          { return new Symbol(sym.LLAVE_CIERRA, yychar, yyline, yytext());
          }
        case 142: break;
        case 63: 
          { return new Symbol(sym.GOBACK, yychar, yyline, yytext());
          }
        case 143: break;
        case 10: 
          { return new Symbol(sym.SIGNO_DE_IGUAL, yychar, yyline, yytext());
          }
        case 144: break;
        case 42: 
          { return new Symbol(sym.SAME, yychar, yyline, yytext());
          }
        case 145: break;
        case 64: 
          { return new Symbol(sym.DEFECT, yychar, yyline, yytext());
          }
        case 146: break;
        case 77: 
          { return new Symbol(sym.SETNEWFILAMENT, yychar, yyline, yytext());
          }
        case 147: break;
        case 30: 
          { return new Symbol(sym.IGUAL_QUE, yychar, yyline, yytext());
          }
        case 148: break;
        case 66: 
          { return new Symbol(sym.FILLCIRCLE, yychar, yyline, yytext());
          }
        case 149: break;
        case 21: 
          { return new Symbol(sym.CORCHETE_ABRE, yychar, yyline, yytext());
          }
        case 150: break;
        case 16: 
          { return new Symbol(sym.MAYOR_QUE, yychar, yyline, yytext());
          }
        case 151: break;
        case 71: 
          { return new Symbol(sym.GETEXTRUSORY, yychar, yyline, yytext());
          }
        case 152: break;
        case 33: 
          { return new Symbol(sym.OR, yychar, yyline, yytext());
          }
        case 153: break;
        case 57: 
          { return new Symbol(sym.CLASS, yychar, yyline, yytext());
          }
        case 154: break;
        case 39: 
          { return new Symbol(sym.CUT, yychar, yyline, yytext());
          }
        case 155: break;
        case 61: 
          { return new Symbol(sym.SELECT, yychar, yyline, yytext());
          }
        case 156: break;
        case 67: 
          { return new Symbol(sym.DRAWCIRCLE, yychar, yyline, yytext());
          }
        case 157: break;
        case 34: 
          { return new Symbol(sym.MENOR_O_IGUAL_QUE, yychar, yyline, yytext());
          }
        case 158: break;
        case 58: 
          { return new Symbol(sym.CHECK, yychar, yyline, yytext());
          }
        case 159: break;
        case 60: 
          { return new Symbol(sym.BEGIN, yychar, yyline, yytext());
          }
        case 160: break;
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
