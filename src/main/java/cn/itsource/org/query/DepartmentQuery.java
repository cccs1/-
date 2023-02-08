package cn.itsource.org.query;

import cn.itsource.basic.query.BasicQuery;
import lombok.Data;

/**
 * @author Hello
 * @date 2023/2/4 19:35
 * 描述：
 */
@Data
public class DepartmentQuery extends BasicQuery {



    private Long managerId;
    private Long parentId;

}
