package com.narcizo.elotech.Utils;

public class MyUtils {
    public static String validateEmail(String email){
        if(email.matches("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
            return email;
        return "";
    }

    public static String validatePhoneNumber(String phoneNumber){
        // Aceita input de celular (xx)9xxxx-xxxx
        if (phoneNumber.matches("^\\([1-9]{2}\\)[9]{0,1}[5-9]{1}[0-9]{3}\\-[0-9]{4}$"))
            return phoneNumber;
        return "";
    }

    public static String validateCpf(String cpf){
        // Aceita input de cpf xxx.xxx.xxx-xx
        String nakedCpf = cpf.replaceAll("[^0-9]", "");

        if (nakedCpf.length() != 11 || nakedCpf.matches("(\\d)\\1{10}"))
            return "";

        int[] digits = extractDigits(11, nakedCpf);

        int firstVerifier = calculateVerifierDigit(digits, 9, 10);
        int secondVerifier = calculateVerifierDigit(digits, 10, 11);

        if (digits[9] == firstVerifier && digits[10] == secondVerifier)
            return cpf;

        return "";
    }

    private static int[] extractDigits(int size, String number){
        int[] digits = new int[size];
        for (int i = 0; i < size; i++) {
            digits[i] = number.charAt(i) - '0';
        }

        return digits;
    }

    private static int calculateVerifierDigit(int[] digits, int length, int weight) {
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += digits[i] * weight;
            weight = (weight == 2) ? 9 : weight - 1;
        }
        int remainder = sum % 11;
        return (remainder < 2) ? 0 : 11 - remainder;
    }
}