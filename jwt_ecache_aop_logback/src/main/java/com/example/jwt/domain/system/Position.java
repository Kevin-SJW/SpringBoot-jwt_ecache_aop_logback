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
@Table(name = "SYS_POST")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "职位")
public class Position extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
    @SequenceGenerator(name = "post_seq", sequenceName = "seq_post", allocationSize = 1)
    @Column(name = "ID", nullable = false)

    @ApiModelProperty(value = "职位id")
    private Long id;

    @ApiModelProperty(value = "职位名称")
    private String postName;

    @ApiModelProperty(value = "职位key")
    private String postKey;

    @ApiModelProperty(value = "职位排序")
    private String postSort;

    @Column(name = "ENABLED")
    @NotNull
    @ApiModelProperty(value = "状态")
    private Boolean enabled;

    @ApiModelProperty(value = "删除标志")
    private String isDeleted;

}
