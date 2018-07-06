package cn.edu.pojo;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class Auctionuser {
    private int userid;
    @Size(min=1,max = 20,message = "用户名错误")
    private String username;
    @Size(min = 6,message = "密码错误")
    private String userpassword;
    @Pattern(regexp="\\d{18}",message="身份证要18位")
    private String usercardno;

    private String usertel;

    private String useraddress;

    private String userpostnumber;

    private int userisadmin;

    private String userquestion;

    private String useranswer;




    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null ? null : userpassword.trim();
    }

    public String getUsercardno() {
        return usercardno;
    }

    public void setUsercardno(String usercardno) {
        this.usercardno = usercardno == null ? null : usercardno.trim();
    }

    public String getUsertel() {
        return usertel;
    }

    public void setUsertel(String usertel) {
        this.usertel = usertel == null ? null : usertel.trim();
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress == null ? null : useraddress.trim();
    }

    public String getUserpostnumber() {
        return userpostnumber;
    }

    public void setUserpostnumber(String userpostnumber) {
        this.userpostnumber = userpostnumber == null ? null : userpostnumber.trim();
    }

    public Integer getUserisadmin() {
        return userisadmin;
    }

    public void setUserisadmin(Integer userisadmin) {
        this.userisadmin = userisadmin;
    }

    public String getUserquestion() {
        return userquestion;
    }

    public void setUserquestion(String userquestion) {
        this.userquestion = userquestion == null ? null : userquestion.trim();
    }

    public String getUseranswer() {
        return useranswer;
    }

    public void setUseranswer(String useranswer) {
        this.useranswer = useranswer == null ? null : useranswer.trim();
    }


}