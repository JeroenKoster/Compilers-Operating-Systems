.class public test
.super java/lang/Object

.method public static main([Ljava/lang/String;)V
.limit stack 99
.limit locals 99

    ldc 1
    istore 0
    while_0:
    iload 0
    ldc 4
    if_icmplt true_condition_0
    	 ldc 0
    	 goto end_0
    true_condition_0:
    	 ldc 1
    end_0:
    ldc 1
    if_icmpeq true_while_0
    	 goto end_while_0
    true_while_0:
    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc "HELLO"
    invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
    iload 0
    ldc 1
    iadd
    istore 0
    goto while_0
    end_while_0:
    return
.end method
