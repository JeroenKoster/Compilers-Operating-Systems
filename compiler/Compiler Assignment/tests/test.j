.class public test
.super java/lang/Object

.method public static main([Ljava/lang/String;)V
.limit stack 99
.limit locals 99

    ldc 10
    istore 0
    getstatic java/lang/System/out Ljava/io/PrintStream;
    iload 0
    invokevirtual java/io/PrintStream/println(I)V
    return
.end method
