package codigo;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char

//******* ALFABETO ****************
L=[a-z_]+
D = [0-9]
num = {D}+
S = \+|\-
CAP=[A-Z]+

//******** IGNORAR *******************
espacio=[ \t\r\n]

//*********** COMENTARIOS ***************
A=[/*]
C=[*/]


cadena = (\")~(\")
enteros = {S}?{num}
numDec =  {S}?{num}?\.{num}
 
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%

//*********** terminales ***************
({cadena})       {return new Symbol(sym.CADENA_TEXTO,      yychar, yyline, yytext());}
setfilamenttype {return new Symbol(sym.SETFILAMENTTYPE, yychar, yyline, yytext());}
"fillrectangle" {return new Symbol(sym.FILLRECTANGLE, yychar, yyline, yytext());}
"drawrectangle" {return new Symbol(sym.DRAWRECTANGLE, yychar, yyline, yytext());}
"sleep" {return new Symbol(sym.SLEEP, yychar, yyline, yytext());}
"fillcircle" {return new Symbol(sym.FILLCIRCLE, yychar, yyline, yytext());}
"drawcircle" {return new Symbol(sym.DRAWCIRCLE, yychar, yyline, yytext());}
"drawrtriangle" {return new Symbol(sym.DRAWTRIANGLE, yychar, yyline, yytext());}

"filltriangle" {return new Symbol(sym.FILLTRIANGLE, yychar, yyline, yytext());}
"stop" {return new Symbol(sym.STOP, yychar, yyline, yytext());}
"getextrusorx" {return new Symbol(sym.GETEXTRUSORX, yychar, yyline, yytext());}
"getextrusory" {return new Symbol(sym.GETEXTRUSORY, yychar, yyline, yytext());}
"getextrusorz" {return new Symbol(sym.GETEXTRUSORZ, yychar, yyline, yytext());}
"getfilamenttype" {return new Symbol(sym.GETFILAMENTTYPE, yychar, yyline, yytext());}

"setnewfilament" {return new Symbol(sym.SETNEWFILAMENT, yychar, yyline, yytext());}
"getfilament" {return new Symbol(sym.GETFILAMENT, yychar, yyline, yytext());}
"gettemperature" {return new Symbol(sym.GETTEMPERATURE, yychar, yyline, yytext());}
"end" {return new Symbol(sym.END, yychar, yyline, yytext());}
"same" {return new Symbol(sym.SAME, yychar, yyline, yytext());}
"get" {return new Symbol(sym.GET, yychar, yyline, yytext());}
"give" {return new Symbol(sym.GIVE, yychar, yyline, yytext());}
"for" {return new Symbol(sym.FOR, yychar, yyline, yytext());}
"if" {return new Symbol(sym.IF, yychar, yyline, yytext());}
"else" {return new Symbol(sym.ELSE, yychar, yyline, yytext());}
"true" {return new Symbol(sym.TRUE, yychar, yyline, yytext());}
"false" {return new Symbol(sym.FALSE, yychar, yyline, yytext());}


"select" {return new Symbol(sym.SELECT, yychar, yyline, yytext());}
"empty" {return new Symbol(sym.EMPTY, yychar, yyline, yytext());}
"while" {return new Symbol(sym.WHILE, yychar, yyline, yytext());}
"case" {return new Symbol(sym.CASE, yychar, yyline, yytext());}
"flag" {return new Symbol(sym.FLAG, yychar, yyline, yytext());}
"cut" {return new Symbol(sym.CUT, yychar, yyline, yytext());}
"just" {return new Symbol(sym.JUST, yychar, yyline, yytext());}
"word" {return new Symbol(sym.WORD, yychar, yyline, yytext());}
"begin" {return new Symbol(sym.BEGIN, yychar, yyline, yytext());}
"model" {return new Symbol(sym.MODEL, yychar, yyline, yytext());}
"defect" {return new Symbol(sym.DEFECT, yychar, yyline, yytext());}
"new" {return new Symbol(sym.NEW, yychar, yyline, yytext());}


"righttemple" {return new Symbol(sym.RIGHTTEMPLE, yychar, yyline, yytext());}
"rightrim" {return new Symbol(sym.RIGHTRIM, yychar, yyline, yytext());}
"leftrim" {return new Symbol(sym.LEFTRIM, yychar, yyline, yytext());}
"lefttemple" {return new Symbol(sym.LEFTTEMPLE, yychar, yyline, yytext());}
"bridge" {return new Symbol(sym.BRIDGE, yychar, yyline, yytext());}

"do" {return new Symbol(sym.DO, yychar, yyline, yytext());}
"goback" {return new Symbol(sym.GOBACK, yychar, yyline, yytext());}
"broken" {return new Symbol(sym.BROKEN, yychar, yyline, yytext());}
"home" {return new Symbol(sym.HOME, yychar, yyline, yytext());}
"printerport" {return new Symbol(sym.PRINTERPORT, yychar, yyline, yytext());}
"method" {return new Symbol(sym.METHOD, yychar, yyline, yytext());}
"check" {return new Symbol(sym.CHECK, yychar, yyline, yytext());}
"trap" {return new Symbol(sym.TRAP, yychar, yyline, yytext());}
"class" {return new Symbol(sym.CLASS, yychar, yyline, yytext());}
"main" {return new Symbol(sym.MAIN, yychar, yyline, yytext());}
"," {return new Symbol(sym.COMA, yychar, yyline, yytext());}
"." {return new Symbol(sym.PUNTO, yychar, yyline, yytext());}
{espacio} {/*Ignore*/}

{A}[^]*{C} {/*Ignore*/}


"=" {return new Symbol(sym.SIGNO_DE_IGUAL, yychar, yyline, yytext());}

"+" {return new Symbol(sym.SIGNO_DE_SUMA, yychar, yyline, yytext());}
"-" {return new Symbol(sym.SIGNO_DE_RESTA, yychar, yyline, yytext());}
"*" {return new Symbol(sym.SIGNO_DE_MULTIPLICACIÓN, yychar, yyline, yytext());}
"/" {return new Symbol(sym.SIGNO_DE_DIVISIÓN, yychar, yyline, yytext());}
"^" {return new Symbol(sym.SIGNO_DE_EXPONENTE, yychar, yyline, yytext());}

"%" {return new Symbol(sym.SIGNO_DE_RESIDUO, yychar, yyline, yytext());}
"!" {return new Symbol(sym.NOT, yychar, yyline, yytext());}
"&&" {return new Symbol(sym.AND, yychar, yyline, yytext());}
"||" {return new Symbol(sym.OR, yychar, yyline, yytext());}

"++" {return new Symbol(sym.MAS_MAS, yychar, yyline, yytext());}
"--" {return new Symbol(sym.MENOS_MENOS, yychar, yyline, yytext());}

"<" {return new Symbol(sym.MENOR_QUE, yychar, yyline, yytext());}
">" {return new Symbol(sym.MAYOR_QUE, yychar, yyline, yytext());}
"<=" {return new Symbol(sym.MENOR_O_IGUAL_QUE, yychar, yyline, yytext());}
">=" {return new Symbol(sym.MAYOR_O_IGUAL_QUE, yychar, yyline, yytext());}
"!=" {return new Symbol(sym.DIFERENTE_QUE, yychar, yyline, yytext());}
"==" {return new Symbol(sym.IGUAL_QUE, yychar, yyline, yytext());}

"(" {return new Symbol(sym.PARENTESIS_ABRE, yychar, yyline, yytext());}
")" {return new Symbol(sym.PARENTESIS_CIERRA, yychar, yyline, yytext());}
"{" {return new Symbol(sym.LLAVE_ABRE, yychar, yyline, yytext());}
"}" {return new Symbol(sym.LLAVE_CIERRA, yychar, yyline, yytext());}
"[" {return new Symbol(sym.CORCHETE_ABRE, yychar, yyline, yytext());}
"]" {return new Symbol(sym.CORCHETE_CIERRA, yychar, yyline, yytext());}

";" {return new Symbol(sym.PUNTOYCOMA, yychar, yyline, yytext());}

{L}({L}|{D})* {return new Symbol(sym.IDENTIFICADOR, yychar, yyline, yytext());}

({enteros})       {return new Symbol(sym.NUMERO, yychar, yyline,new Integer(yytext()));}
({numDec})       {return new Symbol(sym.DECIMAL, yychar, yyline, new Double(yytext()));}

. {return new Symbol(sym.ERROR, yychar, yyline, yytext());}
