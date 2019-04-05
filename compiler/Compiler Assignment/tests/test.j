.class public test
.super java/lang/Object

.method public static main([Ljava/lang/String;)V
.limit stack 99
.limit locals 99

    ldc 2
    ldc 1
    if_icmpgt true_0
    	 ldc 0
    	 goto end_0
    true_0:
    	 ldc 1
    end_0:
    ldc 1
    if_icmpeq true_1
    	 ldc 0
    	 goto end_1
    true_1:
    	 ldc 1
    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc 1
    ldc 2
    iadd
    invokevirtual java/io/PrintStream/println(I)V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc 4
    invokevirtual java/io/PrintStream/println(I)V
    end_1:
    return
.end method
