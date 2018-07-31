package cn.edu.log.impl;

import cn.edu.log.AuctionLogDao;
import cn.edu.pojo.AuctionLog;
import cn.edu.uilt.AbstractBaseMongoTemplete;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.xml.registry.infomodel.User;

@Service("auctionLogDaoImpl")
public class AuctionLogDaoImpl extends AbstractBaseMongoTemplete implements AuctionLogDao {
    @Override
    public void insert(AuctionLog auctionlog) {
        mongoTemplate.insert(auctionlog);
    }

    @Override
    public AuctionLog findById(String id) {
        return mongoTemplate.findById(id,AuctionLog.class);
    }



}
