package mk.nara.application.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import kainos.framework.core.KainosKey;
import lombok.RequiredArgsConstructor;
import mk.nara.application.order.dto.OrderParamDto;
import mk.nara.application.order.repository.OrderManagementRepository;
import mk.nara.common.entity.OrderMaster;

@Service
@RequiredArgsConstructor
public class OrderManagementSerivce {

	private final OrderManagementRepository repository;
	
	/**
	 * 
	 * @param paramData
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default)
	public void creation(OrderParamDto paramData) throws Exception {
		repository.creation(paramData);
	}
	
	/**
	 * 
	 * @param paramData
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default)
	public void update(OrderMaster paramData) {
		repository.update(paramData);
	}
	
	/**
	 * 
	 * @param paramData
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default)
	public void delete(OrderMaster paramData) {
		repository.delete(paramData);
	}
	
	/**
	 * 
	 * @param mobilePhoneNumber
	 * @param orderName
	 * @param orderStatus
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<OrderMaster> find(String mobilePhoneNumber, String orderName, String orderStatus){
		return repository.find(mobilePhoneNumber, orderName, orderStatus);
	}
	
}
