package cn.iamcrawler.crawlergoddess.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by liuliang on 2019/4/10.
 */
@Data
public class AliExpressResult {

    private String reason;


    private String ico;

    private String phone;

    private Boolean success;

    private String nu;

    private List<ExpressDetail> data;

    private String exname;

    private String company;

    private String url;

    private String status;
}
