package com.example.jwt.domain.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "SYS_LOG")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "登录日志")
public class SysLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "syslog_seq")
    @SequenceGenerator(name = "syslog_seq", sequenceName = "seq_syslog", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "操作")
    private String operation;

    @ApiModelProperty(value = "方法名")
    private String method;

    @ApiModelProperty(value = "方法参数")
    private String params;

    @ApiModelProperty(value = "用户ip")
    private String ip;

    @ApiModelProperty(value = "操作时间")
    private Date createDate;
    //创建getter和setter方法
}


