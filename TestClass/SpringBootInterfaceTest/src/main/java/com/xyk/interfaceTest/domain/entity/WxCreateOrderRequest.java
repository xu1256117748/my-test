package com.xyk.interfaceTest.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2021-11-11 16:42
 * @Description 调用微信创建订单接口时,不能携带除了接口文档声明的参数以外的参数,否则胡报错,此类专为调用微信创建订单接口使用
 */
@Data
public class WxCreateOrderRequest {
    /**
     * <pre>
     * 字段名：应用ID
     * 变量名：appid
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  由微信生成的应用ID，全局唯一。请求统一下单接口时请注意APPID的应用属性，例如公众号场景下，需使用应用属性为公众号的APPID
     *  示例值：wxd678efh567hg6787
     * </pre>
     */
    @JsonProperty(value = "appid")
    private String appid;
    /**
     * <pre>
     * 字段名：直连商户号
     * 变量名：mchid
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  直连商户的商户号，由微信支付生成并下发。
     *  示例值：1230000109
     * </pre>
     */
    @JsonProperty(value = "mchid")
    private String mchid;
    /**
     * <pre>
     * 字段名：商品描述
     * 变量名：description
     * 是否必填：是
     * 类型：string[1,127]
     * 描述：
     *  商品描述
     *  示例值：Image形象店-深圳腾大-QQ公仔
     * </pre>
     */
    @JsonProperty(value = "description")
    @NotBlank(message = "商品描述字段description值不能为null/空串")
    private String description;
    /**
     * <pre>
     * 字段名：商户订单号
     * 变量名：out_trade_no
     * 是否必填：是
     * 类型：string[6,32]
     * 描述：
     *  商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一
     *  示例值：1217752501201407033233368018
     * </pre>
     */
    @JsonProperty(value = "out_trade_no")
    @NotBlank(message = "商户订单号字段outTradeNo值不能为null/空串")
    private String outTradeNo;
    /**
     * <pre>
     * 字段名：交易结束时间
     * 变量名：out_trade_no
     * 是否必填：是
     * 类型：string[1,64]
     * 描述：
     *  订单失效时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。
     *  示例值：2018-06-08T10:34:56+08:00
     * </pre>
     */
    @JsonProperty(value = "time_expire")
    private String timeExpire;
    /**
     * <pre>
     * 字段名：附加数据
     * 变量名：attach
     * 是否必填：否
     * 类型：string[1,128]
     * 描述：
     *  附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用
     *  示例值：自定义数据
     * </pre>
     */
    @JsonProperty(value = "attach")
    private String attach;
    /**
     * <pre>
     * 字段名：通知地址
     * 变量名：notify_url
     * 是否必填：是
     * 描述：
     *  通知URL必须为直接可访问的URL，不允许携带查询串，要求必须为https地址。
     *  格式：URL
     *  示例值：https://www.weixin.qq.com/wxpay/pay.php
     * </pre>
     */
    @JsonProperty("notify_url")
    private String notifyUrl;
    /**
     * <pre>
     * 字段名：订单优惠标记
     * 变量名：goods_tag
     * 是否必填：否
     * 类型：string[1,256]
     * 描述：
     *  订单优惠标记
     *  示例值：WXG
     * </pre>
     */
    @JsonProperty(value = "goods_tag")
    private String goodsTag;
    /**
     * <pre>
     * 字段名：订单金额
     * 变量名：amount
     * 是否必填：是
     * 类型：object
     * 描述：
     *  订单金额信息
     * </pre>
     */
    @JsonProperty(value = "amount")
    private WxAmount amount;
    /**
     * <pre>
     * 字段名：支付者
     * 变量名：payer
     * 是否必填：是
     * 类型：object
     * 描述：
     *  支付者信息
     * </pre>
     */
    @JsonProperty(value = "payer")
    private WxCreateOrderRequest.Payer payer;
    /**
     * <pre>
     * 字段名：优惠功能
     * 变量名：detail
     * 是否必填：否
     * 类型：object
     * 描述：
     *  优惠功能
     * </pre>
     */
    @JsonProperty(value = "detail")
    private WxCreateOrderRequest.Discount detail;
    /**
     * <pre>
     * 字段名：场景信息
     * 变量名：scene_info
     * 是否必填：否
     * 类型：object
     * 描述：
     *  支付场景描述
     * </pre>
     */
    @JsonProperty(value = "scene_info")
    private WxCreateOrderRequest.SceneInfo sceneInfo;
    /**
     * <pre>
     * 字段名：结算信息
     * 变量名：settle_info
     * 是否必填：否
     * 类型：Object
     * 描述：结算信息
     * </pre>
     */
    @JsonProperty(value = "settle_info")
    private WxCreateOrderRequest.SettleInfo settleInfo;

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class Payer implements Serializable {
        private static final long serialVersionUID = -1L;
        /**
         * <pre>
         * 字段名：用户标识
         * 变量名：openid
         * 是否必填：是
         * 类型：string[1,128]
         * 描述：
         *  用户在直连商户appid下的唯一标识。
         *  示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
         * </pre>
         */
        @JsonProperty(value = "openid")
        private String openid;
    }

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class Discount implements Serializable {
        private static final long serialVersionUID = 1L;
        /**
         * <pre>
         * 字段名：订单原价
         * 变量名：cost_price
         * 是否必填：否
         * 类型：int
         * 描述：
         *  1、商户侧一张小票订单可能被分多次支付，订单原价用于记录整张小票的交易金额。
         *  2、当订单原价与支付金额不相等，则不享受优惠。
         *  3、该字段主要用于防止同一张小票分多次支付，以享受多次优惠的情况，正常支付订单不必上传此参数。
         *  示例值：608800
         * </pre>
         */
        @JsonProperty(value = "cost_price")
        private Integer costPrice;
        /**
         * <pre>
         * 字段名：商品小票ID
         * 变量名：invoice_id
         * 是否必填：否
         * 类型：string[1,32]
         * 描述：
         *  商品小票ID
         *  示例值：微信123
         * </pre>
         */
        @JsonProperty(value = "invoice_id")
        private String invoiceId;
        /**
         * <pre>
         * 字段名：单品列表
         * 变量名：goods_detail
         * 是否必填：否
         * 类型：array
         * 描述：
         *  单品列表信息
         *  条目个数限制：【1，6000】
         * </pre>
         */
        @JsonProperty(value = "goods_detail")
        private List<WxCreateOrderRequest.GoodsDetail> goodsDetails;
    }

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class GoodsDetail implements Serializable {
        private static final long serialVersionUID = -1L;
        /**
         * <pre>
         * 字段名：商户侧商品编码
         * 变量名：merchant_goods_id
         * 是否必填：是
         * 类型：string[1,32]
         * 描述：
         *  由半角的大小写字母、数字、中划线、下划线中的一种或几种组成。
         *  示例值：商品编码
         * </pre>
         */
        @JsonProperty(value = "merchant_goods_id")
        private String merchantGoodsId;
        /**
         * <pre>
         * 字段名：微信侧商品编码
         * 变量名：wechatpay_goods_id
         * 是否必填：否
         * 类型：string[1,32]
         * 描述：
         *  微信支付定义的统一商品编号（没有可不传）
         *  示例值：1001
         * </pre>
         */
        @JsonProperty(value = "wechatpay_goods_id")
        private String wechatpayGoodsId;
        /**
         * <pre>
         * 字段名：商品名称
         * 变量名：goods_name
         * 是否必填：否
         * 类型：string[1,256]
         * 描述：
         *  商品的实际名称
         *  示例值：iPhoneX 256G
         * </pre>
         */
        @JsonProperty(value = "goods_name")
        private String goodsName;
        /**
         * <pre>
         * 字段名：商品数量
         * 变量名：quantity
         * 是否必填：是
         * 类型：int
         * 描述：
         *  用户购买的数量
         *  示例值：1
         * </pre>
         */
        @JsonProperty(value = "quantity")
        private Integer quantity;
        /**
         * <pre>
         * 字段名：商品单价
         * 变量名：unit_price
         * 是否必填：是
         * 类型：int
         * 描述：
         *  商品单价，单位为分
         *  示例值：828800
         * </pre>
         */
        @JsonProperty(value = "unit_price")
        private Integer unitPrice;
    }

