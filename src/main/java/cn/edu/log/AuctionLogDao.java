package cn.edu.log;

import cn.edu.pojo.AuctionLog;
import org.springframework.stereotype.Repository;

public interface AuctionLogDao {
    void insert(AuctionLog auctionlog);
    AuctionLog findById(String id);

}
