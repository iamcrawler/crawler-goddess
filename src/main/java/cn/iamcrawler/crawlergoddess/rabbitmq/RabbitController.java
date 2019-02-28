package cn.iamcrawler.crawlergoddess.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuliang on 2019/1/23.
 */
@RestController
@RequestMapping("/api/rabbit")
public class RabbitController {


    @Autowired
    private Sender sender;

    /***
     * 测试direct模式
     * @return
     */
    @GetMapping("/direct")
    public ResponseEntity direct(){
        sender.sendDirectQueue();
        return ResponseEntity.ok(true);
    }


}
