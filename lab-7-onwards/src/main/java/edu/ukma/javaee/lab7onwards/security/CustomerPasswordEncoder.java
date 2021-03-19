package edu.ukma.javaee.lab7onwards.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class CustomerPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        byte[] byteString = new byte[charSequence.length()];
        for (int i = 0; i < charSequence.length(); i++) {
            byteString[i] = (byte) charSequence.charAt(i);
        }
        return Base64.getEncoder().encodeToString(byteString);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return encode(charSequence).equals(s);
    }
}
