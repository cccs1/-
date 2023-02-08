package cn.itsource.org.mapper;

import cn.itsource.org.domain.Department;
import cn.itsource.org.query.DepartmentQuery;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Hello
 * @date 2023/2/4 14:57
 * 描述：
 */
public interface DepartmentMapper extends Mapper<Department> {

    /**
     * 分页联表查询
     * @param departmentQuery 分页细腻些
     * @return 返回表数据和分页信息
     */
    List<Department> findPage(DepartmentQuery departmentQuery);

   /**
     * 分页联表查询
     * @return 返回表数据和分页信息
     */
    List<Department> upDeptOptions();


    /**
     * 级联查询
     * @return 三层数据
     */
    List<Department> getUpUp();
}
