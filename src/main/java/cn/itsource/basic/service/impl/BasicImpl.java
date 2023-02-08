package cn.itsource.basic.service.impl;

import cn.itsource.basic.domain.BasicDomain;
import cn.itsource.basic.query.BasicQuery;
import cn.itsource.basic.service.IBasicService;
import cn.itsource.basic.uitl.PageList;
import cn.itsource.org.domain.Department;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

/**
 * @author Hello
 * @date 2023/2/7 19:42
 * 描述：
 */
public class BasicImpl<T extends BasicDomain, Q extends BasicQuery> implements IBasicService<T, Q> {

    @Autowired
    private Mapper<T> mapper;


    @Override
    public PageList<T> findPage(Q q) {

        // 分页接口
        PageHelper.startPage(q.getPage(), q.getSize());
        List<T> selectAll = mapper.selectAll();
        PageInfo<T> pageInfo = new PageInfo<>(selectAll);

        return new PageList<T>(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public T findOne(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> findAll() {
        return mapper.selectAll();
    }


    @Override
    public void save(T t) {
        if (t.getId() == null) {
            mapper.insertSelective(t);
        }else {
            mapper.updateByPrimaryKeySelective(t);
        }
    }



    @Override
    public void delete(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteMuch(Long[] ids) {
        List<Long> list = Arrays.asList(ids);
        Example example = new Example(Department.class);
        example.and().andIn("id", list);
        mapper.deleteByExample(example);
    }
}
