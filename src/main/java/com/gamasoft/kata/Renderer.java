package com.gamasoft.kata;

import java.util.List;

public class Renderer {

    private List<NumberWord> numberWordList;

    public Renderer(List<NumberWord> numberWordList) {

        this.numberWordList = numberWordList;
    }

    public String transformIntoWords(long number){

        for (NumberWord numberWord : numberWordList) {
            if (numberWord.apply(number)){
                return numberWord.render(this, number);
            }
        }
        return "Number too big!";


    }
}
