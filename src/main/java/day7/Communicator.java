package day7;

public interface Communicator {
    public String getActionValue();
    public void setActionValue(String value);
    public String getActionType();
    public void setActionType(String type);
    public AuctionDetails getAuctionDetails();
    public void setAuctionDetails(AuctionDetails auctionDetails);
}
