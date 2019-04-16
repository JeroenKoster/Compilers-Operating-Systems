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

ifStatement: 'IF' condition block ('ELSE IF' condition block)* ('ELSE' block)?;

whileLoop: 'WHILE' condition block;

forLoop: 'FOR' condition ';' assignment block;

declaration
    : variableName IDENTIFIER ';'                                   # DeclOnly
    | variableName IDENTIFIER '=' expression ';'                    # DeclAndAssignment
    ;

variableName: ('INT' | 'STRING' | 'BOOL');

assignment: IDENTIFIER '=' expression ';';

printStatement: 'PRINT' expression ';';

block: '{' (statement)* '}';

condition
    : left=expression comp=('<'|'>'|'<='|'>='|'=='|'!=') right=expression #ConditionalCond
    | left=condition 'OR' right=condition                           # LogicalOrCond
    | left=condition 'AND' right=condition                          # LogicalAndCond
    | 'NOT' condition                                               # LogicalNotCond
    | expression    /** BOOLEAN */                                  # LogicalExBool
    ;

expression
    : '(' expression ')'                                            # ExParentheses
    | '-' expression                                                # ExNegate
    | left=expression op=('*'|'/') right=expression                 # ExMulOp
    | left=expression op=('+'|'-') right=expression                 # ExAddOp
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