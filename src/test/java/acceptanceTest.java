import org.junit.Test;

import static com.gamasoft.kata.NumberWords.transform;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class acceptanceTest {

    @Test
    public void transformBigNumbersInWords() throws Exception {
        assertThat(transform(123456789), is("one hundred twenty-three million, four hundred fifty-six thousand, seven hundred eighty-nine"));
        assertThat(transform(987654321), is("nine hundred eighty-seven million, six hundred fifty-four thousand, three hundred twenty-one"));
        assertThat(transform(432198765), is("four hundred thirty-two million, one hundred ninety-eight thousand, seven hundred sixty-five"));
        assertThat(transform(10001), is("ten thousand, one"));
        assertThat(transform(1000000), is("one million"));
    }

    @Test
    public void transformNumbersUnderTwentyInWords() throws Exception {
        assertThat(transform(1), is("one"));
        assertThat(transform(2), is("two"));
        assertThat(transform(19), is("nineteen"));
        assertThat(transform(11), is("eleven"));
    }

    @Test
    public void transformNumbersUnderOneHundredInWords() throws Exception {
        assertThat(transform(21), is("twenty-one"));
        assertThat(transform(33), is("thirty-three"));
        assertThat(transform(69), is("sixty-nine"));
    }

    @Test
    public void transformNumbersUnderTwoThousand() throws Exception {
        assertThat(transform(321), is("three hundred twenty-one"));
        assertThat(transform(133), is("one hundred thirty-three"));
        assertThat(transform(777), is("seven hundred seventy-seven"));
        assertThat(transform(900), is("nine hundred"));
        assertThat(transform(1980), is("nineteen hundred eighty"));
    }

    @Test
    public void transformNumbersUnderOneMillion() throws Exception {
        assertThat(transform(1000), is("one thousand"));
        assertThat(transform(1066), is("one thousand, sixty-six"));
        assertThat(transform(12345), is("twelve thousand, three hundred forty-five"));

    }


}

