package com.gamasoft.kata;

import java.util.ArrayList;
import java.util.List;

public class NumberWords {


    public static String transform(long number) {

        List<NumberWord> list = new ArrayList<NumberWord>();
        list.add(new Zero());
        list.add(new Twenty());
        list.add(new Tens());
        list.add(new Hundreds());
        list.add(new QuantityBase(1000, "thousand"));
        list.add(new QuantityBase(1000000, "million"));
        list.add(new QuantityBase(1000000000, "billion"));

        Renderer renderer = new Renderer(list);

        return renderer.transformIntoWords(number);

    }


    private static String prefix(String prefix, String main) {
        if (main.isEmpty())
            return "";
        else
            return prefix + main;
    }

    private static class Zero implements NumberWord {

        @Override
        public String render(Renderer renderer, long number) {
            return "";
        }

        @Override
        public boolean apply(long number) {
            return number == 0;
        }
    }

    private static class Twenty implements NumberWord {
        final static String[] underTwentyWords = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};


        @Override
        public String render(Renderer renderer, long number) {
            return underTwentyWords[((int) (number - 1))];
        }

        @Override
        public boolean apply(long number) {
            return number < 20;
        }
    }

    private static class Tens implements NumberWord {
        final static String[] tensWords = new String[]{"ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};


        @Override
        public String render(Renderer renderer, long number) {
            int tens = (int) (number / 10);
            return tensWords[tens - 1] + prefix("-", renderer.transformIntoWords(number % 10));
        }

        @Override
        public boolean apply(long number) {
            return number < 100;
        }
    }

    private static class Hundreds implements NumberWord {
        final static String hundredWord = "hundred";


        @Override
        public String render(Renderer renderer, long number) {
            long hundreds = number / 100;
            return renderer.transformIntoWords(hundreds) + " " + hundredWord + prefix(" ", renderer.transformIntoWords(number % 100));
        }

        @Override
        public boolean apply(long number) {
            return number < 2000 && (number < 1000 || number > 1099);
        }
    }

    private static class QuantityBase implements NumberWord {
        final long base;

        final String baseName;

        public QuantityBase(long base, String baseName) {
            this.base = base;
            this.baseName = baseName;
        }

        @Override
        public String render(Renderer renderer, long number) {
            long quantity = number / base;
            return renderer.transformIntoWords(quantity) + " " + baseName + prefix(", ", renderer.transformIntoWords(number % base));
        }

        @Override
        public boolean apply(long number) {
            return number < base * 1000;
        }
    }


}
