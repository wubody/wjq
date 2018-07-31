package cn.edu.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "auction_user_log")
public class AuctionUserLog {

    String actionType;
    Auctionuser auctionuser;
    public AuctionUserLog(){};
    public AuctionUserLog(String actionType,Auctionuser auctionuser){
        this.actionType=actionType;
        this.auctionuser=auctionuser;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public Auctionuser getAuctionuser() {
        return auctionuser;
    }

    public void setAuctionuser(Auctionuser auctionuser) {
        this.auctionuser = auctionuser;
    }
}
