package cn.itsource.org.query;

import cn.itsource.basic.query.BasicQuery;
import lombok.Data;

/**
 * @author Hello
 * @date 2023/2/4 19:35
 * 描述：
 */
@Data
public class EmployeeQuery extends BasicQuery {

    private Integer page = 1;
    private Integer size = 5;
    private String keyword;
    private Long[] ids;

}
