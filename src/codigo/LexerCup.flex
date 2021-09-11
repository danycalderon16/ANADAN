package codigo;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
L=[a-z_]+
D=[0-9]+
CAP=[A-Z]+
espacio=[ ,\t,\r,\n]+

A=[/*]
C=[*/]

cadena = (\")~(\")
 
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%

setfilamenttype {return new Symbol(sym.Setfilamenttype, yychar, yyline, yytext());}
fillrectangle {return new Symbol(sym.Fillrectangle, yychar, yyline, yytext());}
drawrectangle {return new Symbol(sym.Drawrectangle, yychar, yyline, yytext());}
sleep {return new Symbol(sym.Sleep, yychar, yyline, yytext());}
fillcircle {return new Symbol(sym.Fillcircle, yychar, yyline, yytext());}
drawcircle {return new Symbol(sym.Drawcircle, yychar, yyline, yytext());}
drawrtriangle {return new Symbol(sym.Drawtriangle, yychar, yyline, yytext());}

filltriangle {return new Symbol(sym.Filltriangle, yychar, yyline, yytext());}
stop {return new Symbol(sym.Stop, yychar, yyline, yytext());}
getextrusorx {return new Symbol(sym.Getextrusorx, yychar, yyline, yytext());}
getextrusory {return new Symbol(sym.Getextrusory, yychar, yyline, yytext());}
getextrusorz {return new Symbol(sym.Getextrusorz, yychar, yyline, yytext());}
getfilamenttype {return new Symbol(sym.Getfilamenttype, yychar, yyline, yytext());}

setnewfilament {return new Symbol(sym.Setnewfilament, yychar, yyline, yytext());}
getfilament {return new Symbol(sym.Getfilament, yychar, yyline, yytext());}
gettemperature {return new Symbol(sym.Gettemperature, yychar, yyline, yytext());}
end {return new Symbol(sym.End, yychar, yyline, yytext());}
same {return new Symbol(sym.Same, yychar, yyline, yytext());}
get {return new Symbol(sym.Get, yychar, yyline, yytext());}
give {return new Symbol(sym.Give, yychar, yyline, yytext());}
for {return new Symbol(sym.For, yychar, yyline, yytext());}
if {return new Symbol(sym.If, yychar, yyline, yytext());}
else {return new Symbol(sym.Else, yychar, yyline, yytext());}

select {return new Symbol(sym.Select, yychar, yyline, yytext());}
empty {return new Symbol(sym.Empty, yychar, yyline, yytext());}
while {return new Symbol(sym.While, yychar, yyline, yytext());}
case {return new Symbol(sym.Case, yychar, yyline, yytext());}
flag {return new Symbol(sym.Flag, yychar, yyline, yytext());}
cut {return new Symbol(sym.Cut, yychar, yyline, yytext());}
just {return new Symbol(sym.Just, yychar, yyline, yytext());}
begin {return new Symbol(sym.Begin, yychar, yyline, yytext());}
model {return new Symbol(sym.Model, yychar, yyline, yytext());}
defect {return new Symbol(sym.Defect, yychar, yyline, yytext());}
new {return new Symbol(sym.New, yychar, yyline, yytext());}

do {return new Symbol(sym.Do, yychar, yyline, yytext());}
goback {return new Symbol(sym.Goback, yychar, yyline, yytext());}
broken {return new Symbol(sym.Broken, yychar, yyline, yytext());}
home {return new Symbol(sym.Home, yychar, yyline, yytext());}
printerport {return new Symbol(sym.Printerport, yychar, yyline, yytext());}
method {return new Symbol(sym.Method, yychar, yyline, yytext());}
check {return new Symbol(sym.Check, yychar, yyline, yytext());}
trap {return new Symbol(sym.Trap, yychar, yyline, yytext());}
class {return new Symbol(sym.Class, yychar, yyline, yytext());}
"main" {return new Symbol(sym.Main, yychar, yyline, yytext());}
"," {return new Symbol(sym.Coma, yychar, yyline, yytext());}
"." {return new Symbol(sym.Punto, yychar, yyline, yytext());}
{espacio} {/*Ignore*/}

{A}[^]*{C} {/*Ignore*/}

"\"" {return new Symbol(sym.COMILLAS_DOBLES, yychar, yyline, yytext());}
({cadena})       {return new Symbol(sym.Cadena,      yychar, yyline, yytext());}

"=" {return new Symbol(sym.Signo_de_igual, yychar, yyline, yytext());}

"+" {return new Symbol(sym.Signo_de_Suma, yychar, yyline, yytext());}
"-" {return new Symbol(sym.Signo_de_Resta, yychar, yyline, yytext());}
"*" {return new Symbol(sym.Signo_de_Multiplicación, yychar, yyline, yytext());}
"/" {return new Symbol(sym.Signo_de_División, yychar, yyline, yytext());}

"%" {return new Symbol(sym.Signo_de_Residuo, yychar, yyline, yytext());}
"!" {return new Symbol(sym.NOT, yychar, yyline, yytext());}
"&&" {return new Symbol(sym.AND, yychar, yyline, yytext());}
"||" {return new Symbol(sym.OR, yychar, yyline, yytext());}

"++" {return new Symbol(sym.Mas_Mas, yychar, yyline, yytext());}
"--" {return new Symbol(sym.Menos_Menos, yychar, yyline, yytext());}

"<" {return new Symbol(sym.Menor_que, yychar, yyline, yytext());}
">" {return new Symbol(sym.Mayor_que, yychar, yyline, yytext());}
"<=" {return new Symbol(sym.Menor_o_igual_que, yychar, yyline, yytext());}
">=" {return new Symbol(sym.Mayor_o_igual_que, yychar, yyline, yytext());}
"!=" {return new Symbol(sym.Diferente_que, yychar, yyline, yytext());}
"==" {return new Symbol(sym.Igual_que, yychar, yyline, yytext());}

"(" {return new Symbol(sym.Parentesis_Abre, yychar, yyline, yytext());}
")" {return new Symbol(sym.Parentesis_Cierra, yychar, yyline, yytext());}
"{" {return new Symbol(sym.Llave_Abre, yychar, yyline, yytext());}
"}" {return new Symbol(sym.Llave_Cierra, yychar, yyline, yytext());}
"[" {return new Symbol(sym.Corchete_Abre, yychar, yyline, yytext());}
"]" {return new Symbol(sym.Corchete_Cierra, yychar, yyline, yytext());}

";" {return new Symbol(sym.PuntoYComa, yychar, yyline, yytext());}

{L}({L}|{D})* {return new Symbol(sym.Identificador, yychar, yyline, yytext());}

("+"|"-")?{D}+|("+"|"-")?{D}+"." {D}+ {return new Symbol(sym.Numero, yychar, yyline,new Integer(yytext()));}

(("+-")|("-+"))({D}+|{D}+"."{D}+) {return new Symbol(sym.NUMERO_ERRONEO, yychar, yyline, yytext());}
 
(("+"|"-")?{L}+|("+"|"-")?{D}+"." {D}+)("."|{D}|{L})+ {return new Symbol(sym.MAL_NOMBRE_PARA_IDENTIFICADOR, yychar, yyline, yytext());}
                                                            
(("+"|"-")?{D}+|("+"|"-")?{D}+"." {D}+){L}({L}|{D})* {return new Symbol(sym.MAL_NOMBRE_PARA_IDENTIFICADOR, yychar, yyline, yytext());}
                                                      
{CAP}{L}({L}|{D})* {return new Symbol(sym.MAL_NOMBRE_PARA_IDENTIFICADOR, yychar, yyline, yytext());}
                   
\"\$\&\?\¿\%{L}({L}|{D})* {return new Symbol(sym.MAL_NOMBRE_PARA_IDENTIFICADOR, yychar, yyline, yytext());}

{L}{CAP}+{L}* {return new Symbol(sym.MAYUSCULAS_EN_CADENA, yychar, yyline, yytext());}

. {return new Symbol(sym.ERROR, yychar, yyline, yytext());}
