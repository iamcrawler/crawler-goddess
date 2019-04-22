package cn.iamcrawler.crawlergoddess.controller;

import cn.iamcrawler.crawlergoddess.service.GoddessUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import cn.iamcrawler.crawlergoddess.util.AliExpress;

/**
 * Created by liuliang on 2018/11/9.
 */
@RestController
@RequestMapping("/api/user")
public class GoddessUserController {

    @Autowired
    private GoddessUserService userService;


    @GetMapping("/test1")
    public void test1(){
        userService.A();
    }

    @GetMapping("/test2")
    public void test2(){
        userService.getCricleAgent();
    }

//    @GetMapping("/get/by/code")
//    public ResponseEntity getDetailByCode(@Param("code") String code){
////        AliExpressResult aliExpress = AliExpress.getAliExpress(code,false);
//        return ResponseEntity.ok(aliExpress);
//    }


}
