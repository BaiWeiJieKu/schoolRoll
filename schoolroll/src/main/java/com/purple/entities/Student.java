package com.purple.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Student {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    // 所属学校
    @OneToOne (targetEntity = User.class, fetch = FetchType.EAGER)
    private User user;
    // 学生姓名
    private String name;
    // 身份证号
    private String card;
    // 性别
    private String gender;
    // 是否毕业 0 未毕业 1已经毕业
    private Integer isState;
    // 操作说明  0注册， 1换专业， 2退学， 3毕业  4休学
    private Integer operator; 
    // 毕业时间
    private Date stateTime;
    // 入学时间
    private Date entranceTime;
    // 专业
    @OneToOne (targetEntity = Speciality.class, fetch = FetchType.EAGER)
    private Speciality speciality;
    @OneToOne (targetEntity = Speciality.class)
    private Speciality newSpeciality;
    // 是否可操作0 不可以操作   1可以操作
    private int operatorState;
    // 0审核中 1市审核通过 -1市审核未通过 2省审核通过 -2省审核不通过
    private Integer pass;
    // 创建时间
    private Date createTime;


}
