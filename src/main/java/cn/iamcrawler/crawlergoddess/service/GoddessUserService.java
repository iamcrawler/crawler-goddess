package cn.iamcrawler.crawlergoddess.service;

import cn.iamcrawler.crawler_common.domain.goddess.GoddessUser;
import cn.iamcrawler.crawler_common.domain.goddess.MallAgent;
import cn.iamcrawler.crawlergoddess.interceptor.SayThingsInterceptor;
import cn.iamcrawler.crawlergoddess.mapper.GoddessUserMapper;
import cn.iamcrawler.crawlergoddess.mapper.MallAgentMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuliang on 2018/11/9.
 */
@Service
@Slf4j
public class GoddessUserService extends ServiceImpl<GoddessUserMapper, GoddessUser> {


    @Autowired
    private GoddessUserMapper userMapper ;

    @Autowired
    private MallAgentMapper agentMapper;

    //测试事务的几种特性

    public void test(){
        System.out.println("123");
    }



    //1：A方法不加事务注解,B方法加事务注解,AB在同一个类里面
    @Transactional
    public void A(){
        GoddessUser a =
                GoddessUser.builder()
                        .code("A_CODE")
                        .userName("A")
                        .password("123456")
                        .phone("18855554444")
                        .mail("")
                        .build();
        userMapper.insert(a);

    }


    public void B(){
        GoddessUser b1 =
                GoddessUser.builder()
                        .code("B1_CODE")
                        .userName("B1")
                        .password("123456")
                        .phone("12345678910")
                        .mail("")
                        .build();
        userMapper.insert(b1);


        throw new RuntimeException("123");

//
//        GoddessUser b2 =
//                GoddessUser.builder()
//                        .code("B2_CODE")
//                        .userName("B2")
//                        .password("123456")
//                        .phone("12345678910")
//                        .mail("")
//                        .build();
//        userMapper.insert(b2);
    }

    @Transactional
    public  void C() {

       this.A();
       this.B();
    }




    public List<MallAgent> getCricleAgent(){

        List<MallAgent> mallAgents = agentMapper.selectList(new EntityWrapper<MallAgent>());

        log.info("mallAgents:" ,mallAgents.size());

        mallAgents.stream().forEach(
                agent->{
                    //获取这个人的关系链
                    if(getRelation(agent,agent, new ArrayList<>())){
                        log.info("这个代理的关系链有环！！"+agent.getId());
                        throw new RuntimeException("");
                    }
                });
        log.info("执行完毕！");


        return null;
    }





    //根据id获取关系链
    public Boolean getRelation(MallAgent first,MallAgent node,List<MallAgent> fathers){

        String parentId = node.getParentId();
        if (!StringUtils.isEmpty(parentId) && !"0".equals(parentId) && !"1087665756942430211".equals(parentId)) {
            //有父节点，找出父节点并继续
            MallAgent father = new MallAgent();
            father = agentMapper.selectById(parentId);
            fathers.add(father);
            if(fathers.contains(first)){
                return true;
            }
            getRelation(first,father,fathers);


        }
        return false;
    }



}
