package com.leon.testspringbatch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class People implements Serializable {

    private Integer id;
    private String name;
    private Integer age;
    private String profession;
}
