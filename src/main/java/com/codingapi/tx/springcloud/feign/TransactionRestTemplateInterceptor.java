package com.codingapi.tx.springcloud.feign;

/**
 * Created by liuliang on 2018/10/10.
 */

import com.codingapi.tx.aop.bean.TxTransactionLocal;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class TransactionRestTemplateInterceptor implements RequestInterceptor {

    private Logger logger = LoggerFactory.getLogger(TransactionRestTemplateInterceptor.class);

    public TransactionRestTemplateInterceptor() {
    }

    public void apply(RequestTemplate requestTemplate) {
        TxTransactionLocal txTransactionLocal = TxTransactionLocal.current();
        String groupId = txTransactionLocal == null ? null : txTransactionLocal.getGroupId();
        this.logger.info("LCN-SpringCloud TxGroup info -> groupId:" + groupId);
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes == null ? null : ((ServletRequestAttributes) requestAttributes).getRequest();
        Object attribute = request.getAttribute("OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE");
        String token = attribute == null ? null : attribute.toString();
        requestTemplate.header("Authorization", "Bearer " + token);
        if (txTransactionLocal != null) {
            requestTemplate.header("tx-group", new String[]{groupId});
        }

    }
}
