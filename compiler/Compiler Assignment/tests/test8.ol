INT .a;
.a = 10;

INT .zero = 0;
BOOL .bool = false;
STRING .str = "Hello World";

PRINT 0;
PRINT "Hello World";
PRINT true;

STRING .example = "This is an example";
PRINT .example;


PRINT 1 + 2 ;
PRINT 4 - 3 ;
PRINT 4 * 5 ;
PRINT 6 / 2 ;
PRINT -4 -8 ;
PRINT ( 6 - 2 ) ;
PRINT (-2 + 4 * ( 6 - 1 )) / 9 ;


PRINT 2 < 4;
PRINT 4 > 2;
PRINT 3 <= 5;
PRINT 5 >= 3;
PRINT 4 == 4;
PRINT 4 != 7;
PRINT true != false;
PRINT true == true;
PRINT true AND true;
PRINT true OR false;
PRINT NOT true;

IF 1 < 2 { PRINT "one is smaller than two" ; }

IF 1 > 2 { PRINT "one is larger than two" ; }
ELSE { PRINT "one is not larger than two" ; }

IF 1 > 2 { PRINT "one is larger than two" ; }
ELSE IF 1 < 2 { PRINT "one is smaller than two" ; }
ELSE { PRINT "undefined comparison one and two" ; }

INT .while_cond;
.while_cond = 1;

WHILE .while_cond < 4 {
    PRINT "HELLO";
    .while_cond = .while_cond + 1 ;
}
