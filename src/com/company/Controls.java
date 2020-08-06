package com.company;

// Controllers, they can be easily changed if necessary
public enum Controls {
    LEFT('a'), DOWN('s'), RIGHT('d'), UP('w'), QUIT('q'), YES('y');

    private final char c;

    private Controls(final char value) {
        this.c = value;
    }

    public char getValue() { return c; }
}
