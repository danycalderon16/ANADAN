package codigo;
import java.io.*;
import static codigo.Tokens.*;
%%
%class Lexer
%type Tokens
%line
%column
L=[a-z_]+
D=[0-9]+
CAP=[A-Z]+
espacio=[ ,\t,\r,\n]+
num = {D}+

A=[/*]
C=[*/]

cadena = (\")~(\")
 
%{
    public String lexeme;
    InformacionLexema c = new InformacionLexema();
%}

%%

{espacio} {/*Ignore*/}
{A}[^]*{C} {/*Ignore*/}

<YYINITIAL> "word" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Word;}
<YYINITIAL> "setfilamenttype" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Setfilamenttype;}
<YYINITIAL> "fillrectangle" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Fillrectangle;}
<YYINITIAL> "drawrectangle" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Drawrectangle;}
<YYINITIAL> "sleep" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Sleep;}
<YYINITIAL> "fillcircle" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Fillcircle;}
<YYINITIAL> "drawcircle" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Drawcircle;}
<YYINITIAL> "drawtriangle" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Drawtriangle;}

<YYINITIAL> "filltriangle" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Filltriangle;}
<YYINITIAL> "stop" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Stop;}
<YYINITIAL> "getextrusorx" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Getextrusorx;}
<YYINITIAL> "getextrusory" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Getextrusory;}
<YYINITIAL> "getextrusorz" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Getextrusorz;}
<YYINITIAL> "getfilamenttype" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Getfilamenttype;}

<YYINITIAL> "setnewfilament" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Setnewfilament;}
<YYINITIAL> "getfilament" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Getfilament;}
<YYINITIAL> "gettemperature" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Gettemperature;}
<YYINITIAL> "end" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return End;}
<YYINITIAL> "same" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Same;}
<YYINITIAL> "get" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Get;}
<YYINITIAL> "give" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Give;}
<YYINITIAL> "for" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return For;}
<YYINITIAL> "if" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return If;}
<YYINITIAL> "else" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Else;}

<YYINITIAL> "select" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Select;}
<YYINITIAL> "empty" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Empty;}
<YYINITIAL> "while" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return While;}
<YYINITIAL> "case" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Case;}
<YYINITIAL> "flag" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Flag;}
<YYINITIAL> "cut" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Cut;}
<YYINITIAL> "just" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Just;}
<YYINITIAL> "begin" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Begin;}
<YYINITIAL> "model" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Model;}
<YYINITIAL> "defect" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Defect;}
<YYINITIAL> "new" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return New;}

<YYINITIAL> "do" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Do;}
<YYINITIAL> "goback" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Goback;}
<YYINITIAL> "broken" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Broken;}
<YYINITIAL> "home" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Home;}
<YYINITIAL> "printerport" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Printerport;}
<YYINITIAL> "method" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Method;}
<YYINITIAL> "check" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Check;}
<YYINITIAL> "trap" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Trap;}
<YYINITIAL> "class" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Class;}
<YYINITIAL> "true" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return True;}
<YYINITIAL> "false" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return False;}
<YYINITIAL> "main" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Main;}

<YYINITIAL> "\," {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Coma;}
<YYINITIAL> "." {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Punto;}

<YYINITIAL> "\n" {return Linea;}

<YYINITIAL> "=" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Signo_de_igual;}

<YYINITIAL> "+" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Signo_de_Suma;}
<YYINITIAL> "-" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Signo_de_Resta;}
<YYINITIAL>"*" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Signo_de_Multiplicación;}
<YYINITIAL>"/" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Signo_de_División;}

<YYINITIAL> "%" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Signo_de_Residuo;}
<YYINITIAL> "!" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return NOT;}
<YYINITIAL> "&&" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return AND;}
<YYINITIAL> "||" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return OR;}

<YYINITIAL> "++" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Mas_Mas;}
<YYINITIAL> "--" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Menos_Menos;}

<YYINITIAL> "<" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Menor_que;}
<YYINITIAL> ">" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Mayor_que;}
<YYINITIAL> "<=" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Menor_o_igual_que;}
<YYINITIAL> ">=" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Mayor_o_igual_que;}
<YYINITIAL> "!=" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Diferente_que;}
<YYINITIAL> "==" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Igual_que;}

<YYINITIAL> "(" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Parentesis_Abre;}
<YYINITIAL> ")" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Parentesis_Cierra;}
<YYINITIAL> "{" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Llave_Abre;}
<YYINITIAL> "}" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Llave_Cierra;}
<YYINITIAL> "[" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Corchete_Abre ;}
<YYINITIAL> "]" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Corchete_Cierra;}


<YYINITIAL> "\"" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return COMILLAS_DOBLES;}

<YYINITIAL> ";" {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return PuntoYComa;}


<YYINITIAL> {cadena} {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Cadena;} 

<YYINITIAL> {L}({L}|{D})* {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Identificador;}
<YYINITIAL> ("+"|"-")?{D}+|("+"|"-")?{D}+ "." {D}+ {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return Numero;}

<YYINITIAL> (("+-")|("-+"))({D}+|{D}+ "." {D}+) {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return NUMERO_ERRONEO;}
<YYINITIAL> (\.)*{num}?((\.*)|({num}))* {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return NUMERO_ERRONEO_MAS_PUNTOS;}
 
<YYINITIAL> (("+"|"-")?{L}+|("+"|"-")?{D}+ "." {D}+)("."|{D}|{L})+ {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return MAL_NOMBRE_PARA_IDENTIFICADOR;} 

<YYINITIAL> (("+"|"-")?{D}+|("+"|"-")?{D}+ "." {D}+){L}({L}|{D})* {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return MAL_NOMBRE_PARA_IDENTIFICADOR;}

<YYINITIAL> {CAP}{L}({L}|{D})* {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return MAL_NOMBRE_PARA_IDENTIFICADOR;}

<YYINITIAL> \"\$\&\?\¿\%{L}({L}|{D})* {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return MAL_NOMBRE_PARA_IDENTIFICADOR;} 

<YYINITIAL> {L}{CAP}+{L}* {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return MAYUSCULAS_EN_CADENA;}

. {c.linea=yyline;c.columna=yycolumn;lexeme=yytext(); return ERROR;}

