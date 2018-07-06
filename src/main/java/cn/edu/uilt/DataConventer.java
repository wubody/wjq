package cn.edu.uilt;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//时间格式转换类
public class DataConventer implements Converter<String,Date>{
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Override
    public Date convert(String time) {
        try {
            return simpleDateFormat.parse(time);

        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }
}
