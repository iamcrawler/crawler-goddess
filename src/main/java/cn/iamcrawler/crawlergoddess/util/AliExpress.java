package cn.iamcrawler.crawlergoddess.util;

import cn.iamcrawler.crawlergoddess.dto.AliExpressResult;
import cn.iamcrawler.crawlergoddess.dto.ExpressDetail;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liuliang on 2019/4/10.
 */
@Slf4j
public class AliExpress {


    private static final String host = "http://aliapi.kuaidi.com";
    private static final String  path = "/kuaidiinfo";
    private static final String method = "GET";
    private static final String  appcode = "66222aeed42e42bab02daeef3d26c5f6";
    private static final String EXPRESS = "express:info:";


    public static AliExpressResult getAliExpress(String code) {
        AliExpressResult result = new AliExpressResult();
        //已完成的先从缓存取，没取到再调用接口
        String info = RedisUtil.get(EXPRESS+code);
        if(StringUtils.isNotEmpty(info)){
            Map<String, Object> map = JSONUtil.json2map(info);
            log.info(code+"========================从缓存获取物流信息...");
            return ObjectUtils.parseMap2Object(map, AliExpressResult.class);
        }else {
            Map<String, String> headers = new HashMap<String, String>();
            //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
            headers.put("Authorization", "APPCODE " + appcode);
            Map<String, String> querys = new HashMap<String, String>();
            querys.put("nu", code);
            querys.put("com", "");
            querys.put("muti", "0");
            querys.put("order", "desc");
            querys.put("id",appcode);
            try {
                HttpResponse response = HttpUtil.doGet(host, path, method, headers, querys);
                log.info("response:{}",response);
                String s = EntityUtils.toString(response.getEntity());
                if(!ObjectUtils.isNullOrEmpty(s)){
                    HashMap parse = JSON.parseObject(s,HashMap.class);
                    log.info("parse:{}",parse);
                    result = ObjectUtils.parseMap2Object(parse, AliExpressResult.class);
                    log.info("result:{}",result);
                    if(StringUtils.isNotEmpty(result.getReason())){
                    }
                }
            }

            catch (Exception e){
                e.printStackTrace();
                //查不到或者有异常，不抛，筛到数据里面
                List<ExpressDetail> list = new ArrayList<>();
                ExpressDetail detail = new ExpressDetail();
                detail.setTag("0");
                detail.setTagName(result.getReason());
                detail.setContext(result.getReason());
                list.add(detail);
                result.setData(list);
                result.setNu(code);
            }

            //处理tag标志
            dealTags(result);
            if("6".equals(result.getStatus())){//签收则永久缓存
                log.info(code+"========================已签收，第一次查询，存入缓存...");
                String jsonString = JSON.toJSONString(result);
                RedisUtil.set(EXPRESS+code,jsonString);
            }else {//否则缓存2小时
                log.info(code+"=======================还没签收,缓存两小时.....");
                String jsonString = JSON.toJSONString(result);
                RedisUtil.set(EXPRESS+code,jsonString,60*60*2);
            }
        }
        return result;
    }

    public static void dealTags(AliExpressResult result){

        List<ExpressDetail> datas = result.getData();

        if(!CollectionUtils.isEmpty(datas)){
            datas.forEach(
                    data->{
                        data.setTag(getTag(data.getContext()));
                        data.setTagName(getTagName(data.getTag()));
                    }
            );
            result.setData(datas);
        }
    }



    public static String getTag(String add){

        if(isContainChinese(add,"已发货") || isContainChinese(add,"发货")){
            return "1";
        }else if(isContainChinese(add,"已揽件") || isContainChinese(add,"揽件") ||isContainChinese(add,"揽收")){
            return "2";
        }else if(isContainChinese(add,"运输中") || isContainChinese(add,"运输")){
            return "3";
        }else if(isContainChinese(add,"派送中") || isContainChinese(add,"派送")){
            return "4";
        }else if(isContainChinese(add,"已签收") || isContainChinese(add,"签收")){
            return "5";
        }
        return "0";
    }

    public static String getTagName(String tag){
        switch (tag){
            case "1":
                return "已发货";
            case "2":
                return "已揽件";
            case "3":
                return "运输中";
            case "4":
                return "派送中";
            case "5":
                return "已签收";
            case "0":
                return "";
            default: return "";
        }
    }


    public static boolean isContainChinese(String str,String tag) {
        Pattern p = Pattern.compile(tag);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

//    public static void main(String[] args) {
//
//
//        BigDecimal a = new BigDecimal(1);
//        a = a.add(new BigDecimal(2));
//
//        System.out.println(a);
//
//    }

}
