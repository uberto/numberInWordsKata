package com.gamasoft.kata;

public abstract class Renderer {

    abstract boolean apply(long number);

    abstract String render(long number);

    private Renderer nextRenderer;

    public Renderer(Renderer nextRenderer) {
        this.nextRenderer = nextRenderer;
    }

    String transformIntoWords(long number){

        if (apply(number)){
            return render(number);
        } else {
            return nextRenderer.transformIntoWords(number);
        }

    }
}
