package cn.edu.dao;

import cn.edu.pojo.Auction;

import java.util.List;

import cn.edu.pojo.AuctionExample;
import org.apache.ibatis.annotations.Param;


public interface AuctionMapper {
    long countByExample(AuctionExample example);

    int deleteByExample(AuctionExample example);

    int deleteByPrimaryKey(Integer auctionid);

    int insert(Auction record);

    int insertSelective(Auction record);

    List<Auction> selectByExample(AuctionExample example);

    Auction selectByPrimaryKey(Integer auctionid);

    int updateByExampleSelective(@Param("record") Auction record, @Param("example") AuctionExample example);

    int updateByExample(@Param("record") Auction record, @Param("example") AuctionExample example);

    int updateByPrimaryKeySelective(Auction record);

    int updateByPrimaryKey(Auction record);
}