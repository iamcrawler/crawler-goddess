package cn.iamcrawler.crawlergoddess.config;

import com.codingapi.txlcn.tm.support.restapi.auth.sauth.SAuthHandleException;
import com.codingapi.txlcn.tm.support.restapi.auth.sauth.SAuthLogic;
import com.codingapi.txlcn.tm.support.restapi.auth.sauth.token.TokenStorage;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * Created by liuliang on 2019/4/19.
 */
@Component
public class CrawlerSAuthLogic  implements SAuthLogic {

    private TokenStorage tokenStorage;

    public CrawlerSAuthLogic(TokenStorage tokenStorage){
        this.tokenStorage = tokenStorage;

    }

    //这里可以指定哪些路径可以不用受lcn管控
    @Override
    public List<String> ignoreUrls() {
        return Collections.emptyList();
    }

    @Override
    public boolean isIgnored(HttpServletRequest request) throws SAuthHandleException {
        return true;
    }

    @Override
    public boolean verify(String token) {
        this.tokenStorage.exist(token);
        //这里不拦截任何
        return true;
    }
}
