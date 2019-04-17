// This test will show a invalid example
// If statements with and without else conditions and scoping

INT .a ;
.a = 13 ;

IF 1 < .a {
    INT .b ;
    .b = 10 ;
    PRINT .a + .b;
} ELSE {
    PRINT .a ;
}
PRINT .b ;