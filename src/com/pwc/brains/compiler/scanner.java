package com.pwc.brains.compiler;


public class Scanner {
    private int currentState = 1;
    private StringBuilder temp;
    private int current = 0;
    private String content;
    private Token[] Keywords;
    private String keyWorks;

    public Scanner(String content) {
        this.content = content;
    }
    //Σ 表示有限的输入字符
    //S 表示有限状态的集合
    //s0 表示初始状态， s0 ∈ S
    //σ 是状态转移函数 σ：S × Σ → S
    //F 是最终状态集合，是S的子集

    //id 的正则表示[A-z][A-z0-9]

    //状态转移二维数组表示,  a[0]表示停机状态，a[1]是起始状态，a[i][j]表示在第i状态下输入j的转移目标
    //[0,0,0]
    //[2,0,0]
    //[2,2,0]

    public int[][] mapOfStates = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 2, 10, 9, 7, 5, 3, 0, 12, 0, 14},            //1
            {0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0},       //2
            {4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4},               //3
            {4, 4, 4, 4, 4, 4, 16, 4, 4, 4, 4},              //4
            {0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 6},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 8},              //7
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0},            //10
            {11, 11, 11, 11, 11, 11, 11, 0, 11, 11, 11},
            {12, 12, 12, 12, 12, 12, 12, 12, 16, 13, 12},
            {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0} ,
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    public String next() {
        StringBuilder word = new StringBuilder();
        this.currentState = 1;

        int prevState = currentState;
        if (this.current >= content.length()) {
            return null;
        }
        while (currentState != 0 && this.current < content.length()) {
            char currentChar = this.currentChar();
            int nextState = mapOfStates[currentState][map(currentChar)];
            if (nextState != 0) {
                currentState = nextState;
                word.append(this.read());
            } else {
                if (currentState == 1) {
                    this.read();
                    return next();
                }
                currentState = 0;
                return word.toString();
            }
        }
        return word.toString();
    }

    public Token nextToken(){
        StringBuilder tokenValue = new StringBuilder();
        this.currentState = 1;

        int prevState = currentState;
        if (this.current >= content.length()) {
            return null;
        }
        while (currentState != 0 && this.current < content.length()) {
            char currentChar = this.currentChar();
            int nextState = mapOfStates[currentState][map(currentChar)];
            if (nextState != 0) {
                currentState = nextState;
                tokenValue.append(this.read());
            } else {
                if (currentState == 1) {
                    this.read();
                    return nextToken();
                }
                currentState = 0;
                return new Token(tokenValue.toString());
            }
        }
        return new Token(tokenValue.toString());
    }

    private Token getKeyWord(String nextString) {
        return null;
    }

    private int map(char current) {
        if (current > 'A' && current < 'z') {
            return 1;
        }
        if (current > '0' && current < '9') {
            return 2;
        }
        char[] symbols = new char[]{'%', '(', ')', '[', ']', '{', '}', ';', '?', '*', '/'};

        for (char c : symbols) {
            if (c == current)
                return 3;
        }
        switch (current) {
            case '+':
                return 4;
            case '-':
                return 5;
            case '\'':
                return 6;
            case ' ':
                return 7;
            case '\"':
                return 8;
            case '\\':
                return 9;
            case '=':
                return 10;
            default:
                break;
        }
        return 0;
    }

    private char read() {
        return content.charAt(current++);
    }

    private char currentChar() {
        return content.charAt(current);
    }

}


