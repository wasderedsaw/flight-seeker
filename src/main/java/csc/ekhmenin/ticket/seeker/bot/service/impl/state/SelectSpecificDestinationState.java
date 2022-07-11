package csc.ekhmenin.ticket.seeker.bot.service.impl.state;

import csc.ekhmenin.ticket.seeker.bot.service.CityService;
import csc.ekhmenin.ticket.seeker.bot.service.dto.Request;
import csc.ekhmenin.ticket.seeker.bot.service.stub.CityServiceStub;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class SelectSpecificDestinationState implements State {

    private final CityService cityService = new CityServiceStub();

    @Override
    public State next(String userInput, Request request) {
        if (cityService.verifyCity(userInput)) {
            request.setTo(userInput);
            if (request.isReturn()) {
                return new SelectDateTypeState();
            }
            return new TicketSelectionState();
        }
        return new InvalidDestinationCityState();
    }

    @Override
    public SendMessage generateMessage(Request request) {
        SendMessage message = new SendMessage();
        message.setText("Введите название города, в который хотите полететь");
        return message;
    }
}
