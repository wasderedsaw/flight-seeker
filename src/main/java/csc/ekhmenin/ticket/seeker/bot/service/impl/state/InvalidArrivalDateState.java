package csc.ekhmenin.ticket.seeker.bot.service.impl.state;

import csc.ekhmenin.ticket.seeker.bot.service.dto.Request;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class InvalidArrivalDateState implements State {
    @Override
    public State next(String userInput, Request request) {
        return new SelectArrivalDateState().next(userInput, request);
    }

    @Override
    public SendMessage generateMessage(Request request) {
        SendMessage message = new SendMessage();
        message.setText("Дата введена некорректно, попробуйте еще раз");
        return message;
    }
}
