package cn.edu.service;

import cn.edu.pojo.Auction;
import cn.edu.pojo.AuctionCustom;
import cn.edu.pojo.Auctionrecord;

import java.util.List;

public interface AuctionService {
    List<Auction> findAuctions(Auction auction);
    void  addAuction(Auction auction,String user,int amdinType);
    Auction getAuctionById(int auctionid);
    void updateAuction(Auction auction);
    Auction findAuctionAndRecordListById(int auctionid);
    void addAuctionRecord(Auctionrecord auctionrecord) throws Exception;
    void deleteAuction(int auctionid);
    List<AuctionCustom> finAuctionEndtimeList();
    List<Auction> findAuctionNoEndtimeList();
}
