package ru.homecredit.test.stringparser.service;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.homecredit.test.stringparser.dto.ParseResponse;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(JUnit4.class)
public class ParseServiceTest {

    private final ParseService service = new ParseService();
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void parseString_firstTest() {
        ParseResponse parseResponse = service.parseString(" ab   cd  e  ");
        assertThat(parseResponse.getOutputString(), CoreMatchers.is("a  b  c  d  e"));
    }

    @Test
    public void parseString_secondTest() {
        ParseResponse parseResponse = service.parseString(" ab ");
        assertThat(parseResponse.getOutputString(), CoreMatchers.is("a  b"));
    }

    @Test
    public void parseString_sixthTest() {
        ParseResponse parseResponse = service.parseString(" ab      cd      e  ");
        assertThat(parseResponse.getOutputString(), CoreMatchers.is("a    b    c    d   e"));
    }

    @Test
    public void parseString_seventhTest() {
        ParseResponse parseResponse = service.parseString(" abddddcd  ewwwqe  ");
        assertThat(parseResponse.getOutputString(), CoreMatchers.is("a b d d d dcdewwwqe"));
    }

    @Test
    public void parseString_thirdTest() {
        checkExceptionForInvalidString("");
    }

    @Test
    public void parseString_fourthTest() {
        checkExceptionForInvalidString("  ");
    }

    @Test
    public void parseString_fifthTest() {
        checkExceptionForInvalidString("ии");
    }

    private void checkExceptionForInvalidString(String invalidString) {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Строка должна начинаться и заканчиваться пробелом, а также содержать минимум две буквы!");
        service.parseString(invalidString);
    }
}