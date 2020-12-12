package tech.jiangchen.ssproxydemo.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {
    private Long orderId;
    private Integer productId;
    private Integer userId;
    private BigDecimal productPrice;
    private String address;
    private Integer isDelete;
    private Date createTime;
    private Integer createBy;
    private Date updateTime;
    private Integer updateBy;
}
