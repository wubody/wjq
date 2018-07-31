package cn.edu.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="auction_log")
public class AuctionLog {
    String user;
    int adminType;
    String actionType;
    Auction auction;
    Auction oldauction;
    public AuctionLog(){}
    public AuctionLog(String user,int adminType,String actionType,Auction auction){
        this.user=user;
        this.adminType=adminType;
        this.actionType=actionType;
        this.auction=auction;
    }
    public AuctionLog(String user,int adminType,String actionType,Auction auction,Auction oldauction){
        this.user=user;
        this.adminType=adminType;
        this.actionType=actionType;
        this.auction=auction;
        this.oldauction=oldauction;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getAdminType() {
        return adminType;
    }

    public void setAdminType(int adminType) {
        this.adminType = adminType;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public Auction getOldauction() {
        return oldauction;
    }

    public void setOldauction(Auction oldauction) {
        this.oldauction = oldauction;
    }
}
