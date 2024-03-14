package mk.nara.application.setting.address.repository;

import static mk.nara.common.entity.QAddressManagement.addressManagement;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.querydsl.core.BooleanBuilder;
import kainos.framework.data.querydsl.support.repository.KainosRepositorySupport;
import mk.nara.common.entity.AddressManagement;

@Repository
public class AddressManagementRepository extends KainosRepositorySupport {

	/**
	 * 주소등록
	 * @param paramData
	 */
	public void creation(AddressManagement paramData) {
		insert(addressManagement).
		columns(
				addressManagement.mobilePhoneNumber,
				addressManagement.addressName,
				addressManagement.groupName
		)
		.values(
				paramData.getMobilePhoneNumber(),
				paramData.getAddressName(),
				paramData.getGroupName()
				)
		.execute();
	}
	
	/**
	 * 
	 * @param paramData
	 */
	public void delete(AddressManagement paramData) {
		delete(addressManagement)
		.where(addressManagement.mobilePhoneNumber.eq(paramData.getMobilePhoneNumber())
		.and(addressManagement.addressName.eq(paramData.getAddressName()))
		).execute();
	}
	
	/**
	 * 주소 조회
	 * @param paramData
	 * @return
	 */
	public List<AddressManagement> find(AddressManagement paramData){
		BooleanBuilder where = new BooleanBuilder();
		if(paramData.getMobilePhoneNumber() != null)
			where.and(addressManagement.mobilePhoneNumber.eq(paramData.getMobilePhoneNumber()));
		if(!paramData.getAddressName().isEmpty()) {
			where.and(addressManagement.addressName.eq(paramData.getAddressName()));
		}
		return selectFrom(addressManagement).where(where).orderBy(addressManagement.createDate.desc()).fetch();
	}
	
}
