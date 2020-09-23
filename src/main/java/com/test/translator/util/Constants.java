package com.test.translator.util;

public class Constants {

    public static final String RU = "RU";
    public static final String EN = "EN";

    public static final Integer MAX_WORDS = 100000;

    //<== Patterns ==>
    public static final String TEXT_PATTERN = "[-{}\\[\\].,!: а-яёА-ЯЁ0-9]+";

    //<== Exception ==>
    public static final String CURRENT_LANGUAGE_NOT_SUPPORT = "Current language not support";
    public static final String TARGET_LANGUAGE_NOT_SUPPORT = "Target language not support";
    public static final String TEXT_NOT_FOUND = "Text not found";
    public static final String TEXT_MANY_WORDS = "Exceeded the number of words in the text";
    public static final String TEXT_NOT_SUPPORT = "Text not support, symbol only allowed: а-яёА-ЯЁ0-9 { } ! , . : [ ] -   ";
}
