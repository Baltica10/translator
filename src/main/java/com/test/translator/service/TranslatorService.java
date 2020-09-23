package com.test.translator.service;

import com.test.translator.dto.RequestDto;

public interface TranslatorService {

    String translate(RequestDto dto);
}
