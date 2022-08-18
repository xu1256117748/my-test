//package com.xyk.interfaceTest.controller;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.constraints.Size;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.jason.common.cache.CacheProvider;
//import com.jason.common.security.dto.SecurityAccount;
//import com.jason.common.security.support.RequestAccount;
//import com.jason.common.ssm.domain.vo.response.BasePageResponse;
//import com.jason.common.ssm.domain.vo.response.BaseResponse;
//import com.jason.common.ssm.web.controller.BaseController;
//import com.jason.common.utils.DateUtil;
//import com.single.xuedao.constant.PayOrderConstant;
//import com.single.xuedao.constant.WxConstant;
//import com.single.xuedao.constant.XueDaoErrorCode;
//import com.single.xuedao.module.cms.domain.entity.CmsActivity;
//import com.single.xuedao.module.cms.service.impl.CmsActivityAccountServiceImpl;
//import com.single.xuedao.module.cms.service.impl.CmsActivityServiceImpl;
//import com.single.xuedao.module.pay.domain.dto.WxUnifiedOrder;
//import com.single.xuedao.module.pay.domain.entity.PayOrder;
//import com.single.xuedao.module.pay.domain.entity.PayOrderMain;
//import com.single.xuedao.module.pay.domain.entity.PayOrderMainDetail;
//import com.single.xuedao.module.pay.domain.entity.PayOrderRefund;
//import com.single.xuedao.module.pay.domain.vo.request.SavePayOrderReq;
//import com.single.xuedao.module.pay.domain.vo.response.CreateOrderNativeResp;
//import com.single.xuedao.module.pay.domain.vo.response.XueDaoPayOrderResp;
//import com.single.xuedao.module.pay.service.impl.PayOrderMainDetailServiceImpl;
//import com.single.xuedao.module.pay.service.impl.PayOrderMainServiceImpl;
//import com.single.xuedao.module.pay.service.impl.PayOrderRefundServiceImpl;
//import com.single.xuedao.module.pay.service.impl.PayOrderServiceImpl;
//import com.single.xuedao.module.tech.domain.entity.TechResource;
//import com.single.xuedao.module.tech.service.impl.TechResourceServiceImpl;
//import com.single.xuedao.utils.HjBeanUtil;
//import com.single.xuedao.utils.R;
//import com.single.xuedao.utils.WxPayUtil;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * <p>
// * 订单表 ,暂时不支持混合购买。购物车需要分类。 前端控制器
// * </p>
// *
// * @author Jason
// * @since 2021-04-14
// */
//@Api(tags = "订单前端控制器")
//@RestController
//@RequestMapping("/pay/order")
//@Validated
//@Slf4j
//public class PayOrderApi extends BaseController {
//
//	@Autowired
//	DefaultIdentifierGenerator identifierGenerator;
//	@Autowired
//	CmsActivityServiceImpl cmsActivityService;
//	@Autowired
//	TechResourceServiceImpl techResourceService;
//	@Autowired
//	PayOrderServiceImpl payOrderService;
//	@Autowired
//	PayOrderMainServiceImpl payOrderMainService;
//	@Autowired
//	PayOrderMainDetailServiceImpl payOrderMainDetailService;
//	@Autowired
//	CmsActivityAccountServiceImpl cmsActivityAccountService;
//	@Autowired
//	PayOrderRefundServiceImpl payOrderRefundService;
//	@Autowired
//	CacheProvider cacheProvider;
//
//	@ApiOperation(value = "生成订单",notes = "全部必须")
//	@PostMapping("/create")
//	@Transactional
//	public BaseResponse create(@Validated SavePayOrderReq savePayOrderReq, @RequestAccount SecurityAccount account,HttpServletRequest request) throws Exception {
//		//标记是否能复用订单
//		boolean rePay = false;
//		if(account == null) {
//			return fail(XueDaoErrorCode.security_null);
//		}
//		Long accountId = account.getAccountId();
//		int productType = savePayOrderReq.getProductType();
//		Long productId = savePayOrderReq.getProductId();
//		String productName = "";
//		BigDecimal price = null;
//		//1 查询是否已购买（研讨活动或教学资源）
//		PayOrder lastOrder = payOrderService.getLastOrder(productId, productType, accountId);
//		boolean checkHadBought = payOrderService.checkHadBought(lastOrder);
//		//已购买的直接返回成功
//		if(checkHadBought) {
//			return R.ok().message("请勿重复提交").build();
//		}else {
//			//验证是否满足复用已存在的未支付订单的条件
//			boolean checkCanRePay = payOrderService.checkCanRePay(lastOrder);
//			if(checkCanRePay) {
//				//标记支付订单复用
//				rePay = true;
//			}
//		}
//
//		PayOrder payOrder = null;
//		//不是重新支付的订单，生成新的支付订单
//		CreateOrderNativeResp resp = new CreateOrderNativeResp();
//		if(!rePay) {
//			//1.0根据商品类型生成子订单
//			Long nextId = identifierGenerator.nextId(null);
//			PayOrderMainDetail payOrderMainDetail = new PayOrderMainDetail();
//			//1.1根据商品类型和id查询商品信息
//			BigDecimal quantity = new BigDecimal(savePayOrderReq.getQuantity());
//			BigDecimal naturalPrice = null;
//			BigDecimal discount = null;
//			if(productType == PayOrderConstant.PRODUCT_TYPE_ACTIVITY) {
//				CmsActivity byId = cmsActivityService.getById(productId);
//				naturalPrice = byId.getPrice();
//				productName = byId.getTitle();
//				//1.2查询商品优惠信息
//				discount = new BigDecimal(0);
//				price = naturalPrice.subtract(discount);
//			}else if (productType == PayOrderConstant.PRODUCT_TYPE_RESOURCE) {
//				TechResource byId = techResourceService.getById(productId);
//				naturalPrice = byId.getPrice();
//				productName = byId.getTitle() +" "+ byId.getName();
//				//后期需要从优惠中取出价格
//				//1.2查询商品优惠信息
//				discount = new BigDecimal(0);
//				price = naturalPrice.subtract(discount);
//			}
//			payOrderMainDetail.setNaturalPrice(naturalPrice);
//			payOrderMainDetail.setNaturalTotalPrice(naturalPrice.multiply(quantity));
//
//			//1.3商品实际价格
//			payOrderMainDetail.setPrice(price);
//			payOrderMainDetail.setTotalPrice(price.multiply(quantity));
//			//1.4其他商品信息
//			payOrderMainDetail.setOutTradeNo(nextId);
//			payOrderMainDetail.setProductId(productId);
//			payOrderMainDetail.setProductType(productType);
//			payOrderMainDetail.setQuantity(savePayOrderReq.getQuantity());
//			payOrderMainDetail.setProductName(productName);
//			payOrderMainDetail.setCreateUserId(accountId);
//			payOrderMainDetailService.save(payOrderMainDetail);
//			//2 .生成本地主订单
//			PayOrderMain payOrderMain = new PayOrderMain();
//			payOrderMain.setId(nextId);
//			payOrderMain.setCreateUserId(accountId);
//			//2.1设置价格和优惠信息
//			BigDecimal naturalPayFee = price.multiply(quantity);
//			//2.2设置原总价
//			payOrderMain.setNaturalPayFee(naturalPayFee);
//			//2.3优惠
//			payOrderMain.setDiscount(discount.multiply(quantity));
//			//2.4实付总价
//			BigDecimal payFee = naturalPayFee.subtract(discount.multiply(quantity));
//			payOrderMain.setPayFee(payFee);
//			//2.6保存本地主订单
//			payOrderMainService.save(payOrderMain);
//
//
//			//3 生成支付订单
//			payOrder = new PayOrder();
//			if(savePayOrderReq.getTradeApp() == PayOrderConstant.TRADE_APP_XUEDAO_PC) {
//				payOrder.setAppId(WxConstant.APPID_XUEDAO);
//			}
//			//3.1设置参数
//			payOrder.setOutTradeNo(nextId);
//			payOrder.setAmount(payFee);
//			payOrder.setBuyerOpenid(null);
//			payOrder.setCreateUserId(account.getAccountId());
//			payOrder.setCreateUserName(account.getUsername());
//
//			payOrder.setTradeState(PayOrderConstant.TRADE_STATE_USERPAYING);
//
//			Date createTime = new Date();
//			if(payFee.compareTo(BigDecimal.ZERO)==0) {
//				payOrder.setTradeState(PayOrderConstant.TRADE_STATE_SUCCESS);
//				payOrder.setPayTime(createTime);
//			}
//			payOrder.setCreateTime(createTime);
//			payOrder.setUpdateTime(createTime);
//			payOrder.setExpireTime(DateUtil.addMinutes(createTime, PayOrderConstant.TRADE_PAY_OUT_TIME_MINUTE));
//			//3.2支付方式，默认微信NATIVE
//			payOrder.setTradeType(PayOrderConstant.TRADE_TYPE_NATIVE);
//			payOrderService.save(payOrder);
//
//			//4.向微信服务器提交预订单，返回二维码，金额为0，也就是则不提交直接返回
//			if(!PayOrderConstant.TRADE_STATE_SUCCESS.equals(payOrder.getTradeState())) {
//				// 4.1提交预订单
//				//提交新的预订单
//				//生成二维码返回
//				WxUnifiedOrder wxUnifiedOrder = new WxUnifiedOrder();
//				wxUnifiedOrder.setClintIp(WxPayUtil.getClientIpAddress(request));
//				wxUnifiedOrder.setOutTradeNo(nextId);
//				wxUnifiedOrder.setProductId(productId);
//				wxUnifiedOrder.setProductName(productName);
//				wxUnifiedOrder.setTotalFee(price.intValue()*100);
//				//调用微信统一下单api获得二维码链接
//				Map<String, String> wxNativeJson = WxPayUtil.unifiedOrder(wxUnifiedOrder, WxConstant.SING_MD5);
//				String codeUrl = wxNativeJson.get(WxConstant.CODE_URL);
//
//				resp.setOutTradeNoStr(nextId.toString());
//				//生成二维码图片base64字符串
//				String qrCodeImageStr = WxPayUtil.createQrCodeImageStr(codeUrl);
//				//存入redis中，过期时间为15分钟
//				cacheProvider.set(nextId.toString(), qrCodeImageStr, 2*PayOrderConstant.TRADE_PAY_OUT_TIME_MINUTE*60L);
//				resp.setQrCodeImage(qrCodeImageStr);
//
//			}else {
//				//0元订单处理
//				//4.1加入教研活动
//				if(productType == PayOrderConstant.PRODUCT_TYPE_ACTIVITY) {
//					Boolean joinActivity = cmsActivityService.joinActivity(account, productId);
//					if(!joinActivity) {
//						return fail("加入失败，请联系管理员");
//					}
//				}
//				//4.1获得资源下载权限
//				resp.setOutTradeNoStr(nextId.toString());
//			}
//		}else {
//			//是重新支付的订单（非0元）
//			//复用订单lastOrder
//			payOrder = lastOrder;
//			//获得redis中的二维码字符串
//			String qrCodeImageStr = cacheProvider.get(payOrder.getOutTradeNo().toString());
//			//返回前端
//			resp.setOutTradeNoStr(payOrder.getOutTradeNo().toString());
//			resp.setQrCodeImage(qrCodeImageStr);
//
//		}
//		return success(resp);
//
//	}
//
//	@ApiOperation("重新支付")
//	@PostMapping("/rePay")
//	public BaseResponse rePay(@ApiParam("订单号")String outTradeNoStr,@RequestAccount SecurityAccount account) {
//		//查询订单
//		PayOrder payOrder = payOrderService.getOne(new QueryWrapper<PayOrder>()
//				.eq(PayOrder.OUT_TRADE_NO, Long.valueOf(outTradeNoStr))
//				.eq(PayOrder.CREATE_USER_ID, account.getAccountId())
//				.eq(PayOrder.TRADE_STATE, PayOrderConstant.TRADE_STATE_USERPAYING)
//				.lt(PayOrder.EXPIRE_TIME, LocalDateTime.now())
//				.gt(PayOrder.AMOUNT, 0));
//		//生成支付二维码
//		if(payOrder != null) {
//
//		}
//		return success("二维码");
//	}
//
//
//	@ApiOperation("批量退款")
//	@PostMapping("/batchRefund")
//	@Transactional
//	public BaseResponse batchRefund(@ApiParam("订单id（数字形式的字符串）")@Size(min = 1) String[] ids,
//			@RequestAccount SecurityAccount account) {
//		List<String> asList = Arrays.asList(ids);
//		List<Long> idList = new ArrayList<Long>();
//		asList.forEach(item -> idList.add(Long.parseLong(item)));
//		//1. 收集复合要求的订单id
//		List<PayOrder> list = payOrderService.list(new QueryWrapper<PayOrder>()
//				.in(PayOrder.OUT_TRADE_NO, idList)
//				.eq(PayOrder.CREATE_USER_ID, account.getAccountId()));
//		if(HjBeanUtil.isEmpty(list)) {
//			throw new RuntimeException("没有符合要求的订单");
//		}
//		List<Long> orderMainIds = list.parallelStream().map(item -> item.getOutTradeNo()).collect(Collectors.toList());
//		List<PayOrderMainDetail> orderMainDetails = payOrderMainDetailService.list(new QueryWrapper<PayOrderMainDetail>()
//				.in(PayOrderMainDetail.OUT_TRADE_NO, orderMainIds));
//
//		List<Long> activityIds = orderMainDetails.stream()
//				.filter(item -> item.getProductType() == PayOrderConstant.PRODUCT_TYPE_ACTIVITY)
//				.map(item -> item.getProductId())
//				.collect(Collectors.toList());
//
//
//		//3.筛选有金额和无金额的订单
//		List<PayOrder> noPayOrders = new ArrayList<PayOrder>();
//		List<PayOrder> withPayOrders = new ArrayList<PayOrder>();
//		for (PayOrder payOrder : list) {
//			if(payOrder.getAmount().compareTo(BigDecimal.ZERO) == 0) {
//				noPayOrders.add(payOrder);
//			}else {
//				withPayOrders.add(payOrder);
//			}
//
//		}
//		//3.1无金额的订单处理
//		if(!HjBeanUtil.isEmpty(noPayOrders)) {
//			payOrderRefundService.batchRefund(noPayOrders, account, false);
//
//			//3.1.1教研直接活动恢复状态
//			Boolean refundByOrder = cmsActivityAccountService.refundByOrder(account, activityIds);
//
//			if(!refundByOrder) {
//				throw new RuntimeException("教研活动退出失败，请联系管理员");
//			}
//		}
//
//		//3.2有金额的订单转入退款
//		if(!HjBeanUtil.isEmpty(withPayOrders)) {
//
//			//3.2.1写退款单表
//			payOrderRefundService.batchRefund(withPayOrders, account, true);
//
//		}
//
//		//4.应答前端
//
//		return success();
//	}
//
//	@ApiOperation("查询支付结果")
//	@GetMapping("/check")
//	public BaseResponse check(@ApiParam("订单id")@RequestParam String outTradeNoStr,@RequestAccount SecurityAccount account) {
//
//		//7.前端查询支付结果
//		PayOrder payOrder = payOrderService.getOne(new QueryWrapper<PayOrder>()
//				.eq(PayOrder.OUT_TRADE_NO, Long.valueOf(outTradeNoStr))
//				.eq(PayOrder.CREATE_USER_ID, account.getAccountId()));
//		//8.应答前端
//		String tradeState = "";
//		if(payOrder != null) {
//			tradeState = payOrder.getTradeState();
//		}
//
//		return (PayOrderConstant.TRADE_STATE_SUCCESS.equals(tradeState)) ? success() :fail();
//
//	}
//
//	@ApiOperation("分页查询订单信息")
//	@GetMapping("/page")
//	public BaseResponse<BasePageResponse<XueDaoPayOrderResp>> page(@ApiParam("开始时间")@RequestParam(required = false) Date startTime,
//			@ApiParam("结束时间")@RequestParam(required = false) Date endTime,
//			@ApiParam("订单状态:USERPAYING—用户支付中,SUCCESS—支付成功,CLOSED—已关闭,PAYERROR--支付异常")@RequestParam(required = false) String tradeState,
//			@ApiParam("每页大小")@RequestParam Integer limit,
//			@ApiParam("页码")@RequestParam Integer offset,
//			@RequestAccount SecurityAccount acccAccount) {
//		if(acccAccount == null) {
//			return fail(XueDaoErrorCode.security_null);
//		}
//		BasePageResponse<XueDaoPayOrderResp> basePageResponse = new BasePageResponse<>();
//		PageHelper.startPage(offset, limit);
//		List<PayOrder> orderList = payOrderService.list(new QueryWrapper<PayOrder>()
//				.eq(PayOrder.CREATE_USER_ID, acccAccount.getAccountId())
//				.eq(!HjBeanUtil.isEmpty(tradeState),PayOrder.TRADE_STATE, tradeState)
//				.ge(startTime != null,PayOrder.CREATE_TIME, startTime)
//				.le(endTime != null,PayOrder.CREATE_TIME, endTime)
//				.orderByDesc(PayOrder.CREATE_TIME));
//		PageInfo<PayOrder> pageInfo = new PageInfo<>(orderList);
//		//主订单id集合
//		if(pageInfo.getSize() > 0) {
//			List<Long> outTradeNoList = orderList.parallelStream()
//					.map(item -> item.getOutTradeNo())
//					.collect(Collectors.toList());
//			Map<Long, List<PayOrder>> idToOrderMap = orderList.parallelStream()
//					.collect(Collectors.groupingBy(item -> item.getOutTradeNo()));
//			//业务暂时不需要主订单信息
//			//List<PayOrderMain> orderMainList = payOrderMainService.list(new QueryWrapper<PayOrderMain>().in(PayOrderMain.ID, outTradeNoList));
//			//Map<Long, List<PayOrderMain>> idToOrderMainMap = orderMainList.parallelStream().collect(Collectors.groupingBy(item -> item.getId()));
//			List<PayOrderMainDetail> orderMainDetailList = payOrderMainDetailService.list(new QueryWrapper<PayOrderMainDetail>().in(PayOrderMainDetail.OUT_TRADE_NO, outTradeNoList));
//			//主订单id对应orderMainDetail
//			Map<Long, List<PayOrderMainDetail>> idToOrderMainDetailMap = orderMainDetailList.parallelStream()
//					.collect(Collectors.groupingBy(item -> item.getOutTradeNo()));
//			List<Long> activityIds = orderMainDetailList.parallelStream()
//					.filter(item -> item.getProductType() == PayOrderConstant.PRODUCT_TYPE_ACTIVITY)
//					.map(item -> item.getProductId())
//					.distinct()
//					.collect(Collectors.toList());
//			//productId对应cmsActivity
//			Map<Long, List<CmsActivity>> productIdToCmsActivity = null;
//			if(!HjBeanUtil.isEmpty(activityIds)) {
//				List<CmsActivity> activityList = cmsActivityService.listByIds(activityIds);
//				productIdToCmsActivity = activityList.parallelStream()
//						.collect(Collectors.groupingBy(CmsActivity::getId));
//			}
//			List<XueDaoPayOrderResp> xueDaoPayOrderRespList = HjBeanUtil.copyList(orderList, XueDaoPayOrderResp.class);
//			//查询订单相关退款单
//			List<Long> seccessOrCLosedOrderList = orderList.parallelStream()
//				.filter(item ->
//					PayOrderConstant.TRADE_STATE_SUCCESS.equals(item.getTradeState()) || PayOrderConstant.TRADE_STATE_CLOSED.equals(item.getTradeState())
//				)
//				.map(item -> item.getOutTradeNo()).collect(Collectors.toList());
//			List<PayOrderRefund> payOrderRefundList = null;
//			Map<Long, List<PayOrderRefund>> idToOrderRefundMap = null;
//
//			if(!HjBeanUtil.isEmpty(seccessOrCLosedOrderList)) {
//				payOrderRefundList = payOrderRefundService.list(new QueryWrapper<PayOrderRefund>().in(PayOrderRefund.OUT_TRADE_NO, seccessOrCLosedOrderList));
//				if(!HjBeanUtil.isEmpty(payOrderRefundList)) {
//					idToOrderRefundMap = payOrderRefundList.parallelStream()
//							.collect(Collectors.groupingBy(item -> item.getOutTradeNo()));
//
//				}
//			}
//
//			//vo列表设定字段值
//			for (XueDaoPayOrderResp item : xueDaoPayOrderRespList) {
//				Long outTradeNo = item.getOutTradeNo();
//				List<PayOrderMainDetail> list = idToOrderMainDetailMap.get(outTradeNo);
//				PayOrderMainDetail mainDetail = list.get(0);
//				if(!HjBeanUtil.isEmpty(list)) {
//					//resp设置产品名
//					item.setName(mainDetail.getProductName().replace("null",""));
//					//resp设置原价
//					item.setNaturalPayFee(mainDetail.getNaturalPrice());
//					//resp设置实付价
//					item.setPayFee(mainDetail.getPrice());
//					//resp设置商品id
//					item.setProductId(mainDetail.getProductId());
//					//resp设置商品类型
//					item.setProductType(mainDetail.getProductType());
//					//resp设置活动时间信息
//					if(!HjBeanUtil.isEmpty(productIdToCmsActivity)) {
//						List<CmsActivity> activityList = productIdToCmsActivity.get(mainDetail.getProductId());
//						if(!HjBeanUtil.isEmpty(activityList)) {
//							CmsActivity activity = activityList.get(0);
//							item.setActivityStartTime(activity.getActivityStartTime());
//							item.setActivityEndTime(activity.getActivityEndTime());
//						}
//					}
//					//resp设置退款信息
//					if(idToOrderRefundMap != null) {
//						List<PayOrderRefund> refundList = idToOrderRefundMap.get(outTradeNo);
//						if(!HjBeanUtil.isEmpty(refundList)) {
//							PayOrderRefund payOrderRefund = refundList.get(0);
//							item.setRefundedIdStr(payOrderRefund.getId().toString());
//							item.setRefundStatus(payOrderRefund.getRefundStatus());
//							item.setRefundStatusName(PayOrderConstant.REFUND_STATE_2_NAME_MAP.get(payOrderRefund.getRefundStatus()));
//						}
//					}
//
//				}
//				//resp设置支付状态名
//				item.setTradeStateName(PayOrderConstant.TRADE_STATE_2_NAME_MAP.get(item.getTradeState()));
//				//取出对应支付订单
//				PayOrder payOrder = idToOrderMap.get(outTradeNo).get(0);
//				//计算订单剩余时间
//				int resSec = (int)(long)((payOrder.getExpireTime().getTime() - payOrder.getCreateTime().getTime())/1000L);
//				//如果订单已支付，支付剩余时间置0
//				resSec = (payOrder.getPayTime() == null) ? resSec : 0;
//				item.setRestSec(resSec);
//				item.setOutTradeNoStr(outTradeNo.toString());
//			}
//			basePageResponse.setRecords(xueDaoPayOrderRespList);
//			basePageResponse.setTotal(pageInfo.getTotal());
//		}
//		return success(basePageResponse);
//	}
//}
