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


