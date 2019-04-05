grammar OurLanguage;

start: (statement*) EOF;

statement
    : printStatement
    | ifStatement
    | loop
    | declaration
    | assignment
    | block
    ;

ifStatement: 'IF' condition block;
//ifStatement: 'IF' expression block ('ELSE' (ifStatement | block))?;

loop: 'WHILE' expression block;

declaration: variableName IDENTIFIER ';';

variableName: ('INT' | 'STRING');

assignment: IDENTIFIER '=' expression ';';

printStatement: 'PRINT' expression ';';

block: '{' statement '}';
//block: '{' ( expression* | statement* ) '}';

condition: left=expression comp=('<'|'>'|'<='|'>='|'=='|'!=') right=expression;

expression
    : '(' expression ')'                                   # ExParentheses
    | expression comp=( '<' | '>' | '==' | '!=') expression
        (('OR' | 'AND' ) expression)?                      # ExLogical
    | '-' expression                                       # ExNegate
    | left=expression '*' right=expression                 # ExMulOp
    | left=expression op=('+'|'-') right=expression        # ExAddOp
    | IDENTIFIER                                           # ExIdentifier
    | INT                                                  # ExIntLiteral
    | STRING                                               # ExStringLiteral
    | BOOLEAN                                              # ExBoolLiteral
    ;

INT: '0' | [1-9][0-9]*;
STRING: '"' ~('\n'|'\r')* '"';
BOOLEAN: 'true' | 'false';
IDENTIFIER: [A-Za-z][A-Za-z_]*;
WS: [\r\n\t ]+ -> skip;
COMMENT: '//'.*? [\n\r]+ -> skip;O
