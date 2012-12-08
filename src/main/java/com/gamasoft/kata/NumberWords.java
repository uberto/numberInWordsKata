package com.gamasoft.kata;

public class NumberWords {

    final static String[] underTwentyWords = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    final static String[] tensWords = new String[]{"ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    final static String hundredWord = "hundred";
    final static String thousandWord = "thousand";
    final static String millionWord = "million";

    public static String transform(int number) {

        TooBigNumber tooBig = new TooBigNumber();
        Millions millions = new Millions(tooBig);


        if (number == 0) {
            return "";
        } else if (number < 20) {
            return underTwentyWords[number - 1];
        } else if (number < 100) {
            int tens = number / 10;
            return tensWords[tens - 1] + prefix("-", transform(number % 10));
        } else if (number < 2000 && (number < 1000 || number > 1099)) {
            int hundreds = number / 100;
            return underTwentyWords[hundreds - 1] + " " + hundredWord + prefix(" ", transform(number % 100));
        } else if (number < 1000000) {
            int thousands = number / 1000;
            return transform(thousands) + " " + thousandWord + prefix(", ", transform(number % 1000));
        } else {
            return millions.transformIntoWords(number);
        }
    }


    private static String prefix(String prefix, String main) {
        if (main.isEmpty())
            return "";
        else
            return prefix + main;
    }

    private static class Millions extends Renderer {


        public Millions(Renderer nextRenderer) {
            super(nextRenderer);
        }

        @Override
        public String render(int number) {
            int millions = number / 1000000;
            return transform(millions) + " " + millionWord + prefix(", ", transform(number % 1000000));
        }

        @Override
        public boolean apply(int number) {
            return number < 1000000000;
        }
    }

    private static class TooBigNumber extends Renderer {

        public TooBigNumber() {
            super(null);
        }

        @Override
        public String render(int number) {
            return "number too big!";
        }

        @Override
        public boolean apply(int number) {
            return true;
        }
    }
}
