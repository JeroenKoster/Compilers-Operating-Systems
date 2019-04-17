grammar OurLanguage;

start: (statement*) EOF;

statement
    : printStatement
    | ifStatement
    | whileLoop
    | forLoop
    | declaration
    | assignment
    | block
    ;

ifStatement: 'IF' expression block ('ELSE IF' expression block)* ('ELSE' block)?;

whileLoop: 'WHILE' expression block;

forLoop: 'FOR' expression ';' assignment block;

declaration
    : variableName IDENTIFIER ';'                                   # DeclOnly
    | variableName IDENTIFIER '=' expression ';'                    # DeclAndAssignment
    ;

variableName: ('INT' | 'STRING' | 'BOOL');

assignment: IDENTIFIER '=' expression ';';

printStatement: 'PRINT' expression ';';

block: '{' (statement)* '}';

expression
    : '(' expression ')'                                            # ExParentheses
    | '-' expression                                                # ExNegate
    | left=expression op=('*'|'/') right=expression                 # ExMulOp
    | left=expression op=('+'|'-') right=expression                 # ExAddOp
    | left=expression comp=('<'|'>'|'<='|'>='|'=='|'!=') right=expression #LogicalCond
    | left=expression 'OR' right=expression                         # LogicalOrCond
    | left=expression 'AND' right=expression                        # LogicalAndCond
    | 'NOT' expression                                              # LogicalNotCond
    | IDENTIFIER                                                    # ExIdentifier
    | INT                                                           # ExIntLiteral
    | STRING                                                        # ExStringLiteral
    | BOOLEAN                                                       # ExBoolLiteral
    ;

INT: '0' | [1-9][0-9]*;
STRING: '"' ~('\n'|'\r')* '"';
BOOLEAN: 'true' | 'false';
IDENTIFIER: '.'[A-Za-z][A-Za-z_]*;
WS: [\r\n\t ]+ -> skip;
COMMENT: '//'.*? [\n\r]+ -> skip;