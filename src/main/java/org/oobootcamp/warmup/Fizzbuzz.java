package org.oobootcamp.warmup;

public class Fizzbuzz {
    public static String run(int x) {
        if (x % 3 == 0) {
            return "Fizz";
        } else if (x % 5 == 0) {
            return "Buzz";
        } else {
            return String.valueOf(x);
        }
    }
}
