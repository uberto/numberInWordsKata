package com.gamasoft.kata;

public class NumberWords {


    public static String transform(long number) {

        Renderer tooBig = new TooBigNumber();
        Renderer billions = new QuantityBase(tooBig, 1000000000, "billion");
        Renderer millions = new QuantityBase(billions, 1000000, "million");
        Renderer thousands = new QuantityBase(millions, 1000, "thousand");
        Renderer hundreds = new Hundreds(thousands);
        Renderer tens = new Tens(hundreds);
        Renderer twenty = new Twenty(tens);
        Renderer zero = new Zero(twenty);

        return zero.transformIntoWords(number);

    }


    private static String prefix(String prefix, String main) {
        if (main.isEmpty())
            return "";
        else
            return prefix + main;
    }

    private static class Zero extends Renderer {

        public Zero(Renderer nextRenderer) {
            super(nextRenderer);
        }

        @Override
        public String render(long number) {
            return "";
        }

        @Override
        public boolean apply(long number) {
            return number == 0;
        }
    }

    private static class Twenty extends Renderer {
        final static String[] underTwentyWords = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

        public Twenty(Renderer nextRenderer) {
            super(nextRenderer);
        }

        @Override
        public String render(long number) {
            return underTwentyWords[((int) (number - 1))];
        }

        @Override
        public boolean apply(long number) {
            return number < 20;
        }
    }

    private static class Tens extends Renderer {
        final static String[] tensWords = new String[]{"ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

        public Tens(Renderer nextRenderer) {
            super(nextRenderer);
        }

        @Override
        public String render(long number) {
            int tens = (int) (number / 10);
            return tensWords[tens - 1] + prefix("-", transform(number % 10));
        }

        @Override
        public boolean apply(long number) {
            return number < 100;
        }
    }

    private static class Hundreds extends Renderer {
        final static String hundredWord = "hundred";

        public Hundreds(Renderer nextRenderer) {
            super(nextRenderer);
        }

        @Override
        public String render(long number) {
            long hundreds = number / 100;
            return transform(hundreds) + " " + hundredWord + prefix(" ", transform(number % 100));
        }

        @Override
        public boolean apply(long number) {
            return number < 2000 && (number < 1000 || number > 1099);
        }
    }

    private static class QuantityBase extends Renderer {
        final long base;

        final String baseName;

        public QuantityBase(Renderer nextRenderer, long base, String baseName) {
            super(nextRenderer);
            this.base = base;
            this.baseName = baseName;
        }

        @Override
        public String render(long number) {
            long quantity = number / base;
            return transform(quantity) + " " + baseName + prefix(", ", transform(number % base));
        }

        @Override
        public boolean apply(long number) {
            return number < base * 1000;
        }
    }

    private static class TooBigNumber extends Renderer {

        public TooBigNumber() {
            super(null);
        }

        @Override
        public String render(long number) {
            return "number too big!";
        }

        @Override
        public boolean apply(long number) {
            return true;
        }
    }
}
