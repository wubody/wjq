package cn.edu.service.impl;

import cn.edu.dao.AuctionuserMapper;
import cn.edu.log.AuctionUserLogDao;
import cn.edu.pojo.AuctionUserLog;
import cn.edu.pojo.Auctionuser;
import cn.edu.service.AuctionuserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    AuctionUserLogDao auctionUserLogDao;

    AuctionUserLog auctionUserLog=new AuctionUserLog();

    //通过username查找用户，并存到redis里
    @Cacheable(value = "selectByUserName",key="#userName")
    @Override
    public Auctionuser selectByUserName(String userName){

        return auctionuserMapper.selectByUsername(userName);
    }
    //添加user
    @Transactional
    @CachePut(value = "selectByUserName",key = "#auctionuser.getUsername().toString()")
    @Override
    public void addUser(Auctionuser auctionuser) {
        auctionUserLog.setActionType("新增用户");
        auctionUserLog.setAuctionuser(auctionuser);
        auctionUserLogDao.insert(auctionUserLog);
        auctionuserMapper.insert(auctionuser);
    }




}
