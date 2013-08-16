package com.pwc.brains.compiler;

public class Token{

    public Type type;
    public String value;

    public Token(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public Token(String value) {
        this.value = value;
    }
}
