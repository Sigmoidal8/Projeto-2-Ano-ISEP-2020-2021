# US1004_Criação da Linguagem suporte do sistema
=======================================


# 1. Requisitos

Como Gestor de Projeto, eu pretendo que seja desenvolvida uma linguagem/gramática de suporte geral ao sistema para expressar, entre outras coisas, validações de formulários e atividades automáticas.

# 2. Análise

**Pré-Análise** No sistema já devem estar presentes todos os atributos que poderão ser utilizados nesta Linguagem

**Pós-Análise** Deve ser possível a validação dos scripts

A linguagem de suporte ao sistema pode ser constituída por uma ou mais linguagens ou gramáticas. Vai servir para validar a introdução de dados no sistema.
Deve suportar a validação de um formulário e dos seus atributos(obrigatoriedade, intervalos de valores etc). Deve suportar também a execução automática de tarefas. Para tal deve suportar a obtenção de informação a partir de um determinado ficheiro local XML, deve suportar a realização de cálculos matemáticos e também deve suportar o envio de emails.

# 3. Design

```c
grammar FormValidation;
prog:
    |prog ifVerification
    |prog config
    ;

ifVerification: 'if' conditions 'then' content ';' ;

content: content contentconditions
        |content config
        |content ifVerification
        |contentconditions
        |config
        |ifVerification
        ;

config: Integer 'size is' Integer ';' #valueSizeIs
        | Integer 'is between' gap ';'  #valueIsBetween
        | Integer 'is' Mandatory ';' #valueIsMandatory
        ;

contentconditions: conditions ';'
                 ;

conditions: condition   #conditionsNoOperator
            | conditions ConditionOperator condition #conditionsOperator
            ;

condition:  Integer (LogicOperator|EqualOrNot) intExpression #valueVerifyLogicOrEqual
            |Integer ':' StringState #valueState
            |Integer EqualOrNot stringCondition #valueIsEqualString
            |Integer EqualOrNot BoolValue #valueIsBoolean
            |BoolValue EqualOrNot Integer #booleanIsValue
            ;

stringCondition: stringQuoteMarks | stringValue;

stringQuoteMarks: '"' stringValue '"';

stringValue: String
            | String stringValue
        ;


intExpression: intValue
                | intExpression MathOperator intValue
                ;

intValue: String ('.'?) Integer
        | Integer
        ;                

gap: Integer 'and' Integer;

declaration: EqualOrNot | LogicOperator
        ;

StringState: 'filled' | 'empty' ;
LogicOperator: '<'|'>'|'<='|'>=';
BoolValue: 'true'|'false';
EqualOrNot: '=='|'!=';
ConditionOperator: '&&' | '||';
OptionType: 'Integer'|'Real'|'String';
MathOperator: '+'|'-'|'*'|'/';
Mandatory: 'obligatory' | 'not obligatory';
String: [A-Za-z]+;
Integer: [0-9]+;
WS : [ \t\r\n]+ -> skip ;

```

```c
grammar AutomaticTaskLanguage;
prog:prog email
    | prog file
    | prog calculation
    | email
    | file
    | calculation
    ;


file: 'search' 'file'  filexml ':' filetext ':' filetext';' #searchfile
;

calculation: 'calculation' ':' arithmetic ';'       #calculationArithmetic
            ;


arithmetic: textinfo                                            #textinfoArithmetic
          | Number                                              #number
          | '(' inner=arithmetic ')'                            #parentheses
          | left=arithmetic operator=POW right=arithmetic       #power
          | left=arithmetic operator=(MUL|DIV) right=arithmetic #multiplicationOrDivision
          | left=arithmetic operator=(ADD|SUB) right=arithmetic #additionOrSubtraction
          ;

email: 'send' 'email' '|' text '|' emailtext ';' #sendEmail
    ;

filetext: filetext text  #filetextFileTextText
        | filetext DIV   #filetextDiv
        | filetext '/' textinfo '=' textinfo  #filetextFormValidation
        | text  #filetextText
        ;

emailtext: emailtext textinfo       #emailTextWithTextInfo
            | textinfo              #emailTextTextInfo
            ;

textinfo: formResponse              #textInfoFormResponse
        | fileSearch                #textInfoFileSearch
        | calculationSearch         #textInfoCalculationSearch
        | text                      #textInfoText
        ;

text: text Word     #textTextWord
    | text Number   #textTextNumber
    | text Decision #textTextDecision
    | Decision      #textDecision
    | Word          #textWord
    | Number        #textNumber
    ;

filexml: filexml '/' fileName XML  #fileXmlWithSlash
    | fileName                     #fileXmlFileName
    | fileName XML                 #fileXmlWithoutSlash
    ;

fileName: fileName Word         #fileNameWithWord
    | fileName Number           #fileNameWithNumber
    | Word                      #fileNameWord
    | Number                    #fileNameNumber
;

formResponse: request
            | approval
            ;

fileSearch: '{' 'File' Number '}';

calculationSearch: '{' 'Calculation' Number '}';

request: '{' 'Form' 'Request' Number '}';

approval: '{' 'Form' 'Approval' Number '}';

Decision: '{Decision}';
Email: [a-zA-Z0-9]+'@'([a-zA-Z0-9]+'.')+('com'|'pt') ;
Word: [a-zA-Z]+;
Number: '-'?[0-9]+ ;
POW: '^';
MUL: '*';
DIV: '/';
ADD: '+';
SUB: '-';
XML: '.xml';
WS : [ \t\r\n]+ -> skip ;

```

# 4. Integração/Demonstração


Esta linguageem pode reconhecer expressões de validação de formulários como por exemplo:
 - *if 4 == "Unica" then
	7 is obligatory;*
;


Esta linguagem pode reconhecer expressões para pesquisa em ficheiros, como por exemplo:
  - *search file fileName1.xml : products/product/id={Form Request 1} : price;

Pode reconhecer expressões para o envio de emails, como por exemplo:
  - *send email | cabecalho1 | conteudo1 {Calculation 1} conteudo2;*

Pode também reconhecer expressões para cálculos, como por exemplo:
  - calculation : { File 1 } * {Form Request 2};
