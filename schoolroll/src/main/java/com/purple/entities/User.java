package com.purple.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class User implements Cloneable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "编号不允许为空")
    private String no;
    @NotEmpty(message = "名称不允许为空")
    private String name;
    @NotEmpty(message = "密码不允许为空")
    private String password;
    private Date createTime;
    
    private int type;
    @NotNull(message = "所属类型不允许为空")
    private Integer parentId;
    @OneToMany (targetEntity = Speciality.class, fetch = FetchType.EAGER)
    private Set<Speciality> speciality;

}
