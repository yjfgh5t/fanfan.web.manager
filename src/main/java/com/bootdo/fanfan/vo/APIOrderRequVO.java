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
    //订单类型 1：堂吃 2：打包 3：外卖
    private Integer orderType;
    //订单类型文本
    private String orderTypeText;
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
    //订单支付类型文本
    private String orderPayTypeText;
    //用餐人数
    private String orderMealsNum;
    //商户描述（退款描述）
    private String orderCustomerRemark;
    //描述
    private String orderRemark;
    //订单发票信息
    private String orderInvoice;
    //订单商品总数
    private Integer orderCommodityTotal;
    //商户id
    private Integer customerId;
    //收货人信息
    private APIOrderReceiverVO receiver;
    //订单详情
    private List<APIOrderDetailVO> detailList;
    //创建时间
    private Date createTime;
    //支付宝预付单Str
    private String alipayOrderStr;
    //详情图片
    private String mainImg;
    //最后付款剩余秒数
    private Long lastPayTime;
    //桌号
    private String orderDeskNum;
    //当日串号
    private String  orderDateNum;
    //下单时间
    private Date orderTime;
}
