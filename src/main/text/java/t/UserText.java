package t;

import cn.edu.log.AuctionLogDao;
import cn.edu.log.AuctionUserLogDao;
import cn.edu.pojo.AuctionLog;
import cn.edu.pojo.AuctionUserLog;
import cn.edu.pojo.Auctionuser;
import cn.edu.service.AuctionuserService;
import org.junit.runner.RunWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class UserText {

    @Autowired
    AuctionuserService auctionuserService;
    Auctionuser auctionuser=new Auctionuser();


    @Test
    public void a(){
        auctionuser.setUsername("789789");
        auctionuser.setUserpassword("789789");
        auctionuser.setUserpostnumber("999");
        auctionuser.setUserquestion("999");
        auctionuser.setUsercardno("999");
        auctionuser.setUsertel("999");
        auctionuser.setUserisadmin(0);

        auctionuserService.addUser(auctionuser);
    }


}
