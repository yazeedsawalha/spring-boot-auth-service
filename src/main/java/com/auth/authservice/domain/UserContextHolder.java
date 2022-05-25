package com.auth.authservice.domain;

public class UserContextHolder {
    private static final ThreadLocal<UserContext> USER_CONTEXT = new InheritableThreadLocal<>();

    private UserContextHolder() {
    }

    public static void set(final UserContext userContext) {
        USER_CONTEXT.set(userContext);
    }

    public static UserContext get() {
        return USER_CONTEXT.get();
    }

    public static void remove() {
        USER_CONTEXT.remove();
    }


    private void setLanguage(final String language) {
        final UserContext userContext = get();
        if (language == null || userContext == null) {
            return;
        }
        userContext.setLanguage(language);
    }

}
