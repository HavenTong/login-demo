package com.haven.logindemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author HavenTong
 * @date 2019/11/19 8:19 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 8182079122506202460L;

    private Integer id;
    private String username;
    private String email;
    private Boolean isRegistered;

}
