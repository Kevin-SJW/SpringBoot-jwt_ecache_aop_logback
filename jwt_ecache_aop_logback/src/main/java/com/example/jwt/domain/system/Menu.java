package com.example.jwt.domain.system;

import com.example.jwt.domain.AbstractAuditingEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;



@Setter
@Getter
@Entity
@Table(name = "SYS_MENU")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "菜单")
public class Menu extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_seq")
    @SequenceGenerator(name = "menu_seq", sequenceName = "seq_menu", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    @ApiModelProperty(value = "菜单id")
    private Long id;

    @ApiModelProperty(value = "父菜单id")
    private Long parentId;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单Kye")
    private String menuKey;

    @ApiModelProperty(value = "菜单排序")
    private String menuSort;

    @ApiModelProperty(value = "菜单类型")
    private String menuType;

    @Column(name = "ENABLED")
    @NotNull
    @ApiModelProperty(value = "状态")
    private Boolean enabled;

    @ApiModelProperty(value = "删除标志")
    private String isDeleted;

    @ApiModelProperty(value = "备注")
    private String remarks;

}
