package mk.nara.application.setting.manager.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kainos.framework.core.KainosKey;
import lombok.RequiredArgsConstructor;
import mk.nara.application.setting.manager.repository.ManagerManagementRepository;
import mk.nara.common.entity.CommonUser;

@Service
@RequiredArgsConstructor
public class ManagerManagementSerivce {

	private final ManagerManagementRepository repository;
	
	/**
	 * 
	 * @param paramData
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default)
	public void creationManager(CommonUser paramData) throws Exception {
		repository.creationManager(paramData);
	}
	
	/**
	 * 
	 * @param paramData
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default)
	public void modifyManager(CommonUser paramData) throws Exception {
		repository.modifyManager(paramData);
	}
	
	/**
	 * 
	 * @param paramData
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default)
	public void deleteManager(CommonUser paramData) throws Exception {
		repository.deleteManager(paramData);
	}
	
	/**
	 * 
	 * @param paramData
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<CommonUser> findManager(CommonUser paramData){
		return repository.findManager(paramData);
	}
	
}
