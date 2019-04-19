package cn.iamcrawler.crawlergoddess.pg.service.impl;

import cn.iamcrawler.crawlergoddess.pg.entity.TestUser;
import cn.iamcrawler.crawlergoddess.pg.mapper.TestUserMapper;
import cn.iamcrawler.crawlergoddess.pg.service.ITestUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class TestUserServiceImpl extends ServiceImpl<TestUserMapper, TestUser> implements ITestUserService {

}
