package mk.nara.application.open.repository;

import static mk.nara.common.entity.QOrderMaster.orderMaster;
import static mk.nara.common.entity.QOrderProduct.orderProduct;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;

import kainos.framework.data.querydsl.support.repository.KainosRepositorySupport;
import mk.nara.application.open.dto.OpenParamDto;

@Repository
public class OpenRepository extends KainosRepositorySupport {

	/**
	 * 
	 * @param pathVariable
	 * @return
	 */
	public List<OpenParamDto.FindOrderProduct> findOrderProduct(String pathVariable){
		return select(Projections.bean(OpenParamDto.FindOrderProduct.class
		, orderProduct.productName
		, orderProduct.priceProduct
		, orderProduct.productQuantity
		, orderProduct.orderQuantity
		))
		.from(orderProduct)
		.where(orderProduct.pathVariable.eq(pathVariable))
		.fetch();
	}
	
	public OpenParamDto.FindOrder findOrder(String pathVariable){
		return select(Projections.bean(OpenParamDto.FindOrder.class
				, orderMaster.pathVariable
				, orderMaster.minimumAmount
				, orderMaster.dueDate
				))
				.from(orderMaster)
				.where(orderMaster.pathVariable.eq(pathVariable))
				.fetchOne();
	}
	
}
