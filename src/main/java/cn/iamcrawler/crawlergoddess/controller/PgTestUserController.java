package cn.iamcrawler.crawlergoddess.controller;


import cn.iamcrawler.crawlergoddess.service.PgTestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuliang
 * @since 2019-04-19
 */
@RestController
@RequestMapping("/pg")
public class PgTestUserController {

    @Autowired
    private PgTestUserService userService;


    @GetMapping
    public void testLcn(){
        userService.testLcn();
    }

}
