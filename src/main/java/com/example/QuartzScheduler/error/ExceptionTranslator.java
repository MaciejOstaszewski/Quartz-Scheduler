package com.example.QuartzScheduler.error;


import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.nonNull;

@Slf4j
public class ExceptionTranslator {
    private static final String NO_MESSAGE_AVAILABLE = "(no message available)";
    private static final String UNSPECIFIED = "(unspecified)";

    private String getMessage(Exception ex) {
        return nonNull(ex.getMessage()) ? ex.getMessage() : NO_MESSAGE_AVAILABLE;
    }

    private String getCause(Exception ex) {
        return nonNull(ex.getCause()) ? String.valueOf(ex.getCause()) : UNSPECIFIED;
    }

    protected void logError(String method, Exception ex) {
        log.error("{}({}) with cause: {} and exception: {}", method, ex.getClass(), getCause(ex), getMessage(ex));
        log.error("Exception ", ex);
    }


}

