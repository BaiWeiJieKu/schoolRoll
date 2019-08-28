package com.purple.entities;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StudentParam {

    private Integer id;
    // 所属学校
    private Integer user;
    // 学生姓名
    private String name;
    // 身份证号
    private String card;
    // 性别
    private String gender;
    // 休学状态
    private boolean hughState;
    // 退学状态
    private boolean quitState;
    // 是否毕业
    private boolean state;
    // 毕业时间
    private Date stateTime;

    // 专业
    protected Integer speciality;
    // 是否在转专业
    private boolean operatorState;
    // 创建时间
    private Date createTime;

}
