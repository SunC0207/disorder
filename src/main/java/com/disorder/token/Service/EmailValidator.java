package com.disorder.token.Service;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class EmailValidator implements Predicate<String> {

    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private final Pattern pattern = Pattern.compile(EMAIL_REGEX,Pattern.CASE_INSENSITIVE);

    @Override
    public boolean test(String email) {
        if (email == null || email.isEmpty()){
            return false;
        }

        return pattern.matcher(email).matches();
    }
}
