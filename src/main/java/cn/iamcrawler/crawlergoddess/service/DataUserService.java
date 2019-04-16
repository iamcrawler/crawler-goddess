package cn.iamcrawler.crawlergoddess.service;

import cn.iamcrawler.crawler_common.domain.goddess.DataUser;
import cn.iamcrawler.crawlergoddess.mapper.DataUserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by liuliang on 2019/3/21.
 */
@Service
@Slf4j
public class DataUserService extends ServiceImpl<DataUserMapper,DataUser>{


    private static  final  String DATA_USER= "data:user:";

    @Autowired
    private RedisTemplate redisTemplate;

    public void queryDataUsers(){
        List<DataUser> result = this.list(
                new QueryWrapper<DataUser>()
                        .orderBy(true,false,"register_time_label")
        );
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        result.forEach(
                dataUser -> {
                    String date = dataUser.getDate();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Long time = 0L;
                    try {
                         Date parse = simpleDateFormat.parse(date);
                         time = parse.getTime();
                         log.info("source:{}",time);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    zSetOperations.add(DATA_USER,dataUser,time);
                }
        );
        log.info("=========所有用户已经加载完毕.....");
    }


    public IPage getDataUsersPage(IPage page, Boolean flag){
        if(flag){
            log.info("===========page:{}",page);
            ZSetOperations zSetOperations = redisTemplate.opsForZSet();
            Set<DataUser> range = zSetOperations.range(DATA_USER, page.getCurrent()-1,page.getSize());
            log.info("=============range:{}",range);
            page.setRecords(new ArrayList<>(range));
            page.setTotal(range.size());//todo  这个total不准确
            return  page;
        }

        return this.page(page, new QueryWrapper<DataUser>().orderBy(true,false,"register_time_label"));
    }

}
