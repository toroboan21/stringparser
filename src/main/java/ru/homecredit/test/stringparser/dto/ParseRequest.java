package ru.homecredit.test.stringparser.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ParseRequest {
    @NotNull(message = "Невозможно обработать пустое значение! Необходимо передать строку!")
    String inputString;
}
