package com.pos.posapi.util;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Generator {

    private final Random RANDOM = new Random();
    private final String NUMERIC = "0123456789";

    private final int[] NUMBERS = {0,1,2,3,4,5,6,7,8,9};
    private final String NUMERIC2 = "123456789";
    public final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public final static String CHARACTERS = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    //=====================================

    public String generateGeneralId(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(ALPHABET.charAt(new Random().nextInt(25)));
        }
        return builder.toString();
    }

    public String generateUserPassword() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            builder.append(CHARACTERS.charAt(new Random().nextInt(9)));
        }
        return builder.toString();
    }

    public int generateOtp() {
        Random random = new Random();
        int generatedOTP = 0;

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(NUMBERS.length);
            generatedOTP = generatedOTP * 10 + NUMBERS[index];
        }

        return generatedOTP;
    }

    public String generatePrefix() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            builder.append(ALPHABET.charAt(new Random().nextInt(25)));
        }
        return builder.toString();
    }

}
