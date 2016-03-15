package englishtree;
import static englishtree.Token.*;
%%
%class Lexer
%type Token

white=[ \t\r\n]

L=[a-zA-Z_]
D=[0-9]

S = "I"|"You"|"We"|"They"|"you"|"we"|"they"
T = "She"|"He"|"It"|"she"|"he"|"it"
V = "find" | "thick"|"give"|"make"|"know"|"take"|"see"|"look"
P = {L} ({L}|{D})* 

%{
    public String save;
%}
%%
 
<YYINITIAL> {T} {save = yytext(); return S3;}
<YYINITIAL> {S} {save = yytext(); return S;}
<YYINITIAL> {V} {save = yytext(); return V;}

<YYINITIAL>{
{white} {/*Ignore*/}
"//".* {/*Ignore*/}
"not" {save = yytext(); return not;} 
{P} {save = yytext(); return P;}
"do" {save = yytext(); return do1;} 
"does" {save = yytext(); return does1;} 
"Do" {save = yytext(); return do1;} 
"Does" {save = yytext(); return does1;} 
"?" {save = yytext(); return question;} 

. {return ERROR;}
}