    @Data
    @NoArgsConstructor
    public static class SceneInfo implements Serializable {
        private static final long serialVersionUID = 1L;
        /**
         * <pre>
         * 字段名：用户终端IP
         * 变量名：payer_client_ip
         * 是否必填：是
         * 类型：string[1,45]
         * 描述：
         *  用户的客户端IP，支持IPv4和IPv6两种格式的IP地址。
         *  示例值：14.23.150.211
         * </pre>
         */
        @JsonProperty(value = "payer_client_ip")
        private String payerClientIp;
        /**
         * <pre>
         * 字段名：商户端设备号
         * 变量名：device_id
         * 是否必填：否
         * 类型：string[1,32]
         * 描述：
         *  商户端设备号（门店号或收银设备ID）。
         *  示例值：013467007045764
         * </pre>
         */
        @JsonProperty(value = "device_id")
        private String deviceId;
        /**
         * <pre>
         * 字段名：商户门店信息
         * 变量名：store_info
         * 是否必填：否
         * 类型：object
         * 描述：
         *  商户门店信息
         * </pre>
         */
        @JsonProperty(value = "store_info")
        private WxCreateOrderRequest.StoreInfo storeInfo;
        /**
         * <pre>
         * 字段名：H5场景信息
         * 变量名：h5_info
         * 是否必填：否(H5支付必填)
         * 类型：object
         * 描述：
         *  H5场景信息
         * </pre>
         */
        @JsonProperty(value = "h5_info")
        private WxCreateOrderRequest.H5Info h5Info;
    }

