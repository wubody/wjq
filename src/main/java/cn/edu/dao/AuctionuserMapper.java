package cn.edu.dao;

import cn.edu.pojo.Auctionuser;
import cn.edu.pojo.AuctionuserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionuserMapper {
    long countByExample(AuctionuserExample example);

    int deleteByExample(AuctionuserExample example);

    int deleteByPrimaryKey(int userid);

    int insert(Auctionuser record);

    int insertSelective(Auctionuser record);

    List<Auctionuser> selectByExample(AuctionuserExample example);

    Auctionuser selectByPrimaryKey(int userid);

    Auctionuser selectByUsername(String username);

    int updateByExampleSelective(@Param("record") Auctionuser record, @Param("example") AuctionuserExample example);

    int updateByExample(@Param("record") Auctionuser record, @Param("example") AuctionuserExample example);

    int updateByPrimaryKeySelective(Auctionuser record);

    int updateByPrimaryKey(Auctionuser record);
}