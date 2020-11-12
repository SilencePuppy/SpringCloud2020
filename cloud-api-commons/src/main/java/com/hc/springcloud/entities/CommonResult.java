package com.hc.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 李晓冰
 * @date 2020年11月01日
 */
@Data
@AllArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
      this(code,message,null);
    }

}
