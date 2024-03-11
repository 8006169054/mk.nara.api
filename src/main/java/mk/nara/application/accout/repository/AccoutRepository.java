package mk.nara.application.accout.repository;

import static mk.nara.common.entity.QCommonUser.commonUser;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Repository;
import com.querydsl.core.types.Projections;
import kainos.framework.data.querydsl.support.repository.KainosRepositorySupport;
import mk.nara.application.accout.dto.SessionDto;

@Repository
public class AccoutRepository extends KainosRepositorySupport {

	/**
	 * 
	 * @param userId
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public SessionDto fiendUser(String userId, String password) throws Exception {
		return select(Projections.bean(SessionDto.class
				,commonUser.username.as("name")
				,commonUser.mobilePhoneNumber
				,commonUser.authority
				))
				.from(commonUser)
				.where(commonUser.mobilePhoneNumber.eq(userId)
						.and(commonUser.password.eq(DigestUtils.sha256Hex(password))))
				.fetchOne();
	}
}
