package mk.nara.application.setting.manager;

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
import mk.nara.application.setting.manager.service.ManagerManagementSerivce;
import mk.nara.common.entity.CommonUser;

@RestController
@RequiredArgsConstructor
public class ManagerManagementController {

	private final ManagerManagementSerivce service;
	
	/**
	 * 
	 * @param session
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "api/manager")
	public ResponseEntity<?> creationManager(@KainosSession SessionDto session, @RequestBody CommonUser paramData) throws Exception {
		service.creationManager(paramData);
		return KainosResponseEntity.noneData();
	}
	
	/**
	 * 
	 * @param session
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	@PutMapping(value = "api/manager")
	public ResponseEntity<?> modifyManager(@KainosSession SessionDto session, @RequestBody CommonUser paramData) throws Exception {
		service.modifyManager(paramData);
		return KainosResponseEntity.noneData();
	}
	
	/**
	 * 
	 * @param session
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping(value = "api/manager")
	public ResponseEntity<?> deleteManager(@KainosSession SessionDto session, @RequestBody CommonUser paramData) throws Exception {
		paramData.setMobilePhoneNumber(session.getMobilePhoneNumber());
		service.deleteManager(paramData);
		return KainosResponseEntity.noneData();
	}
	
	/**
	 * 
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "api/manager")
	public ResponseEntity<?> findManager(@KainosSession SessionDto session, @RequestParam String userName, String usageStatus) throws Exception {
		return KainosResponseEntity.builder().build()
				.addData(service.findManager(
						CommonUser.builder().username(userName).usageStatus(usageStatus).build()
						))
				.colse();
	}
	
}
