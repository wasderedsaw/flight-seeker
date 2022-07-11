package csc.ekhmenin.ticket.seeker.skypicker;

import csc.ekhmenin.ticket.seeker.bot.service.dto.Request;
import csc.ekhmenin.ticket.seeker.bot.service.dto.Response;
import csc.ekhmenin.ticket.seeker.skypicker.exception.TicketsNotFoundException;

import java.util.List;

public interface SkyPicker {
    List<Response> getTickets(Request request) throws TicketsNotFoundException;
}
