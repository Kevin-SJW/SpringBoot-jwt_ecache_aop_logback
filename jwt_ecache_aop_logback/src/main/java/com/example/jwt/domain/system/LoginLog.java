package com.example.jwt.domain.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "LOGIN_LOG")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "登录日志")
public class LoginLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "login_log_seq")
    @SequenceGenerator(name = "login_log_seq", sequenceName = "seq_login_log", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    @ApiModelProperty(value = "登录id")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "登录id")
    private String ip;

    @ApiModelProperty(value = "登录时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
