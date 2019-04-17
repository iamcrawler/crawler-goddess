package cn.iamcrawler.crawlergoddess.postgres.service.impl;

import cn.iamcrawler.crawlergoddess.postgres.entity.PgUser;
import cn.iamcrawler.crawlergoddess.postgres.mapper.PgUserMapper;
import cn.iamcrawler.crawlergoddess.postgres.service.IPgUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuliang
 * @since 2019-04-17
 */
@Service
public class PgUserServiceImpl extends ServiceImpl<PgUserMapper, PgUser> implements IPgUserService {

}
