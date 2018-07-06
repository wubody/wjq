package cn.edu.service;

import cn.edu.pojo.Auctionuser;

public interface AuctionuserService {
    Auctionuser selectByUserName(String userName);
    void addUser(Auctionuser auctionuser);
}
