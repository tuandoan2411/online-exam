package com.tuandoan.entity;

public enum Answer {

    A('a'),
    B('b'),
    C('c'),
    D('d');

    private final char code;

    Answer(char code) {
        this.code = code;
    }

    public static Answer fromCode(char code) {
        if (code == 'A' || code == 'a') {
            return A;
        }
        if (code == 'B' || code == 'b') {
            return B;
        }
        if (code == 'C' || code == 'c') {
            return C;
        }
        if (code == 'D' || code == 'd') {
            return D;
        }
        throw new UnsupportedOperationException(
                "The code " + code + " is not supported!"
        );
    }

    public char getCode() {
        return code;
    }
}
