package cn.iamcrawler.crawlergoddess.service;

import cn.iamcrawler.crawler_common.domain.goddess.PgTestUser;
import cn.iamcrawler.crawler_common.domain.kindness.PgOrder;
import cn.iamcrawler.crawler_common.feign.KindnessFeign;
import cn.iamcrawler.crawlergoddess.mapper.PgTestUserMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuliang
 * @since 2019-04-19
 */
@Service
@Slf4j
public class PgTestUserService extends ServiceImpl<PgTestUserMapper, PgTestUser> {

    @Autowired
    private KindnessFeign kindnessFeign;

    @Autowired
    private PgTestUserMapper mapper;

    public void testLcn(){

        PgTestUser testUser = new PgTestUser();
        testUser.setAge(1);
        testUser.setName("liuliang");
        testUser.setRemark("要买东西");
        testUser.setSalary("10");
        this.save(testUser);

        PgOrder order = new PgOrder();
        order.setAmount("10");
        order.setItemName("小视频");

        kindnessFeign.insertOne(order);


        log.info("插入完毕...事务提交！");


    }


}
