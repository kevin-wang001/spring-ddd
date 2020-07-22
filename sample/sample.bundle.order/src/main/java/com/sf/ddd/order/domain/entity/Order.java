package com.sf.ddd.order.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @Author: 01387147
 * @Date:2019/12/2
 */

@Data
@TableName("ti_order")
public class Order {

    @TableId(value = "id", type = IdType.AUTO)
    private Long   id;

    //@ApiModelProperty(value = "订单名称")
    private String name;

    //@ApiModelProperty(value = "订单金额")
    private Float  fee;

    //@ApiModelProperty(value = "订单描述")
    private String desc;
}
