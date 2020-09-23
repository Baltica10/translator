package com.test.translator.controller;

import com.test.translator.dto.RequestDto;
import com.test.translator.service.TranslatorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TranslatorController {

    private final TranslatorService translatorService;

    public TranslatorController(TranslatorService translatorService) {
        this.translatorService = translatorService;
    }

    @ApiOperation("На данный момент реализован функционал перевода RU->EN")
    @PostMapping()
    public ResponseEntity<String> translateText(@RequestBody RequestDto dto) {
        return ResponseEntity.ok(translatorService.translate(dto));
    }
}
