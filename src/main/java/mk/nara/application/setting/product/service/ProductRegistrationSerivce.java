package mk.nara.application.setting.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kainos.framework.core.KainosKey;
import lombok.RequiredArgsConstructor;
import mk.nara.application.setting.product.repository.ProductRegistrationRepository;
import mk.nara.common.entity.ProductManagement;

@Service
@RequiredArgsConstructor
public class ProductRegistrationSerivce {

	private final ProductRegistrationRepository repository;
	
	/**
	 * 
	 * @param paramData
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default)
	public void creationProduct(ProductManagement paramData) throws Exception {
		repository.creationProduct(paramData);
	}
	
	/**
	 * 
	 * @param paramData
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default)
	public void modifyProduct(ProductManagement paramData) throws Exception {
		repository.modifyProduct(paramData);
	}
	
	/**
	 * 
	 * @param paramData
	 * @throws Exception
	 */
	@Transactional(transactionManager = KainosKey.DBConfig.TransactionManager.Default)
	public void deleteProduct(ProductManagement paramData) throws Exception {
		repository.deleteProduct(paramData);
	}
	
	/**
	 * 
	 * @param paramData
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<ProductManagement> findProduct(ProductManagement paramData){
		return repository.findProduct(paramData);
	}
	
}
