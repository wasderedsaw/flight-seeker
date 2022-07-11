package csc.ekhmenin.ticket.seeker.bot.service.impl;

import csc.ekhmenin.ticket.seeker.bot.service.DateValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidatorImpl implements DateValidator {

    private final DateTimeFormatter dateFormatter;

    public DateValidatorImpl(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    @Override
    public boolean isValid(String dateStr) {
        try {
            LocalDate.parse(dateStr, this.dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
