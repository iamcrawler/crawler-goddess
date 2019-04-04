package cn.iamcrawler.crawlergoddess.service;

import cn.iamcrawler.crawler_common.domain.goddess.GoddessRole;
import cn.iamcrawler.crawler_common.domain.goddess.GoddessUser;
import cn.iamcrawler.crawlergoddess.interceptor.SayThingsInterceptor;
import cn.iamcrawler.crawlergoddess.mapper.GoddessRoleMapper;
import cn.iamcrawler.crawlergoddess.mapper.GoddessUserMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liuliang on 2018/11/9.
 */
@Service
@Slf4j
public class GoddessRoleService extends ServiceImpl<GoddessRoleMapper, GoddessRole> {

    private GoddessUserService userService;
    private GoddessUserMapper userMapper ;


    public void D(){
        userService.A();
        E();
    }

    public void E(){
        GoddessUser a =
                GoddessUser.builder()
                        .code("E_CODE")
                        .userName("E")
                        .password("123456")
                        .phone("18855554444")
                        .mail("")
                        .build();
        userMapper.insert(a);
        throw new RuntimeException("");
    }








    public static void main(String[] args){

        //测试数据库的传播性
        GoddessUserService userService = new GoddessUserService();

        userService.A();


//        测试动态代理
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(GoddessUserService.class);//继承被代理类
        enhancer.setCallback(new SayThingsInterceptor());//设置回调
        GoddessUserService service = (GoddessUserService)enhancer.create();//生成代理对象
//        System.out.println(service);
        service.test();//调用代理类中的方法时，会被我们实现的方法拦截器拦截

        String s = GoddessRoleService.MD5Encrypt("mft990412");//366600c73401cfee683bd3a190a2ace8
        System.out.println(s+"  111");

        //测试算法执行时间
        GoddessRoleService goddessRoleService = new GoddessRoleService();
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        integers.add(7);
        integers.add(8);
        integers.add(9);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        integers.add(7);
        integers.add(8);
        integers.add(9);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        integers.add(7);
        integers.add(8);
        integers.add(9);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        integers.add(7);
        integers.add(8);
        integers.add(9);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        integers.add(7);
        integers.add(8);
        integers.add(9);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        integers.add(7);
        integers.add(8);
        integers.add(9);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        integers.add(7);
        integers.add(8);
        integers.add(9);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        integers.add(7);
        integers.add(8);
        integers.add(9);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        integers.add(7);
        integers.add(8);
        integers.add(9);
        goddessRoleService.getIndex(integers,10);
    }



    public static String MD5(String str) {
        try {
            byte[] bs = str.getBytes("UTF-8");
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(bs);
            String hex = (new BigInteger(1, digest.digest())).toString(16);
            return (new String(new char[32 - hex.length()])).replace("\u0000", "0") + hex;
        } catch (Exception var4) {
//            log.info("MD5加密失败：" + var4);
            return null;
        }
    }



    //给定集合，最快方式找到两个数相加=指定结果
    public List<List<Integer>> getIndex(List<Integer> numbers, Integer target){
        ArrayList<List<Integer>> list = new ArrayList<>();
        long l1 = System.currentTimeMillis();
        for(int i=0;i<numbers.size();i++){
            for(int j=0;j<numbers.size();j++){
                if(i!=j){
                    int t = numbers.get(i) + numbers.get(j);
                    if(target.equals(t)){
                        ArrayList<Integer> arrayList = new ArrayList<>();
                        arrayList.add(numbers.get(i));
                        arrayList.add(numbers.get(j));
                        list.add(arrayList);
                    }
                }
            }
        }
        long l2 = System.currentTimeMillis();
        log.info("花费时间："+(l2-l1) +" ms");
        log.info("list:{}",list);
        return list;
    }



    public void getIndex2(List<Integer> numbers, Integer target){

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<numbers.size();i++){

//            map.put()


        }


    }



    public static String MD5Encrypt(String str) {
        String resultString = MD5(str + MD5("1f32a83588848d341c89f102dde11d4f"));
        return resultString;
    }

}
