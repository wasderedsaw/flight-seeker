package csc.ekhmenin.ticket.seeker.bot.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Request {
    private String from = null;
    private String to = null;
    private boolean isReturn = false;
    private int travellerAmount = 1;
    private int budget = -1;
    private String dateFrom = null;
    private String dateTo = null;
    private String dateType = null;
}
