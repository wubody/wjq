package cn.edu.log.impl;

import cn.edu.log.AuctionUserLogDao;
import cn.edu.pojo.AuctionUserLog;
import cn.edu.pojo.Auctionuser;
import cn.edu.uilt.AbstractBaseMongoTemplete;
import org.springframework.stereotype.Service;

@Service("auctionUserLogImpl")
public class AuctionUserLogImpl extends AbstractBaseMongoTemplete implements AuctionUserLogDao{
    @Override
    public void insert(AuctionUserLog auctionUserLog) {
        mongoTemplate.insert(auctionUserLog);
    }
}
