package mk.nara.application.order.repository;

import static mk.nara.common.entity.QOrderMaster.orderMaster;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;

import kainos.framework.data.querydsl.support.repository.KainosRepositorySupport;
import kainos.framework.utils.KainosDateUtil;
import mk.nara.application.order.dto.OrderParamDto;
import mk.nara.common.entity.OrderMaster;

@Repository
public class OrderManagementRepository extends KainosRepositorySupport {

	/**
	 * 주문서 생성
	 * @param paramData
	 * @throws Exception 
	 */
	public void creation(OrderParamDto paramData) throws Exception {
		Date dueDate = KainosDateUtil.StringToData(paramData.getDueDate() + " " + paramData.getDueTime(), "yyyy-MM-dd HH");
		Date deliveryDate = KainosDateUtil.StringToData(paramData.getDeliveryDate(), "yyyy-MM-dd");
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
				paramData.getMobilePhoneNumber(),
				UUID.randomUUID().toString(),
				paramData.getOrderName(),
				paramData.getMinimumAmount(),
				dueDate,
				deliveryDate
				)
		.execute();
	}
	
	/**
	 * 
	 * @param paramData
	 */
	public void update(OrderMaster paramData) {
		update(orderMaster)
		.set(orderMaster.orderName, paramData.getOrderName())
		.set(orderMaster.minimumAmount, paramData.getMinimumAmount())
		.set(orderMaster.dueDate, paramData.getDueDate())
		.set(orderMaster.deliveryDate, paramData.getDeliveryDate())
		.where(orderMaster.mobilePhoneNumber.eq(paramData.getMobilePhoneNumber())
		.and(orderMaster.pathVariable.eq(paramData.getPathVariable()))
		).execute();
	}
	
	/**
	 * 
	 * @param paramData
	 */
	public void delete(OrderMaster paramData) {
		delete(orderMaster)
		.where(orderMaster.mobilePhoneNumber.eq(paramData.getMobilePhoneNumber())
		.and(orderMaster.pathVariable.eq(paramData.getPathVariable()))
		).execute();
	}
	
	/**
	 * 
	 * @param mobilePhoneNumber
	 * @param orderName
	 * @param orderStatus
	 * @return
	 */
	public List<OrderMaster> find(String mobilePhoneNumber, String orderName, String orderStatus){
		BooleanBuilder where = new BooleanBuilder();
		if(mobilePhoneNumber != null)
			where.and(orderMaster.mobilePhoneNumber.eq(mobilePhoneNumber));
		if(!orderName.isEmpty()) {
			where.and(orderMaster.orderName.eq(orderName));
		}
		return selectFrom(orderMaster).where(where).orderBy(orderMaster.createDate.desc()).fetch();
	}
	
}
