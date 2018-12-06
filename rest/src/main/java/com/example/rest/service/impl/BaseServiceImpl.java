package com.example.rest.service.impl;

import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@CommonsLog
public class BaseServiceImpl {

    public <T> Stream<T> nullSafeStreamOf(Collection<T> collection) {
        return Optional.ofNullable(collection)
                .map(Collection::stream)
                .orElse(Stream.empty());
    }

    private static final String EMPTY = StringUtils.EMPTY;

    @Autowired
    private MessageSource messageSource;


    private static final String VALIDATION_ERROR_CODE = "ValidationError";

    // Helper Method To read message from messages.properties file -- STARTS--

    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }

    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        return messageSource.getMessage(code, args, locale);
    }

    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        return messageSource.getMessage(resolvable, locale);
    }

    public String getMessage(String messageKey) {
        return getMessage(messageKey, EMPTY);
    }

    public String getMessage(String messageKey, Object... args) {
        return messageSource.getMessage(messageKey, args, LocaleContextHolder.getLocale());
    }
}
