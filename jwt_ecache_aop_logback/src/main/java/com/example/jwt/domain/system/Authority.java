package com.example.jwt.domain.system;

import com.example.jwt.domain.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;



@Setter
@Getter
@Entity
@Table(name = "SYS_ROLE")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "角色")
public class Authority extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name = "role_seq", sequenceName = "seq_role", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    @ApiModelProperty(value = "角色id")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色Key")
    private String roleKey;

    @ApiModelProperty(value = "角色排序")
    private String roleSort;

    @Column(name = "ENABLED")
    @NotNull
    @ApiModelProperty(value = "状态")
    private Boolean enabled;

    @ApiModelProperty(value = "删除标志")
    private String isDeleted;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ROLE_Menu", joinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "Menu_ID", referencedColumnName = "ID"))
    @JsonIgnoreProperties("authorities")
    @JsonBackReference(value = "menus")
    private List<Menu> menus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(String roleSort) {
        this.roleSort = roleSort;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = new LinkedHashSet<>();
        for (Menu menu : menus) {
            grantedAuthorities.add(new SimpleGrantedAuthority(menu.getMenuKey()));
        }

        return grantedAuthorities;
    }
}
