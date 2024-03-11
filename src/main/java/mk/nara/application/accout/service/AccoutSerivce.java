package mk.nara.application.accout.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kainos.framework.core.KainosKey;
import lombok.RequiredArgsConstructor;
import mk.nara.application.accout.dto.SessionDto;
import mk.nara.application.accout.repository.AccoutRepository;

@Service
@RequiredArgsConstructor
public class AccoutSerivce {

	private final AccoutRepository repository;
	
	/**
	 * 
	 * @param userId
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default)
	public SessionDto fiendUser(String userId, String password) throws Exception {
		return repository.fiendUser(userId, password);
	}
}
