package mk.nara.application.order.repository;

import static mk.nara.common.entity.QOrderMaster.orderMaster;
import static mk.nara.common.entity.QOrderProduct.orderProduct;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;

import kainos.framework.data.querydsl.support.repository.KainosRepositorySupport;
import kainos.framework.utils.KainosDateUtil;
import mk.nara.application.accout.dto.SessionDto;
import mk.nara.application.order.dto.OrderParamDto;
import mk.nara.common.entity.OrderProduct;

@Repository
public class OrderManagementRepository extends KainosRepositorySupport {

	/**
	 * 주문서 생성
	 * @param paramData
	 * @throws Exception 
	 */
	public String creation(SessionDto session, OrderParamDto paramData) throws Exception {
		Date dueDate = KainosDateUtil.StringToData(paramData.getDueDate() + " " + paramData.getDueTime(), "yyyy-MM-dd HH");
		Date deliveryDate = KainosDateUtil.StringToData(paramData.getDeliveryDate(), "yyyy-MM-dd");
		String pathVariable = UUID.randomUUID().toString();
		insert(orderMaster).
		columns(
				orderMaster.mobilePhoneNumber,
				orderMaster.pathVariable,
				orderMaster.orderName,
				orderMaster.minimumAmount,
				orderMaster.dueDate,
				orderMaster.deliveryDate
		)
		.values(
				session.getMobilePhoneNumber(),
				pathVariable,
				paramData.getOrderName(),
				paramData.getMinimumAmount(),
				dueDate,
				deliveryDate
				)
		.execute();
		return pathVariable;
	}
	
	/**
	 * 
	 * @param paramData
	 * @throws Exception 
	 */
	public void update(SessionDto session, OrderParamDto paramData) throws Exception {
		Date dueDate = KainosDateUtil.StringToData(paramData.getDueDate() + " " + paramData.getDueTime(), "yyyy-MM-dd HH");
		Date deliveryDate = KainosDateUtil.StringToData(paramData.getDeliveryDate(), "yyyy-MM-dd");
		update(orderMaster)
		.set(orderMaster.orderName, paramData.getOrderName())
		.set(orderMaster.minimumAmount, paramData.getMinimumAmount())
		.set(orderMaster.dueDate, dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
		.set(orderMaster.deliveryDate, deliveryDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
		.where(orderMaster.mobilePhoneNumber.eq(session.getMobilePhoneNumber())
		.and(orderMaster.pathVariable.eq(paramData.getPathVariable()))
		).execute();
	}
	
	/**
	 * 
	 * @param paramData
	 */
	public void delete(SessionDto session, String pathVariable) {
		delete(orderMaster)
		.where(orderMaster.mobilePhoneNumber.eq(session.getMobilePhoneNumber())
		.and(orderMaster.pathVariable.eq(pathVariable))
		).execute();
	}
	
	/**
	 * 
	 * @param mobilePhoneNumber
	 * @param orderName
	 * @param orderStatus
	 * @return
	 */
	public List<OrderParamDto> find(String mobilePhoneNumber, String orderName, String orderStatus){
		BooleanBuilder where = new BooleanBuilder();
		if(mobilePhoneNumber != null)
			where.and(orderMaster.mobilePhoneNumber.eq(mobilePhoneNumber));
		if(!orderName.isEmpty()) {
			where.and(orderMaster.orderName.eq(orderName));
		}
		return select(Projections.bean(OrderParamDto.class
				, orderMaster.mobilePhoneNumber
				, orderMaster.pathVariable
				, orderMaster.orderName
				, orderMaster.minimumAmount
				, ExpressionUtils.as(Expressions.stringTemplate(
				        "to_char({0}, {1})"
				        , orderMaster.dueDate
				        , ConstantImpl.create("YYYY-MM-DD")), "dueDate")
				, ExpressionUtils.as(Expressions.stringTemplate(
				        "to_char({0}, {1})"
				        , orderMaster.dueDate
				        , ConstantImpl.create("hh24")), "dueTime")
				, ExpressionUtils.as(Expressions.stringTemplate(
				        "to_char({0}, {1})"
				        , orderMaster.deliveryDate
				        , ConstantImpl.create("YYYY-MM-DD")), "deliveryDate")
				))
				.from(orderMaster)
				.where(where)
				.orderBy(orderMaster.createDate.desc()).fetch();
		
		
	}
	
//	/**
//	 * 주문상품등록
//	 * @param paramData
//	 */
//	public void creationProduct(OrderProduct paramData) {
//		int seq = select(orderProduct.seq.max()).from(orderProduct)
//				.where(orderProduct.mobilePhoneNumber.eq(paramData.getMobilePhoneNumber())
//						.and(orderProduct.pathVariable.eq(paramData.getPathVariable()))
//				).fetchOne();
//		insert(orderProduct).
//		columns(
//				orderProduct.mobilePhoneNumber,
//				orderProduct.pathVariable,
//				orderProduct.seq,
//				orderProduct.productName,
//				orderProduct.priceProduct,
//				orderProduct.productQuantity,
//				orderProduct.orderQuantity
//		)
//		.values(
//				paramData.getMobilePhoneNumber(),
//				paramData.getPathVariable(),
//				seq,
//				paramData.getProductName(),
//				paramData.getPriceProduct(),
//				paramData.getProductQuantity(),
//				"0"
//				)
//		.execute();
//	}
	
	/**
	 * 
	 * @param mobilePhoneNumber
	 * @param pathVariable
	 * @return
	 */
	public List<OrderProduct> findProduct(String mobilePhoneNumber, String pathVariable){
		BooleanBuilder where = new BooleanBuilder();
		where.and(orderProduct.mobilePhoneNumber.eq(mobilePhoneNumber));
		where.and(orderProduct.pathVariable.eq(pathVariable));
		return selectFrom(orderProduct).where(where).orderBy(orderProduct.seq.desc()).fetch();
	}
	
	/**
	 * 주문 상품 등록
	 * @param session
	 * @param paramDatas
	 * @throws Exception
	 */
	public void creationProduct(SessionDto session, String pathVariable, List<OrderProduct> paramDatas) {
		for (Iterator<OrderProduct> iterator = paramDatas.iterator(); iterator.hasNext();) {
			OrderProduct product = iterator.next();
			insert(orderProduct).columns(
				orderProduct.mobilePhoneNumber,
				orderProduct.pathVariable,
				orderProduct.seq,
				orderProduct.productName,
				orderProduct.priceProduct,
				orderProduct.productQuantity,
				orderProduct.orderQuantity
			).values(
				session.getMobilePhoneNumber(),
				pathVariable,
				product.getSeq(),
				product.getProductName(),
				product.getPriceProduct(),
				product.getProductQuantity(),
				"0"
			)
			.execute();
		}
		
	}
	
	/**
	 * 
	 * @param paramData
	 */
	public void deleteProduct(SessionDto session, String pathVariable) {
		delete(orderProduct)
		.where(orderProduct.mobilePhoneNumber.eq(session.getMobilePhoneNumber())
		.and(orderProduct.pathVariable.eq(pathVariable))
		).execute();
	}
}
