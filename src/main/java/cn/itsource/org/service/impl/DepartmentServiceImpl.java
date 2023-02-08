package cn.itsource.org.service.impl;

import cn.itsource.basic.service.impl.BasicImpl;
import cn.itsource.basic.uitl.PageList;
import cn.itsource.basic.uitl.TreeUtil;
import cn.itsource.org.domain.Department;
import cn.itsource.org.mapper.DepartmentMapper;
import cn.itsource.org.query.DepartmentQuery;
import cn.itsource.org.service.IDepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

/**
 * @author Hello
 * @date 2023/2/4 15:02
 * 描述：
 */
@Service
public class DepartmentServiceImpl extends BasicImpl<Department, DepartmentQuery> implements IDepartmentService {
    @Autowired
    private DepartmentMapper mapper;

    @Override
    public List<Department> upDeptOptions() {
        return mapper.upDeptOptions();
    }

    @Override
    public List<Department> getUpUp() {
        List<Department> list = mapper.selectAll();
        return TreeUtil.getTree(list);
    }

    @Override
    public PageList<Department> findPage(DepartmentQuery query) {

        // 分页接口
        PageHelper.startPage(query.getPage(), query.getSize());
        List<Department> selectAll = mapper.findPage(query);
        PageInfo<Department> pageInfo = new PageInfo<>(selectAll);

        return new PageList<Department>(pageInfo.getTotal(), pageInfo.getList());
    }



    @Override
    public void saveOrUpdate(Department department) {
        if (department.getId() == null) {
            mapper.insertSelective(department);
            extracted(department);
        }else {
            extracted(department);

            mapper.updateByPrimaryKey(department);
            // 根据主键修改
            mapper.updateByPrimaryKeySelective(department);
        }
    }

    private void extracted(Department department) {
        StringBuilder sb = new StringBuilder("");
        if (department.getParentIds() != null && department.getParentIds().length > 0) {
            // 获取上级,
            Long id = department.getParentIds()[department.getParentIds().length -1];
            // 设置上级id
            department.setParentId(id);
            Long[] longs = department.getParentIds();
            for (Long parentId : longs) {
                sb.append("/").append(parentId);
                System.out.println("进入循环");
            }
            sb.append("/").append(department.getId());
            department.setDirPath(sb.toString());
        }else {
            sb.append("/").append(department.getId());
            department.setDirPath(sb.toString());
        }

        mapper.updateByPrimaryKey(department);

    }




}
