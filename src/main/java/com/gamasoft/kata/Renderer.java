package com.gamasoft.kata;

public abstract class Renderer {

    abstract boolean apply(int number);

    abstract String render(int number);

    private Renderer nextRenderer;

    public Renderer(Renderer nextRenderer) {
        this.nextRenderer = nextRenderer;
    }

    String transformIntoWords(int number){

        if (apply(number)){
            return render(number);
        } else {
            return nextRenderer.transformIntoWords(number);
        }

    }
}
