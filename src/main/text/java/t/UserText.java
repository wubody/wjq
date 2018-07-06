package t;

import cn.edu.dao.AuctionuserMapper;
import cn.edu.pojo.Auctionuser;
import org.junit.runner.RunWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class UserText {
    @Autowired
    AuctionuserMapper auctionuserMapper;
    @Test
    public void a(){
        Auctionuser auctionuser=auctionuserMapper.selectByUsername("kk");
        System.out.print(auctionuser.getUserid());
    }


}
