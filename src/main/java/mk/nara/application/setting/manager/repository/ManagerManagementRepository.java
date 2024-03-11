package mk.nara.application.setting.manager.repository;

import static mk.nara.common.entity.QCommonUser.commonUser;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Repository;
import com.querydsl.core.BooleanBuilder;
import kainos.framework.data.querydsl.support.repository.KainosRepositorySupport;
import mk.nara.common.entity.CommonUser;

@Repository
public class ManagerManagementRepository extends KainosRepositorySupport {

	/**
	 * 상품등록
	 * @param paramData
	 */
	public void creationManager(CommonUser paramData) {
		insert(commonUser).
		columns(
				commonUser.username,
				commonUser.mobilePhoneNumber,
				commonUser.password,
				commonUser.authority,
				commonUser.usageStatus
		)
		.values(
				paramData.getUsername(),
				paramData.getMobilePhoneNumber(),
				DigestUtils.sha256Hex(paramData.getPassword()),
				paramData.getAuthority(),
				paramData.getUsageStatus()
				)
		.execute();
	}
	
	/**
	 * 
	 * @param paramData
	 */
	public void modifyManager(CommonUser paramData) {
		update(commonUser)
		.set(commonUser.username, paramData.getUsername())
		.set(commonUser.password, paramData.getPassword())
		.set(commonUser.authority, paramData.getAuthority())
		.set(commonUser.usageStatus, paramData.getUsageStatus())
		.where(commonUser.mobilePhoneNumber.eq(paramData.getMobilePhoneNumber())
		).execute();
	}
	
	/**
	 * 
	 * @param paramData
	 */
	public void deleteManager(CommonUser paramData) {
		delete(commonUser)
		.where(commonUser.mobilePhoneNumber.eq(paramData.getMobilePhoneNumber())
		).execute();
	}
	
	/**
	 * 사용자 조회
	 * @param paramData
	 * @return
	 */
	public List<CommonUser> findManager(CommonUser paramData){
		BooleanBuilder where = new BooleanBuilder();
		if(paramData.getMobilePhoneNumber() != null)
			where.and(commonUser.mobilePhoneNumber.eq(paramData.getMobilePhoneNumber()));
		if(!paramData.getUsername().isEmpty()) {
			where.and(commonUser.username.eq(paramData.getUsername()));
		}
		if(!paramData.getUsageStatus().equalsIgnoreCase("ALL")) {
			where.and(commonUser.usageStatus.eq(paramData.getUsageStatus()));
		}
		return selectFrom(commonUser).where(where).orderBy(commonUser.createDate.desc()).fetch();
	}
	
}
