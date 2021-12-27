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
        | Integer (LogicOperator|EqualOrNot) intExpression #valueVerifyLogicOrEqualConfig
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

