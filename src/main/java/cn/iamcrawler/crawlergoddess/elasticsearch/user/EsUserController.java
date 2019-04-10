package cn.iamcrawler.crawlergoddess.elasticsearch.user;

import cn.iamcrawler.crawler_common.domain.goddess.GoddessUser;
import cn.iamcrawler.crawlergoddess.mapper.GoddessUserMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuliang on 2019/4/8.
 */
@RestController
@RequestMapping("/api/es")
@Slf4j
public class EsUserController {


    @Autowired
    private GoddessUserMapper userMapper;

    @Autowired EsUserRepository esUserRepository;

    @PostMapping("/insert/all")
    public ResponseEntity loadAllUser(){
        List<GoddessUser> users = userMapper.selectList(new EntityWrapper<GoddessUser>());
        log.info("users:{}",users);
        users.stream().forEach(goddessUser -> {
            esUserRepository.save(goddessUser);
        });
        return ResponseEntity.ok(true);
    }



    @GetMapping("/search")
    public ResponseEntity getByName(String name, Pageable pageable){

        //按name进行搜索
        MatchQueryBuilder builder = QueryBuilders.matchQuery("userName", name);

        //如果实体和数据的名称对应就会自动封装，pageable分页参数
        Page<GoddessUser> users = esUserRepository.search(builder, pageable);

        //迭代器转list
        ArrayList<GoddessUser> list = Lists.newArrayList(users);
        return ResponseEntity.ok(list);
    }







}
