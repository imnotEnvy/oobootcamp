package org.oobootcamp.warmup;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class FizzBuzzTest {

    @Test
    void should_return_1_given_1() {
        assertThat(Fizzbuzz.run(1)).isEqualTo("1");
    }

    @Test
    void should_return_fizz_given_3() {
        assertThat(Fizzbuzz.run(3)).isEqualTo("Fizz");
    }

    @Test
    void should_return_buzz_given_5() {
        assertThat(Fizzbuzz.run(5)).isEqualTo("Buzz");
    }

}
