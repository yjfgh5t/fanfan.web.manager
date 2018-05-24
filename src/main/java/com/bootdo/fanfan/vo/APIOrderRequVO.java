package com.bootdo.fanfan.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class APIOrderRequVO {
    private Integer id;
    //用户id
    private Integer userId;
    //订单号
    private String orderNum;
    //订单状态：101：创建订单  102：待支付 103：已支付 104:超时未支付 105：确认收单 106：异常订单反馈 201：确认收单 202:取消收单-退款 203:开始派单
    private Integer orderState;
    //订单状态文本
    private String orderStateText;
    //订单总额
    private BigDecimal orderTotal;
    //支付总额
    private BigDecimal orderPay;
    //订单优惠总额
    private BigDecimal orderDiscountTotal;
    //订单支付类型  1:支付宝  2：微信 3：现金
    private Integer orderPayType;
    //用餐人数
    private String orderMealsNum;
    //描述
    private String orderRemark;
    //订单发票信息
    private String orderInvoice;
    //商户id
    private Integer customerId;
    //收货人信息
    private APIOrderReceiverVO receiver;
    //订单详情
    private List<APIOrderDetail> detailList;
    //创建时间
    private Date createTime;
    //支付宝预付单Str
    private String alipayOrderStr;
    //详情图片
    private String mainImg;
    //最后付款剩余秒数
    private Long lastPayTime;
}
