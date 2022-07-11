package csc.ekhmenin.ticket.seeker.bot.service.stub;

import csc.ekhmenin.ticket.seeker.bot.service.CityService;

public class CityServiceStub implements CityService {
    @Override
    public boolean verifyCity(String cityName) {
        return "KSZ".equals(cityName);
    }
}
