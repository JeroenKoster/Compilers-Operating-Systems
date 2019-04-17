// This test will show a valid example
// Logical or condtional expressions

IF 2 > 1 {
    PRINT "BASIC: IM TRUE";
} ELSE {
     PRINT "I FAILED";
}

IF true {
    PRINT "BOOL: IM TRUE";
} ELSE {
     PRINT "I FAILED";
}

IF 1 > 1 OR 1 > 2 OR 2 > 1 {
    PRINT "OR: IM TRUE";
} ELSE {
     PRINT "I FAILED";
}

IF 2 < 3 AND 3 < 4 {
    PRINT "AND: IM TRUE" ;
} ELSE {
     PRINT "I FAILED";
}

IF NOT 2 > 3 {
    PRINT "NOT: IM TRUE";
} ELSE {
     PRINT "I FAILED";
}

IF NOT false {
    PRINT "NOT: IM TRUE";
} ELSE {
     PRINT "I FAILED";
}

IF 1 > 0 OR 1 < 0 AND 2 > 1 AND true AND NOT 2 > 3 AND NOT false {
    PRINT "IM TRUE MAJOR TEST" ;
} ELSE {
     PRINT "I FAILED";
}
