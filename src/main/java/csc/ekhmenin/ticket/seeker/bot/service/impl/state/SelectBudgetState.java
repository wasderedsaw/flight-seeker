package csc.ekhmenin.ticket.seeker.bot.service.impl.state;

import csc.ekhmenin.ticket.seeker.bot.service.dto.Request;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class SelectBudgetState implements State {
    @Override
    public State next(String userInput, Request request) {
        if (userInput.matches("[1-9][0-9]+")) {
            request.setBudget(Integer.parseInt(userInput));
            return new SelectIfDestinationIsKnownState();
        }
        return new InvalidBudgetState();
    }

    @Override
    public SendMessage generateMessage(Request request) {
        SendMessage message = new SendMessage();
        message.setText("Введите максимальную сумму, которую вы готовы потратить на билеты");
        return message;
    }
}
