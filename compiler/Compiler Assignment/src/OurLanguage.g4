grammar OurLanguage;

start: (statement*) EOF;

statement
    : printStatement
    | ifStatement
    | declaration
    | assignment
    | block
    ;

ifStatement: 'IF' expression ('<' | '>') expression block;

declaration: variableName IDENTIFIER ';';

variableName: ('INT' | 'STRING');

assignment: IDENTIFIER '=' expression ';';

printStatement: 'PRINT' expression ';';

block: '{' expression* '}';

expression
    : '(' expression ')'                                   # ExParentheses
    | '-' expression                                       # ExNegate
    | left=expression '*' right=expression                 # ExMulOp
    | left=expression op=('+' | '-') right=expression      # ExAddOp
    | IDENTIFIER                                           # ExIdentifier
    | INT                                                  # ExIntLiteral
    | STRING                                               # ExStringLiteral
//    | BOOLEAN                                              # ExBooleanLiteral
    ;

INT: '0' | [1-9][0-9]*;
STRING: '"' ~('\n'|'\r')* '"';
IDENTIFIER: [A-Za-z][A-Za-z_]*;
WS: [\r\n\t ]+ -> skip;
COMMENT: '//'.*? [\n\r]+ -> skip;
