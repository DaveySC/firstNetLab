package com.example.test.util;

public class Converter {
    public static int[] convertId(String id) {
        return  id.chars().filter(Character::isDigit).map(Character::getNumericValue).toArray();
    }

}
