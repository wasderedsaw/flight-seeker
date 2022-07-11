package csc.ekhmenin.ticket.seeker.bot.service.impl.state;

import csc.ekhmenin.ticket.seeker.bot.service.CityService;
import csc.ekhmenin.ticket.seeker.bot.service.dto.Request;
import csc.ekhmenin.ticket.seeker.bot.service.stub.CityServiceStub;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class SelectDepartureCityState implements State {

    private final CityService cityService = new CityServiceStub();

    @Override
    public State next(String cityName, Request request) {
        if (cityService.verifyCity(cityName)) {
            request.setFrom(cityName);
            return new SelectFlightTypeState();
        }
        return new InvalidDepartureCityState();
    }

    @Override
    public SendMessage generateMessage(Request request) {
        SendMessage message = new SendMessage();
        message.setText("Введите название города вылета");
        return message;
    }
}
