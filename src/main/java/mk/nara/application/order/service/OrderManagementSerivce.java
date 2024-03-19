package mk.nara.application.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kainos.framework.core.KainosKey;
import lombok.RequiredArgsConstructor;
import mk.nara.application.accout.dto.SessionDto;
import mk.nara.application.order.dto.OrderManagementDto;
import mk.nara.application.order.dto.OrderParamDto;
import mk.nara.application.order.repository.OrderManagementRepository;
import mk.nara.common.entity.OrderProduct;

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
	public void creation(SessionDto session, OrderManagementDto paramData) throws Exception{
		String pathVariable = repository.creation(session, paramData.getOrderMaster());
		if(paramData.getOrderProducts() != null)
			repository.creationProduct(session, pathVariable, paramData.getOrderProducts());
	}
	
	/**
	 * 
	 * @param paramData
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default)
	public void update(SessionDto session, OrderManagementDto paramData) throws Exception {
		repository.update(session, paramData.getOrderMaster());
		repository.deleteProduct(session, paramData.getOrderMaster().getPathVariable());
		if(paramData.getOrderProducts() != null) {
			repository.creationProduct(session, paramData.getOrderMaster().getPathVariable(), paramData.getOrderProducts());
		}
	}
	
	/**
	 * 
	 * @param paramData
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default)
	public void delete(SessionDto session, String pathVariable) {
		repository.delete(session, pathVariable);
	}
	
	/**
	 * 
	 * @param mobilePhoneNumber
	 * @param orderName
	 * @param orderStatus
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<OrderParamDto> find(String mobilePhoneNumber, String orderName, String orderStatus){
		return repository.find(mobilePhoneNumber, orderName, orderStatus);
	}
	
	/**
	 * 주문상품 등록
	 * @param paramData
	 */
//	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default)
//	public void creationProduct(OrderProduct paramData) {
//		repository.creationProduct(paramData);
//	}
	
	/**
	 * 
	 * @param mobilePhoneNumber
	 * @param pathVariable
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<OrderProduct> findProduct(String mobilePhoneNumber, String pathVariable){
		return repository.findProduct(mobilePhoneNumber, pathVariable);
	}
}
