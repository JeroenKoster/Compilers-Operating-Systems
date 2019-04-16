IF 2 > 1 {
    PRINT "BASIC: IM TRUE";
}

IF true {
    PRINT "BOOL: IM TRUE";
}

IF 1 > 1 OR 1 > 2 OR 2 > 1 {
    PRINT "OR: IM TRUE";
}

IF 2 < 3 AND 3 < 4 {
    PRINT "AND: IM TRUE" ;
}

IF NOT 2 > 3 {
    PRINT "NOT: IM TRUE";
}

IF NOT false {
    PRINT "NOT: IM TRUE";
}

IF 1 > 0 OR 1 < 0 AND 2 > 1 AND true AND NOT 2 > 3 AND NOT false {
    PRINT "IM TRUE MAJOR TEST" ;
}

