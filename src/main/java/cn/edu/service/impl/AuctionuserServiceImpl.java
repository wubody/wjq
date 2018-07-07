package cn.edu.service.impl;

import cn.edu.dao.AuctionuserMapper;
import cn.edu.pojo.Auctionuser;
import cn.edu.service.AuctionuserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("auctionuserService")
@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
public class AuctionuserServiceImpl implements AuctionuserService {
    @Resource
    AuctionuserMapper auctionuserMapper;
    //通过username查找用户
    @Cacheable(value = "selectByUserName",key="#userName")
    @Override
    public Auctionuser selectByUserName(String userName){

        return auctionuserMapper.selectByUsername(userName);
    }
    //添加user
    @CachePut(value = "selectByUserName",key = "#userName")
    @Override
    public void addUser(Auctionuser auctionuser) {
        auctionuserMapper.insert(auctionuser);
    }
}
