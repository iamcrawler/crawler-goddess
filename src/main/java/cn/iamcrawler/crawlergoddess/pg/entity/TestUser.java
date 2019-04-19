package cn.iamcrawler.crawlergoddess.pg.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.Version;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * @since 2019-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pg_test_user")
public class TestUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Integer age;

    private String remark;

    private String salary;

    private LocalDate birthday;

    private LocalDateTime createdDate;

    private LocalDateTime updateDate;

    @Version
    private Integer version;

    @TableLogic
    private Integer deleted;


}
