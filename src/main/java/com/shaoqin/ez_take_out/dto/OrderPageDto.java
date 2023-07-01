package com.shaoqin.ez_take_out.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * ClassName: OrderPageDto
 * Package: com.shaoqin.ez_take_out.dto
 * Description:
 * Author Shaoqin
 * Create 6/30/23 12:17 PM
 * Version 1.0
 */
@Data
public class OrderPageDto extends PageDto{

    private String number;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

}
