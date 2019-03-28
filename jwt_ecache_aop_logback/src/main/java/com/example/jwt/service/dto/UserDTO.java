package com.example.jwt.service.dto;

import com.example.jwt.constants.Constants;
import com.example.jwt.domain.system.User;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;

import java.util.Set;


public class UserDTO {

    private Long id;

    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 4, max = 50)
    private String username;

    @Size(min = 4, max = 50)
    private String password;

    @Size(min = 4, max = 50)
    private String displayName;

    @Size(min = 4, max = 50)
    private String firstName;

    @Size(min = 4, max = 50)
    private String lastName;

    @Size(min = 4, max = 50)
    private String email;

    @Size(min = 1, max = 50)
    private String gender;

    @Size(min = 4, max = 50)
    private String phone;

    @Size(min = 4, max = 50)
    private String mobile;

    @Size(min = 4, max = 150)
    private String avatar;

    private String langKey;

    private Boolean enabled;

    private String department;

    private Set<String> positions;

    private Set<String> authorities;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this(user.getId(), user.getDisplayName(), user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getGender(), user.getPhone(), user.getMobile(),
                user.getAvatar(), user.getLangKey(), user.getEnabled(), user.getDepartment().getDeptKey(),
                user.col2LongPositionList(user.getPositions()), user.col2LongAuthorityList(user.getRoles()),
                user.getCreatedBy(), user.getCreatedDate(), user.getLastModifiedBy(), user.getLastModifiedDate()
        );
    }

    public UserDTO(Long id, @Size(min = 4, max = 50) String displayName, @Pattern(regexp = Constants.LOGIN_REGEX) @Size(min = 4, max = 50) String username, @Size(min = 4, max = 50) String password, @Size(min = 4, max = 50) String firstName, @Size(min = 4, max = 50) String lastName, @Size(min = 4, max = 50) String email, @Size(min = 1, max = 50) String gender, @Size(min = 4, max = 50) String phone, @Size(min = 4, max = 50) String mobile, @Size(min = 4, max = 150) String avatar, String langKey, Boolean enabled, String department, Set<String> positions, Set<String> authorities, String createdBy, Instant createdDate, String lastModifiedBy, Instant lastModifiedDate) {
        this.id = id;
        this.displayName = displayName;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.mobile = mobile;
        this.avatar = avatar;
        this.langKey = langKey;
        this.enabled = enabled;
        this.department = department;
        this.positions = positions;
        this.authorities = authorities;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Set<String> getPositions() {
        return positions;
    }

    public void setPositions(Set<String> positions) {
        this.positions = positions;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
