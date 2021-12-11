package com.getir.casestudy.util;

import com.getir.casestudy.security.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

public class Util {
    public static String getUserId() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return userDetails.getId();
    }
}
