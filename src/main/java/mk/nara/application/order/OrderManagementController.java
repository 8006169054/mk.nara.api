package mk.nara.application.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kainos.framework.core.servlet.KainosResponseEntity;
import kainos.framework.core.session.annotation.KainosSession;
import lombok.RequiredArgsConstructor;
import mk.nara.application.accout.dto.SessionDto;
import mk.nara.application.order.dto.OrderManagementDto;
import mk.nara.application.order.service.OrderManagementSerivce;

@RestController
@RequiredArgsConstructor
public class OrderManagementController {

	private final OrderManagementSerivce service;

	/**
	 * 
	 * @param session
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "api/order")
	public ResponseEntity<?> creation(@KainosSession SessionDto session, @RequestBody OrderManagementDto paramData) throws Exception {
		service.creation(session, paramData);
		return KainosResponseEntity.noneData();
	}
	
	/**
	 * 
	 * @param session
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping(value = "api/order/{pathVariable}")
	public ResponseEntity<?> delete(@KainosSession SessionDto session, @PathVariable String pathVariable) throws Exception {
		service.delete(session, pathVariable);
		return KainosResponseEntity.noneData();
	}
	
	/**
	 * 
	 * @param session
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	@PatchMapping(value = "api/order")
	public ResponseEntity<?> update(@KainosSession SessionDto session, @RequestBody OrderManagementDto paramData) throws Exception {
		service.update(session, paramData);
		return KainosResponseEntity.noneData();
	}
	
	/**
	 * 
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "api/order")
	public ResponseEntity<?> find(@KainosSession SessionDto session, @RequestParam String orderName, String orderStatus) throws Exception {
		return KainosResponseEntity.builder().build()
				.addData(service.find(session.getMobilePhoneNumber(), orderName, orderStatus))
				.colse();
	}

	/**
	 * 
	 * @param session
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
//	@PostMapping(value = "api/orderProduct")
//	public ResponseEntity<?> creationProduct(@KainosSession SessionDto session, @RequestBody OrderProduct paramData) throws Exception {
//		paramData.setMobilePhoneNumber(session.getMobilePhoneNumber());
//		service.creationProduct(paramData);
//		return KainosResponseEntity.noneData();
//	}

	/**
	 * 
	 * @param session
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "api/orderProduct/{pathVariable}")
	public ResponseEntity<?> findProduct(@KainosSession SessionDto session, @PathVariable String pathVariable) throws Exception {
		return KainosResponseEntity.builder().build()
				.addData(service.findProduct(session.getMobilePhoneNumber(), pathVariable))
				.colse();
	}
	
}
