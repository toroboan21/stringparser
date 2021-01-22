package ru.homecredit.test.stringparser.service;

import org.springframework.stereotype.Service;
import ru.homecredit.test.stringparser.dto.ParseResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParseService {

    private boolean checkWhitespaceRule(String inputString) {
        return inputString.endsWith(" ") && inputString.startsWith(" ");
    }

    private boolean checkLettersRule(String inputString) {
        return inputString.matches("^\\s[a-zA-Zа-яА-Я\\s]{2,}\\s$");
    }

    public ParseResponse parseString(String inputString) {
        if (!checkLettersRule(inputString) || !checkWhitespaceRule(inputString)) {
            throw new IllegalArgumentException("Строка должна начинаться и заканчиваться пробелом, а также содержать минимум две буквы!");
        }

        //знаем что всегда минимум 2 пробела по условиям задачи
        int whiteSpacesNumber = 2;
        List<Character> lettersList = new ArrayList<>();

        //пробегаем по строке, считаем пробелы, буквы добавляем в отдельный список (не учитываем первый и последний символ, т.к. знаем по условиям, что это всегда пробел)
        for (int i = 1; i < inputString.length() - 1; i++) {
            char ch = inputString.charAt(i);
            if (Character.isWhitespace(ch)) {
                whiteSpacesNumber++;
            } else {
                lettersList.add(ch);
            }
        }

        //считаем кол-во букв (последнюю не учитываем, т.к. знаем, что после нее пробела не будет)
        int numberOfLettersWithWhiteSpaces = lettersList.size() - 1;

        //считаем кол-во пробелов между буквами в ответе
        int numberOfWhitespacesPlacements = whiteSpacesNumber / numberOfLettersWithWhiteSpaces;

        //считаем избыток пробелов
        int whiteSpacesRemainder = whiteSpacesNumber - (numberOfWhitespacesPlacements * numberOfLettersWithWhiteSpaces);

        //считаем между сколькими буквами распределяем избыток пробелов
        int numberOfLettersWithAdditionalWhiteSpaces = whiteSpacesRemainder % numberOfLettersWithWhiteSpaces;

        //считаем по сколько избыточных пробелов распределяем
        int numberAdditionalWhiteSpaces = 0;

        if (numberOfLettersWithAdditionalWhiteSpaces != 0) {
            numberAdditionalWhiteSpaces = whiteSpacesRemainder / numberOfLettersWithAdditionalWhiteSpaces;
        }

        StringBuilder stringBuilder = new StringBuilder(inputString.length());

        for (int i = 0; i < numberOfLettersWithWhiteSpaces; i++) {
            stringBuilder.append(lettersList.get(i));
            stringBuilder.append(" ".repeat(numberOfWhitespacesPlacements));
            if (i < numberOfLettersWithAdditionalWhiteSpaces) {
                stringBuilder.append(" ".repeat(numberAdditionalWhiteSpaces));
            }
        }
        stringBuilder.append(lettersList.get(lettersList.size() - 1));

        ParseResponse parseResponse = new ParseResponse();
        parseResponse.setOutputString(stringBuilder.toString());

        return parseResponse;
    }
}
