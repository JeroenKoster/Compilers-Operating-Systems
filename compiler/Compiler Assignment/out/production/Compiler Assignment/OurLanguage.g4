grammar OurLanguage;

start: (statement*) EOF;

statement
    : printStatement ;
//    : declaration
//    | assignment
//    | block
//    ;
//
//declaration: variableName IDENTIFIER ';';
//
//variableName: ('INT' | 'STRING');
//
//assignment: IDENTIFIER '=' expression ';';
//
printStatement: 'PRINT' expression ';';
//
//block: '{' statement* '}';

expression
    : '(' expression ')'                                   # ExParentheses
    | '-' expression                                       # ExNegate
    | left=expression '*' right=expression                 # ExMulOp
    | left=expression op=('+' | '-') right=expression      # ExAddOp
    | IDENTIFIER                                           # ExIdentifier
    | INT                                                  # ExIntLiteral
    | STRING                                               # ExStringLiteral
    ;

INT: '0' | [1-9][0-9]*;
STRING: '"' ~('\n'|'\r')* '"';
IDENTIFIER: [A-Za-z][A-Za-z_]*;
WS: [\r\n\t ]+ -> skip;
COMMENT: '//'.*? [\n\r]+ -> skip;
