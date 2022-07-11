package csc.ekhmenin.ticket.seeker.bot.service.impl.state;

import csc.ekhmenin.ticket.seeker.bot.service.dto.Request;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class InvalidTravellerAmountState implements State {
    @Override
    public State next(String userInput, Request request) {
        return new SelectTravellerAmountState().next(userInput, request);
    }

    @Override
    public SendMessage generateMessage(Request request) {
        SendMessage message = new SendMessage();
        message.setText("Неверно введено число путешественников. Введите цифру от 1 до 9");
        return message;
    }
}
