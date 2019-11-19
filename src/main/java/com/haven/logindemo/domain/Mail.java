package com.haven.logindemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author HavenTong
 * @date 2019/11/19 8:49 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail implements Serializable {


    private static final long serialVersionUID = 6321269928837707310L;

    private Integer id;

    private String email;
    private String checkCode;
}
