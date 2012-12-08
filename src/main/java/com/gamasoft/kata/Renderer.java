package com.gamasoft.kata;

import java.util.List;

public class Renderer {

    private List<NumberWord> numberWordList;

    public Renderer(List<NumberWord> numberWordList) {

        this.numberWordList = numberWordList;
    }

    String transformIntoWords(long number){

        for (NumberWord numberWord : numberWordList) {
            if (numberWord.apply(number)){
                return numberWord.render(number);
            }
        }
        return "Number too big!";


    }
}
