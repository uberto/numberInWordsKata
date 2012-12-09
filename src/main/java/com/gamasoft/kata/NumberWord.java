package com.gamasoft.kata;

public interface NumberWord {

    abstract boolean apply(long number);

    abstract String render(Renderer renderer, long number);

}
