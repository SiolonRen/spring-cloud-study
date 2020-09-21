package com.pcocoon.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Description:[服务通讯类]</p>
 * Created on: 2020/9/19 13:30
 *
 * @author: <a href="mailto: Siolon_Ren@foxmail.com">Siolon_Ren</a>
 * version: 1.0
 * Copyright (c) 2019 timedu.com.cn
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sender {

    private int id;

    private String name;
}
