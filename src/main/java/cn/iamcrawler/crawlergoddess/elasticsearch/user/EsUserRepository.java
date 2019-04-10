package cn.iamcrawler.crawlergoddess.elasticsearch.user;

import cn.iamcrawler.crawler_common.domain.goddess.GoddessUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * Created by liuliang on 2019/4/8.
 */
@Component
public interface EsUserRepository extends ElasticsearchRepository<GoddessUser, Long> {
}
