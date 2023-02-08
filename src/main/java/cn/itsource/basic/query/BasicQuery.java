package cn.itsource.basic.query;

import lombok.Data;

/**
 * @author Hello
 * @date 2023/2/7 19:39
 * 描述：
 */
@Data
public class BasicQuery {
    private Integer page = 1;
    private Integer size = 5;
    private String keyword;
    private Long[] ids;
}
