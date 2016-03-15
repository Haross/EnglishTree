package englishtree;
import static analizadorlexico.Token.*;
%%
%class Lexer
%type Token
L=[a-zA-Z_]
D=[0-9]
white=[ \t\r\n]
C= [op]
CO = [%]

identificador = {L} ({L}|{D})* 
T = "int"|"float"|"char"|"double"  
expresion = {identificador} {simbolo} {identificador}
simbolo = "!="|"=="|">"|"<"|"<>" 
impresion = "printf(\""{identificador}"\")"
%{
    public String save;
%}
%%
 
<YYINITIAL> {T} {save = yytext(); return tipo;}
<YYINITIAL>{
{white} {/*Ignore*/}
"//".* {/*Ignore*/}


"=" {return igual;}
"+" {return suma;}
["'!''¡'"]?{D}+[.] {D}+ {save = yytext(); return Numero;}
["'!''¡'"]?{D}+ {save = yytext(); return Numero;}
"*" {return multiplicacion;}
"-" {return resta;}
"/" {return division;}
"==" {return EQUALS;}
"!=" {return DIF;}


{CO}({L}|{D})*{CO} {save = yytext();return CO;}
{C}({L}|{D})*{C} {save = yytext();return CAD;}
{impresion} {return print;}
{expresion} {return ExpLogica;}
"while" {return WHILE;}
"return" {return RETURN;}
"," {return coma;}
";" {return puntoYComa;}
"(" {return PI;}
")" {return PD;}
"{" {return LI;}
"}" {return LD;}
"main" {save = yytext();return MAIN;}
"#include <stdio.h>" {save = yytext(); return INCLUDE;}
{identificador} {save = yytext();return ID;}

. {return ERROR;}
}