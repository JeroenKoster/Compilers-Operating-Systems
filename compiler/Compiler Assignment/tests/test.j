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
    ldc "Hi there"
    astore 1
    getstatic java/lang/System/out Ljava/io/PrintStream;
    aload 1
    invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
    ldc 1
    istore 2
    getstatic java/lang/System/out Ljava/io/PrintStream;
    iload 2
    invokevirtual java/io/PrintStream/println(Z)V
    ldc 0
    istore 3
    getstatic java/lang/System/out Ljava/io/PrintStream;
    iload 3
    invokevirtual java/io/PrintStream/println(I)V
    ldc "Hello"
    astore 4
    getstatic java/lang/System/out Ljava/io/PrintStream;
    aload 4
    invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
    ldc 0
    istore 5
    getstatic java/lang/System/out Ljava/io/PrintStream;
    iload 5
    invokevirtual java/io/PrintStream/println(Z)V
    return
.end method
