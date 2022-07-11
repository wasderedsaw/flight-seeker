package csc.ekhmenin.ticket.seeker.bot.service.impl.state;

import csc.ekhmenin.ticket.seeker.bot.service.DateValidator;
import csc.ekhmenin.ticket.seeker.bot.service.dto.Request;
import csc.ekhmenin.ticket.seeker.bot.service.impl.DateValidatorImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.format.DateTimeFormatter;

public class SelectArrivalDateState implements State {

    private final DateValidator dateValidator = new DateValidatorImpl(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    @Override
    public State next(String userInput, Request request) {
        if (dateValidator.isValid(userInput)) {
            request.setDateTo(userInput);
            return new TicketSelectionState();
        }
        return new InvalidArrivalDateState();
    }

    @Override
    public SendMessage generateMessage(Request request) {
        SendMessage message = new SendMessage();
        message.setText("Введите дату возвращения в формате 30.06.2021");
        return message;
    }
}
