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
    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc 6
    ldc 2
    isub
    invokevirtual java/io/PrintStream/println(I)V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc 2
    ineg
    ldc 4
    ldc 5
    imul
    iadd
    invokevirtual java/io/PrintStream/println(I)V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc 2
    ineg
    ldc 4
    ldc 6
    ldc 1
    isub
    imul
    iadd
    ldc 9
    idiv
    invokevirtual java/io/PrintStream/println(I)V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc "Hello"
    invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc 6
    ldc 2
    idiv
    invokevirtual java/io/PrintStream/println(I)V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc 4
    ineg
    ldc 8
    isub
    invokevirtual java/io/PrintStream/println(I)V
    return
.end method
