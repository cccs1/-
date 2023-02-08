package cn.itsource.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 该类为树形结构的泛型类,所有使用TreeUtil的List集合中泛型都需要继承该类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseTreeNode<T> extends BasicDomain{
    /**
     * 子Id
     */
    private Long id;
    /**
     * 父ID ,若parentId等于0 或者null,表示为顶级
     */
    private Long parentId;
    /**
     * 子节点
     */
    private List<T> children;

}
