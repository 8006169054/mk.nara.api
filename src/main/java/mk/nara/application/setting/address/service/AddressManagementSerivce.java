package mk.nara.application.setting.address.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kainos.framework.core.KainosKey;
import lombok.RequiredArgsConstructor;
import mk.nara.application.setting.address.repository.AddressManagementRepository;
import mk.nara.common.entity.AddressManagement;

@Service
@RequiredArgsConstructor
public class AddressManagementSerivce {

	private final AddressManagementRepository repository;
	
	/**
	 * 
	 * @param paramData
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default)
	public void creation(AddressManagement paramData) {
		repository.creation(paramData);
	}
	
	/**
	 * 
	 * @param paramData
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default)
	public void delete(AddressManagement paramData) {
		repository.delete(paramData);
	}
	
	/**
	 * 
	 * @param paramData
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<AddressManagement> find(AddressManagement paramData){
		return repository.find(paramData);
	}
	
}
