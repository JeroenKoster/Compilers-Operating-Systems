// This test will show a valid example
// If statements with and without else conditions and scoping

IF 1 < 2 { PRINT "one is smaller than two" ; }

IF 1 > 2 { PRINT "one is larger than two" ; }
ELSE { PRINT "one is not larger than two" ; }

IF 1 > 2 { PRINT "one is larger than two" ; }
ELSE IF 1 < 2 { PRINT "one is smaller than two" ; }
ELSE { PRINT "undefined comparison one and two" ; }


INT .a ;
.a = 13 ;

IF 1 < .a {
    INT .b ;
    .b = 10 ;
    PRINT .a + .b;
} ELSE {
    PRINT .a ;
}
