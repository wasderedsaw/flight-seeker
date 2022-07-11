package csc.ekhmenin.ticket.seeker.skypicker.impl;

import csc.ekhmenin.ticket.seeker.bot.service.dto.Request;
import csc.ekhmenin.ticket.seeker.bot.service.dto.Response;
import csc.ekhmenin.ticket.seeker.skypicker.SkyPicker;
import csc.ekhmenin.ticket.seeker.skypicker.exception.TicketsNotFoundException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.List;

public class SkyPickerImpl implements SkyPicker {
    @Override
    public List<Response> getTickets(Request request) throws TicketsNotFoundException {
        HttpGet httpRequest = generateHttpRequest(request);
        CloseableHttpResponse response = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            response = httpClient.execute(httpRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response == null) {
            throw new TicketsNotFoundException();
        }
        System.out.println(response.getStatusLine().getStatusCode());
        return null;
    }
    
    private HttpGet generateHttpRequest(Request request) {
        HttpGet httpRequest = new HttpGet("https://api.skypicker.com/flights");
        httpRequest.setHeader("curr", "RUB");
        httpRequest.setHeader("v", "3");
        httpRequest.setHeader("locale", "ru");
        httpRequest.setHeader("sort", "price");
        httpRequest.setHeader("flyFrom", request.getFrom());
        httpRequest.setHeader("partner", "picky");
        httpRequest.setHeader("passengers", String.valueOf(request.getTravellerAmount()));
        String to = request.getTo();
        if ("russia".equals(to)) {
            httpRequest.setHeader("location_types", "country");
            httpRequest.setHeader("term", "russia");
        } else if ("schengen".equals(to)) {

        } else if ("whatever".equals(to)) {

        } else {
            httpRequest.setHeader("to", request.getTo());
        }
        if ("specific".equals(request.getDateType())) {
            httpRequest.setHeader("dateFrom", request.getDateFrom());
            httpRequest.setHeader("dateTo", request.getDateTo());
        } else {
            httpRequest.setHeader("onlyWeekends", "1");
        }
        if (request.isReturn()) {
            httpRequest.setHeader("typeFlight", "round");
        } else {
            httpRequest.setHeader("typeFlight", "oneway");
        }
        return httpRequest;
    }
}
