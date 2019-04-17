package cn.iamcrawler.crawlergoddess.postgres.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuliang
 * @since 2019-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PgUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;


}
