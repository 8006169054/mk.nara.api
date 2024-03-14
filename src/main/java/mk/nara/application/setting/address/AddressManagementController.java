package mk.nara.application.setting.address;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kainos.framework.core.servlet.KainosResponseEntity;
import kainos.framework.core.session.annotation.KainosSession;
import lombok.RequiredArgsConstructor;
import mk.nara.application.accout.dto.SessionDto;
import mk.nara.application.setting.address.service.AddressManagementSerivce;
import mk.nara.common.entity.AddressManagement;

@RestController
@RequiredArgsConstructor
public class AddressManagementController {

	private final AddressManagementSerivce service;
	
	/**
	 * 
	 * @param session
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "api/address")
	public ResponseEntity<?> creationManager(@KainosSession SessionDto session, @RequestBody AddressManagement paramData) throws Exception {
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
	@DeleteMapping(value = "api/address")
	public ResponseEntity<?> delete(@KainosSession SessionDto session, @RequestBody AddressManagement paramData) throws Exception {
		paramData.setMobilePhoneNumber(session.getMobilePhoneNumber());
		service.delete(paramData);
		return KainosResponseEntity.noneData();
	}
	
	/**
	 * 
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "api/address")
	public ResponseEntity<?> findManager(@KainosSession SessionDto session, @RequestParam String addressName) throws Exception {
		return KainosResponseEntity.builder().build()
				.addData(service.find(
						AddressManagement.builder().addressName(addressName).build()
						))
				.colse();
	}
	
}
