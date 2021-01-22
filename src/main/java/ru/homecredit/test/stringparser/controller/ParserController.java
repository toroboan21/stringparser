package ru.homecredit.test.stringparser.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.homecredit.test.stringparser.dto.ParseRequest;
import ru.homecredit.test.stringparser.dto.ParseResponse;
import ru.homecredit.test.stringparser.service.ParseService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stringparser/v1")
public class ParserController {
    private final ParseService parseService;

    @PostMapping("/parse")
    public ResponseEntity<ParseResponse> parseString(@RequestBody @Validated ParseRequest parseRequest) {
        try {
            return ResponseEntity.ok(parseService.parseString(parseRequest.getInputString()));
        } catch (IllegalArgumentException e) {
            ParseResponse parseResponse = new ParseResponse();
            parseResponse.setErrorMessage(e.getMessage());
            return ResponseEntity.badRequest().body(parseResponse);
        }
    }
}
