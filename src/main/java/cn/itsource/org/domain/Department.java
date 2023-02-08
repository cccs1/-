package cn.itsource.org.domain;


import cn.itsource.basic.domain.BaseTreeNode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Hello
 *      部门表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_department")
@ApiModel(value = "cn.itsource.org.domain.Department", description = "部门表")
public class Department extends BaseTreeNode<Department> {
    /**
     * 表信息
     * id
     * sn   部门编号
     * name     部门名称
     * state    部门状态,
     * manager_id   部门管理员, 关联employee表id
     * parent_id    上级部门
     * dir_path     部门级联路径
     *  @ GeneratedValue(strategy=GenerationType.AUTO) 自动装配获取自增id
     */


    @ApiModelProperty(value = "部门编号")
    @NotEmpty(message = "部门编号不能为空")
    private String sn;
    @ApiModelProperty(value = "部门名称")
    @Size(min = 3, max = 8, message = "部门名称长度应在 3~8")
    private String name;

    @ApiModelProperty(value = "部门状态")
    private Long state;

    @Column(name = "manager_id")
    @ApiModelProperty(value = "部门管理员")
    private Long managerId;

    @Column(name = "parent_id")
    @ApiModelProperty(value = "上级部门")
    private Long parentId;
    @Column(name = "dir_path")
    @ApiModelProperty(value = "部门级联路径")
    private String dirPath;

    /**
     * 临时字段
     */
    @Transient
    private Employee manager;
    @Transient
    private Department parent;

    @Transient
    private Long[] parentIds;

}
