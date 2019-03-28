package com.example.jwt.domain.system;

import com.example.jwt.domain.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;




@Entity
@Table(name = "SYS_USER")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户")
public class User extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "seq_user", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    @ApiModelProperty(value = "用户id")
    private Long id;

    @Column(name = "USERNAME", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    @ApiModelProperty(value = "用户名称")
    private String username;

    @JsonIgnore
    @Column(name = "PASSWORD", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    @ApiModelProperty(value = "密码")
    private String password;

    @Column(name = "DISPLAY_NAME", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    @ApiModelProperty(value = "显示名称")
    private String displayName;

    @Column(name = "FIRST_NAME", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    @ApiModelProperty(value = "名")
    private String firstName;

    @Column(name = "LAST_NAME", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    @ApiModelProperty(value = "姓")
    private String lastName;

    @Column(name = "EMAIL", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    @ApiModelProperty(value = "电子邮件")
    private String email;

    @Column(name = "GENDER", length = 50)
    @NotNull
    @Size(min = 1, max = 50)
    @ApiModelProperty(value = "性别")
    private String gender;

    @Column(name = "PHONE", length = 50)
    @Size(min = 4, max = 50)
    @ApiModelProperty(value = "电话")
    private String phone;

    @Column(name = "MOBILE", length = 50)
    @Size(min = 4, max = 50)
    @ApiModelProperty(value = "电话")
    private String mobile;

    @Column(name = "AVATAR", length = 150)
    @Size(min = 4, max = 150)
    @ApiModelProperty(value = " ")
    private String avatar;

    @Column(name = "LANG", length = 50)
    @ApiModelProperty(value = "语言key")
    private String langKey;

    @Column(name = "ENABLED")
    @NotNull
    @ApiModelProperty(value = "状态")
    private Boolean enabled;

    @Column(name = "IS_DELETED")
    @NotNull
    @ApiModelProperty(value = "删除标志")
    private Boolean isDeleted;

    @Column(name = "LAST_PASSWORD_RESET_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @ApiModelProperty(value = "最后密码的重置日期")
    private Date lastPasswordResetDate;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "DEPT_ID", referencedColumnName = "ID")
    @JsonIgnoreProperties("users")
    @JsonBackReference(value = "department")
    private Department department;

    @ManyToMany()
    @JoinTable(name = "USER_POSITION", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "POSITION_ID", referencedColumnName = "ID"))
    @JsonIgnoreProperties("users")
    @JsonBackReference(value = "positions")
    private List<Position> positions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_AUTHORITY", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID"))
    @JsonIgnoreProperties("users")
    @JsonBackReference(value = "authorities")
    private List<Authority> authorities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public List<Authority> getRoles() {
        return authorities;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = new LinkedHashSet<>();
        if (authorities != null) {
            for (Authority authority : getRoles()) {
                grantedAuthorities.addAll(authority.getAuthorities());
            }
        }
        return grantedAuthorities;
    }


    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", displayName='" + displayName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", avatar='" + avatar + '\'' +
                ", langKey='" + langKey + '\'' +
                ", enabled=" + enabled +
                ", isDeleted=" + isDeleted +
                ", lastPasswordResetDate=" + lastPasswordResetDate +
                ", department=" + department +
                ", positions=" + positions +
                ", authorities=" + authorities +
                '}';
    }

    public Set<String> col2LongAuthorityList(List<Authority> roles) {
        Set<String> set = new HashSet<>();
        for (Authority authority : roles) {
            set.add(authority.getRoleKey());
        }

        return set;
    }

    public Set<String> col2LongPositionList(List<Position> positions) {
        Set<String> set = new HashSet<>();
        for (Position position : positions) {
            set.add(position.getPostKey());
        }

        return set;
    }
}
