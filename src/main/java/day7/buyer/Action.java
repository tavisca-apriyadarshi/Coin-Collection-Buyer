package day7.buyer;

import day7.AuctionDetails;
import day7.Communicator;

public class Action implements Communicator {
    private String actionValue = "";
    private String actionType = "";
    private AuctionDetails auctionDetails = null;

    @Override
    public String getActionValue() {
        return actionValue;
    }

    @Override
    public void setActionValue(String value) {
        actionValue = value;
    }

    @Override
    public String getActionType() {
        return actionType;
    }

    @Override
    public void setActionType(String type) {
        actionType = type;
    }

    @Override
    public AuctionDetails getAuctionDetails() {
        return auctionDetails;
    }

    @Override
    public void setAuctionDetails(AuctionDetails auctionDetails) {
        this.auctionDetails = auctionDetails;
    }
}
