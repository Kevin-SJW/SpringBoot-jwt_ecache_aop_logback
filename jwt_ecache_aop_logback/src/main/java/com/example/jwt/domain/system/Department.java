package com.example.jwt.domain.system;

import com.example.jwt.domain.AbstractAuditingEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;




@Setter
@Getter
@Entity
@Table(name = "SYS_DEPT")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "部门")
public class Department<T> extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "dept_seq")
    @SequenceGenerator(name = "dept_seq", sequenceName = "seq_dept", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    @ApiModelProperty(value = "部门id")
    private Long id;

    @ApiModelProperty(value = "父部门ID")
    private Long parent_id;

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "部门ID")
    private String deptKey;

    @ApiModelProperty(value = "部门排序")
    private String deptSort;

    @ApiModelProperty(value = "负责人")
    private String leader;

    @ApiModelProperty(value = "联系电话")
    private String mobile;

    @ApiModelProperty(value = "电子邮件")
    private String email;

    @Column(name = "ENABLED")
    @NotNull
    @ApiModelProperty(value = "状态")
    private Boolean enabled;

    @ApiModelProperty(value = "删除标志")
    private String idDeleted;

    @ApiModelProperty(value = "备注")
    private String remarks;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id",referencedColumnName="id")
    @OrderBy("dept_sort asc")
    private List<Department> children = new ArrayList<>();

    public List<Department> getChildren() {
        return children;
    }

    public void setChildren(List<Department> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
