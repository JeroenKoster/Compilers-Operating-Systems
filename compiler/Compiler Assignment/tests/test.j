.class public test
.super java/lang/Object

.method public static main([Ljava/lang/String;)V
.limit stack 99
.limit locals 99

    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc 1
    ldc 2
    iadd
    invokevirtual java/io/PrintStream/println(I)V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc 4
    ldc 5
    imul
    ldc 3
    isub
    invokevirtual java/io/PrintStream/println(I)V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc "Hi"
    invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
    return
.end method
