package mk.nara.application.setting.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kainos.framework.core.servlet.KainosResponseEntity;
import kainos.framework.core.session.annotation.KainosSession;
import lombok.RequiredArgsConstructor;
import mk.nara.application.accout.dto.SessionDto;
import mk.nara.application.setting.product.service.ProductRegistrationSerivce;
import mk.nara.common.entity.ProductManagement;

@RestController
@RequiredArgsConstructor
public class ProductRegistrationController {

	private final ProductRegistrationSerivce service;
	
	/**
	 * 
	 * @param session
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "api/productregistration")
	public ResponseEntity<?> creationProduct(@KainosSession SessionDto session, @RequestBody ProductManagement paramData) throws Exception {
		paramData.setMobilePhoneNumber(session.getMobilePhoneNumber());
		service.creationProduct(paramData);
		return KainosResponseEntity.noneData();
	}
	
	/**
	 * 
	 * @param session
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	@PutMapping(value = "api/productregistration")
	public ResponseEntity<?> modifyProduct(@KainosSession SessionDto session, @RequestBody ProductManagement paramData) throws Exception {
		paramData.setMobilePhoneNumber(session.getMobilePhoneNumber());
		service.modifyProduct(paramData);
		return KainosResponseEntity.noneData();
	}
	
	/**
	 * 
	 * @param session
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping(value = "api/productregistration")
	public ResponseEntity<?> deleteProduct(@KainosSession SessionDto session, @RequestBody ProductManagement paramData) throws Exception {
		paramData.setMobilePhoneNumber(session.getMobilePhoneNumber());
		service.deleteProduct(paramData);
		return KainosResponseEntity.noneData();
	}
	
	/**
	 * 
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "api/findproduct")
	public ResponseEntity<?> findProduct(@KainosSession SessionDto session, @RequestParam String productName, String usageStatus) throws Exception {
		return KainosResponseEntity.builder().build()
				.addData(service.findProduct(
						ProductManagement.builder().productName(productName).usageStatus(usageStatus).mobilePhoneNumber(session.getMobilePhoneNumber()).build()
						))
				.colse();
	}
	
}
