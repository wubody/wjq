package cn.edu.service;

import cn.edu.pojo.Auctionuser;
//竞拍用户服务接口类
public interface AuctionuserService {
    //
    Auctionuser selectByUserName(String userName);
    void addUser(Auctionuser auctionuser);
}
