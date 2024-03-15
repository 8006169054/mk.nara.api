package mk.nara.application.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kainos.framework.core.servlet.KainosResponseEntity;
import kainos.framework.core.session.annotation.KainosSession;
import kainos.framework.utils.KainosDateUtil;
import lombok.RequiredArgsConstructor;
import mk.nara.application.accout.dto.SessionDto;
import mk.nara.application.order.dto.OrderParamDto;
import mk.nara.application.order.service.OrderManagementSerivce;
import mk.nara.common.entity.OrderMaster;

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
	public ResponseEntity<?> creation(@KainosSession SessionDto session, @RequestBody OrderParamDto paramData) throws Exception {
		paramData.setMobilePhoneNumber(session.getMobilePhoneNumber());
		service.creation(paramData);
		return KainosResponseEntity.noneData();
	}
	
	/**
	 * 
	 * @param session
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping(value = "api/order")
	public ResponseEntity<?> delete(@KainosSession SessionDto session, @RequestBody OrderMaster paramData) throws Exception {
		paramData.setMobilePhoneNumber(session.getMobilePhoneNumber());
		service.delete(paramData);
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
	public ResponseEntity<?> update(@KainosSession SessionDto session, @RequestBody OrderMaster paramData) throws Exception {
		paramData.setMobilePhoneNumber(session.getMobilePhoneNumber());
		service.update(paramData);
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


}
