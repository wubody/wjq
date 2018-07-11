package cn.edu.service.impl;

import cn.edu.dao.AuctionCustomMapper;
import cn.edu.dao.AuctionMapper;
import cn.edu.dao.AuctionrecordMapper;
import cn.edu.pojo.*;
import cn.edu.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("auctionService")
public class AuctionServiceImpl implements AuctionService{
    @Autowired
    AuctionMapper auctionMapper;
    @Autowired
    AuctionCustomMapper auctionCustomMapper;
    @Autowired
    AuctionrecordMapper auctionrecordMapper;
    //查询商品
    @Override
    public List<Auction> findAuctions(Auction auction) {

        AuctionExample auctionExample=new AuctionExample();
        AuctionExample.Criteria criteria=auctionExample.createCriteria();
        if(auction!=null){
            if (auction.getAuctionname() != null && !"".equals(auction.getAuctionname())) {
                criteria.andAuctionnameLike("%"+auction.getAuctionname()+"%");
            }
            if(auction.getAuctiondesc()!=null&&!"".equals(auction.getAuctiondesc())){
                criteria.andAuctiondescLike("%"+auction.getAuctiondesc()+"&");
            }
            if(auction.getAuctionstarttime()!=null){
                criteria.andAuctionstarttimeGreaterThan(auction.getAuctionstarttime());
            }
            if (auction.getAuctionendtime()!=null){
                criteria.andAuctionendtimeLessThan(auction.getAuctionendtime());
            }
            if(auction.getAuctionstartprice()!=null){
                criteria.andAuctionstartpriceGreaterThan(auction.getAuctionstartprice());
            }
        }
        auctionExample.setOrderByClause("auctionstarttime desc");
        return auctionMapper.selectByExample(auctionExample);
    }
    //添加商品
    @CachePut(value = {"getAuctionById"},key = "#auctionid  ")
    @Override
    public void  addAuction(Auction auction){
        auctionMapper.insert(auction);
    }
    //通过商品id查询商品
    @Cacheable(value = "getAuctionById",key="#auctionid")
    @Override
    public Auction getAuctionById(int auctionid) {
        return auctionMapper.selectByPrimaryKey(auctionid);
    }
    //更新商品信息
    @CachePut(value = {"getAuctionById"},key = "#auctionid")
    @Override
    public void updateAuction(Auction auction) {
        auctionMapper.updateByPrimaryKey(auction);
    }

    //通过商品id查询商品信息和该商品的竞拍信息
    @Override
    public Auction findAuctionAndRecordListById(int auctionid) {
        return auctionCustomMapper.findAuctionAndRecordListById(auctionid);
    }
    //添加商品竞拍价格
    @Override
    public void addAuctionRecord(Auctionrecord auctionrecord) throws Exception{
        Auction auction=auctionCustomMapper.findAuctionAndRecordListById(auctionrecord.getAuctionid());
        if(auction.getAuctionendtime().after(new Date()) == false){
            throw new Exception("竞拍时间已过期");
        }else{
            if(auction.getrecordList()!=null && auction.getrecordList().size()>1){
                Auctionrecord maxRecord=auction.getrecordList().get(0);
                if (auctionrecord.getAuctionprice() <= maxRecord.getAuctionprice()){
                    throw new Exception("价格必须高于所有的竞拍价");
                }
            }else {
                if (auctionrecord.getAuctionprice()<auction.getAuctionstartprice()){
                    throw new Exception("价格必须高于起拍价");
                }
            }
        }
        auctionrecordMapper.insert(auctionrecord);
    }
    //通过商品id删除商品
    @CacheEvict(value = {"getAuctionById"},key = "#auctionid")
    @Override
    public void deleteAuction(int auctionid) {
        AuctionrecordExample example=new AuctionrecordExample();
        AuctionrecordExample.Criteria criteria=example.createCriteria();
        criteria.andAuctionidEqualTo(auctionid);
        auctionrecordMapper.deleteByExample(example);
        auctionMapper.deleteByPrimaryKey(auctionid);
    }
    //已经结束拍卖的商品列表
    @Override
    public List<AuctionCustom> finAuctionEndtimeList() {
        return auctionCustomMapper.finAuctionEndtimeList();
    }
    //还未结束拍卖的商品列表
    @Override
    public List<Auction> findAuctionNoEndtimeList() {
        return auctionCustomMapper.findAuctionNoEndtimeList();
    }

    //更改



}
