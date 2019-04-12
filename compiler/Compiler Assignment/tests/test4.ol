PRINT "THIS IS THE OUTPUT OF THE BASIC IF STATEMENT: " ;
IF 1 < 2 { PRINT "IF 1 smaller than 2"; }

PRINT "THIS IS THE OUTPUT OF THE BASIC IF-ELSE STATEMENT: " ;
IF 1 > 2 { PRINT "IF 1 smaller than 2"; }
ELSE { PRINT "ELSE 1 not smaller than 2"; }

PRINT "THIS IS THE OUTPUT OF THE ELSE-IF STATEMENT: " ;
IF 1 > 2 { PRINT "IF 1 greater than 2" ; }
ELSE IF 1 < 2 { PRINT "ELSE IF 1 smaller than 2" ; }
ELSE { PRINT "ELSE 2 is not smaller than 1" ; }

PRINT "THIS IS THE OUTPUT OF A IF-ELSE STATEMENT WITH VARIABLES: " ;
INT .a ;
.a = 13 ;

IF 1 < .a {
    INT .b ;
    .b = 10 ;
    PRINT .a + .b;
} ELSE {
    PRINT .a ;
}
