package cn.edu.dao;

import cn.edu.pojo.Auction;
import cn.edu.pojo.AuctionCustom;

import java.util.List;

public interface AuctionCustomMapper {
    public Auction findAuctionAndRecordListById(int auctionid);
    public List<AuctionCustom> finAuctionEndtimeList();
    public List<Auction> findAuctionNoEndtimeList();
}