    @Data
    @NoArgsConstructor
    public static class StoreInfo implements Serializable {
        private static final long serialVersionUID = -1L;
        /**
         * <pre>
         * 字段名：门店编号
         * 变量名：id
         * 是否必填：是
         * 类型：string[1,32]
         * 描述：
         *  商户侧门店编号
         *  示例值：0001
         * </pre>
         */
        @JsonProperty(value = "id")
        private String id;
        /**
         * <pre>
         * 字段名：门店名称
         * 变量名：name
         * 是否必填：否
         * 类型：string[1,256]
         * 描述：
         *  商户侧门店名称
         *  示例值：腾讯大厦分店
         * </pre>
         */
        @JsonProperty(value = "name")
        private String name;
        /**
         * <pre>
         * 字段名：地区编码
         * 变量名：area_code
         * 是否必填：否
         * 类型：string[1,32]
         * 描述：
         *  地区编码，详细请见省市区编号对照表(https://pay.weixin.qq.com/wiki/doc/apiv3/terms_definition/chapter1_1_3.shtml)。
         * 示例值：440305
         * </pre>
         */
        @JsonProperty(value = "area_code")
        private String areaCode;
        /**
         * <pre>
         * 字段名：详细地址
         * 变量名：address
         * 是否必填：是
         * 类型：string[1,512]
         * 描述：
         *  详细的商户门店地址
         *  示例值：广东省深圳市南山区科技中一道10000号
         * </pre>
         */
        @JsonProperty(value = "address")
        private String address;
    }
    @Data
    @NoArgsConstructor
    public static class H5Info implements Serializable {
        private static final long serialVersionUID = -1L;
        /**
         * <pre>
         * 字段名：场景类型
         * 变量名：type
         * 是否必填：是
         * 类型：string[1,32]
         * 描述：
         *  场景类型
         *  示例值：iOS, Android, Wap
         * </pre>
         */
        @JsonProperty(value = "type")
        private String type;
        /**
         * <pre>
         * 字段名：应用名称
         * 变量名：app_name
         * 是否必填：否
         * 类型：string[1,64]
         * 描述：
         *  应用名称
         *  示例值：王者荣耀
         * </pre>
         */
        @JsonProperty(value = "app_name")
        private String appName;
        /**
         * <pre>
         * 字段名：网站URL
         * 变量名：app_url
         * 是否必填：否
         * 类型：string[1,128]
         * 描述：
         *  网站URL
         *  示例值：https://pay.qq.com
         * </pre>
         */
        @JsonProperty(value = "app_url")
        private String appUrl;
        /**
         * <pre>
         * 字段名：iOS平台BundleID
         * 变量名：bundle_id
         * 是否必填：否
         * 类型：string[1,128]
         * 描述：
         *  iOS平台BundleID
         *  示例值：com.tencent.wzryiOS
         * </pre>
         */
        @JsonProperty(value = "bundle_id")
        private String bundleId;
        /**
         * <pre>
         * 字段名：Android平台PackageName
         * 变量名：package_name
         * 是否必填：否
         * 类型：string[1,128]
         * 描述：
         *  Android平台PackageName
         *  示例值：com.tencent.tmgp.sgame
         * </pre>
         */
        @JsonProperty(value = "package_name")
        private String packageName;
    }
    @Data
    @NoArgsConstructor
    public static class SettleInfo implements Serializable {
        private static final long serialVersionUID = 1L;
        /**
         * <pre>
         * 字段名：是否指定分账
         * 变量名：profit_sharing
         * 是否必填：否
         * 类型：boolean
         * 描述：
         *  是否指定分账
         *  示例值：false
         * </pre>
         */
        @JsonProperty(value = "profit_sharing")
        private Boolean profitSharing;
    }

    @Data
    @NoArgsConstructor
    public static class WxAmount implements Serializable {
        private static final long serialVersionUID = 1L;
        /**
         * <pre>
         * 字段名：退款金额
         * 变量名：refund
         * 是否必填：是
         * 类型：int
         * 描述：
         *  退款金额，币种的最小单位，只能为整数，不能超过原订单支付金额。
         *  示例值：888
         * </pre>
         */
        @JsonProperty(value = "refund")
        private Integer refund;
        /**
         * <pre>
         * 字段名：原订单金额
         * 变量名：total
         * 是否必填：是
         * 类型：int
         * 描述：
         *  原支付交易的订单总金额，币种的最小单位，只能为整数。
         *  示例值：888
         * </pre>
         */
        @JsonProperty(value = "total")
        private Integer total;
        /**
         * <pre>
         * 字段名：币类型
         * 变量名：currency
         * 是否必填：否
         * 类型：string[1, 16]
         * 描述：
         *  符合ISO 4217标准的三位字母代码，目前只支持人民币：CNY。
         *  示例值：CNY
         * </pre>
         */
        @JsonProperty(value = "currency")
        private String currency;
    }
}
