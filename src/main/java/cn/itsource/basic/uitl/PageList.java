package cn.itsource.basic.uitl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hello
 * @date 2023/2/5 10:52
 * 描述：
 *      简化pageInfo, 提取核心代码
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageList<T> {
    /**
     * total 总条数
     * list 全部数据
     */
    private Long total = 0L;
    private List<T> list = new ArrayList<>();

}
