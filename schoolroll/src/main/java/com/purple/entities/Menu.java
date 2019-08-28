package com.purple.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Menu {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "菜单名不允许为空")
    private String name;
    @NotEmpty(message = "菜单链接不允许为空")
    private String href;
    @NotEmpty(message = "菜单图标不允许为空")
    private String icon;
    @NotEmpty(message = "菜单类型不允许为空")
    private int type;

}
