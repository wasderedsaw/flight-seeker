package csc.ekhmenin.ticket.seeker.bot.service.impl.state;

import csc.ekhmenin.ticket.seeker.bot.service.dto.Request;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class SelectTravellerAmountState implements State {
    @Override
    public State next(String userInput, Request request) {
        if (userInput.strip().matches("^[1-9]$")) {
            request.setTravellerAmount(Integer.parseInt(userInput));
            return new SelectIfBudgetIsRestrictedState();
        }
        return new InvalidTravellerAmountState();
    }

    @Override
    public SendMessage generateMessage(Request request) {
        SendMessage message = new SendMessage();
        message.setText("Укажите цифру - количество путешественников(не более 9)");
        return message;
    }
}
