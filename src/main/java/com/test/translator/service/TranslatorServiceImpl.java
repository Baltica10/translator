package com.test.translator.service;

import com.test.translator.dto.RequestDto;
import com.test.translator.exception.ValidationException;
import com.test.translator.model.Dictionary;
import com.test.translator.repository.DictionaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.test.translator.util.Constants.CURRENT_LANGUAGE_NOT_SUPPORT;
import static com.test.translator.util.Constants.EN;
import static com.test.translator.util.Constants.MAX_WORDS;
import static com.test.translator.util.Constants.RU;
import static com.test.translator.util.Constants.TARGET_LANGUAGE_NOT_SUPPORT;
import static com.test.translator.util.Constants.TEXT_MANY_WORDS;
import static com.test.translator.util.Constants.TEXT_NOT_FOUND;
import static com.test.translator.util.Constants.TEXT_NOT_SUPPORT;
import static com.test.translator.util.Constants.TEXT_PATTERN;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@Service
public class TranslatorServiceImpl implements TranslatorService {

    private final DictionaryRepository dictionaryRepository;

    public TranslatorServiceImpl(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    @Override
    public String translate(RequestDto dto) {
        log.info("{}->{} Text: {}", dto.getCurrentLanguage(), dto.getTargetLanguage(), dto.getText());

        validateLanguageFromToParam(dto);
        validateText(dto.getText());

        List<String> words = getStingParts(dto.getText());

        List<Dictionary> dictionaryList = dictionaryRepository.findAllByRuInIgnoreCase(words);

        var translateText = new Object() {
            String result = "";
        };
        words.forEach(word -> {

            Dictionary wordTranslate = dictionaryList.stream()
                    .filter(v -> Objects.equals(word.toLowerCase(), v.getRu()))
                    .findAny()
                    .map(v -> {
                        if (Character.isUpperCase(word.charAt(0))) {
                            v.setEn(v.getEn().substring(0, 1).toUpperCase() + v.getEn().substring(1));
                        }
                        return v;
                    })
                    .orElse(null);

            if (nonNull(wordTranslate)) {
                translateText.result = translateText.result + wordTranslate.getEn();
            } else {
                translateText.result = translateText.result + word;
            }
        });

        return translateText.result;
    }

    private void validateLanguageFromToParam(RequestDto dto) {
        if (isNull(dto.getCurrentLanguage()) || !dto.getCurrentLanguage().equalsIgnoreCase(RU)) {
            throw new ValidationException(CURRENT_LANGUAGE_NOT_SUPPORT);
        }
        if (isNull(dto.getTargetLanguage()) || !dto.getTargetLanguage().equalsIgnoreCase(EN)) {
            throw new ValidationException(TARGET_LANGUAGE_NOT_SUPPORT);
        }
    }

    private void validateText(String text) {
        if (isNull(text) || text.isBlank()) {
            throw new ValidationException(TEXT_NOT_FOUND);
        }
        if (!text.matches(TEXT_PATTERN)) {
            throw new ValidationException(TEXT_NOT_SUPPORT);
        }
    }

    private List<String> getStingParts(String text) {
        List<String> result = new ArrayList<>();
        String tempWord = "";
        String tempNotWord = "";
        int wordCount = 0;

        for (int i = 0; i < text.length(); i++) {

            if (Character.isLetter(text.charAt(i))) {
                tempWord = tempWord + text.charAt(i);
            } else {
                tempNotWord = tempNotWord + text.charAt(i);
            }

            if (i > 0 && !Character.isLetter(text.charAt(i)) && Character.isLetter(text.charAt(i - 1))) {
                result.add(tempWord);
                wordCount++;
                tempWord = "";
            }
            if (i > 0 && Character.isLetter(text.charAt(i)) && !Character.isLetter(text.charAt(i - 1))) {
                result.add(tempNotWord);
                tempNotWord = "";
            }
            if (i == text.length() - 1) {
                if (!tempWord.isBlank()) {
                    result.add(tempWord);
                    wordCount++;
                } else {
                    result.add(tempNotWord);
                }
            }

            if (wordCount > MAX_WORDS) {
                throw new ValidationException(TEXT_MANY_WORDS);
            }
        }

        return result;
    }
}