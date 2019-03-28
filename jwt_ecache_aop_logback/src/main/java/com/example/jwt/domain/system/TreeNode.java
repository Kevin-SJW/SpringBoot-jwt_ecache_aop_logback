package com.example.jwt.domain.system;

import com.example.jwt.domain.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@ToString
@Setter
@Getter
@Entity
@Table(name = "TREE_NODE")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "树形节点")
public class TreeNode extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "tree_node_seq")
    @SequenceGenerator(name = "tree_node_seq", sequenceName = "seq_tree_node", allocationSize = 1)
    @Column(name = "id", nullable = false)
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "树key")
    private Long treeKey;

    @ApiModelProperty(value = "姓名")
    private String name;

    @NotNull
    @ApiModelProperty(value = "父节点ID")
    private Long parent_id;

    @ApiModelProperty(value = "年龄")
    private Long age;

    @ApiModelProperty(value = "等级")
    private Long level;

    @ApiModelProperty(value = "可扩展")
    private boolean expand;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    @OrderBy("tree_key asc")
    private List<TreeNode> children = new ArrayList<>();

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }



    public boolean getExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }


}
