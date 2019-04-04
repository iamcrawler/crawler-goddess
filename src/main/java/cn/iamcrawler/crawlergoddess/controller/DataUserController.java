package cn.iamcrawler.crawlergoddess.controller;

import cn.iamcrawler.crawlergoddess.service.DataUserService;
import cn.iamcrawler.crawlergoddess.util.MybatisPageUtil;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuliang on 2019/3/21.
 */
@RestController
@RequestMapping("/api/data/user")
public class DataUserController {

    @Autowired
    private DataUserService dataUserService;

    @GetMapping("/qurey/all")
    public ResponseEntity query(){
//        PageMybatisPageUtil.getPage(0, 20);
        dataUserService.queryDataUsers();
        return null;
    }

    @GetMapping("/qurey/page")
    public ResponseEntity getPage(Pageable pageable, @RequestParam(value = "flag",required = false,defaultValue = "false") Boolean flag){
        Page page = MybatisPageUtil.getPage(pageable);
        return ResponseEntity.ok(dataUserService.getDataUsersPage(page,flag));
    }



}
