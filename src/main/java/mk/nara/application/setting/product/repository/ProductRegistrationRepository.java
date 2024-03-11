package mk.nara.application.setting.product.repository;

import static mk.nara.common.entity.QProductManagement.productManagement;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.querydsl.core.BooleanBuilder;
import kainos.framework.data.querydsl.support.repository.KainosRepositorySupport;
import mk.nara.common.entity.ProductManagement;

@Repository
public class ProductRegistrationRepository extends KainosRepositorySupport {

	/**
	 * 상품등록
	 * @param paramData
	 */
	public void creationProduct(ProductManagement paramData) {
		int seq = ((select(productManagement.seq.max().coalesce(0)).from(productManagement).where(productManagement.mobilePhoneNumber.eq(paramData.getMobilePhoneNumber())).fetchOne()) + 1);
		insert(productManagement).
		columns(
				productManagement.seq,
				productManagement.mobilePhoneNumber,
				productManagement.productName,
				productManagement.priceProduct,
				productManagement.productDescription,
				productManagement.usageStatus
		)
		.values(
				seq,
				paramData.getMobilePhoneNumber(),
				paramData.getProductName(),
				paramData.getPriceProduct(),
				paramData.getProductDescription(),
				paramData.getUsageStatus()
				)
		.execute();
	}
	
	/**
	 * 
	 * @param paramData
	 */
	public void modifyProduct(ProductManagement paramData) {
		update(productManagement)
		.set(productManagement.productName, paramData.getProductName())
		.set(productManagement.priceProduct, paramData.getPriceProduct())
		.set(productManagement.productDescription, paramData.getProductDescription())
		.set(productManagement.usageStatus, paramData.getUsageStatus())
		.where(productManagement.mobilePhoneNumber.eq(paramData.getMobilePhoneNumber())
		.and(productManagement.seq.eq(paramData.getSeq()))
		).execute();
	}
	
	/**
	 * 
	 * @param paramData
	 */
	public void deleteProduct(ProductManagement paramData) {
		delete(productManagement)
		.where(productManagement.mobilePhoneNumber.eq(paramData.getMobilePhoneNumber())
		.and(productManagement.seq.eq(paramData.getSeq()))
		).execute();
	}
	
	/**
	 * 상품조회
	 * @param paramData
	 * @return
	 */
	public List<ProductManagement> findProduct(ProductManagement paramData){
		BooleanBuilder where = new BooleanBuilder();
		where.and(productManagement.mobilePhoneNumber.eq(paramData.getMobilePhoneNumber()));
		if(!paramData.getProductName().isEmpty()) {
			where.and(productManagement.productName.eq(paramData.getProductName()));
		}
		if(!paramData.getUsageStatus().equalsIgnoreCase("ALL")) {
			where.and(productManagement.usageStatus.eq(paramData.getUsageStatus()));
		}
		
		return selectFrom(productManagement).where(where).orderBy(productManagement.seq.desc()).fetch();
	}
	
}
