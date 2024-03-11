package mk.nara.application.accout;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kainos.framework.core.lang.KainosException;
import kainos.framework.core.servlet.KainosResponseEntity;
import kainos.framework.core.session.KainosSessionContext;
import lombok.RequiredArgsConstructor;
import mk.nara.application.accout.dto.SessionDto;
import mk.nara.application.accout.service.AccoutSerivce;

@RestController
@RequiredArgsConstructor
public class AccoutController {

	private final AccoutSerivce service;
	private final KainosSessionContext kainosSession;

	/**
	 * 로그인 처리
	 * 
	 * @param userId
	 * @param userPw
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "open/login")
	public ResponseEntity<?> login(@RequestParam String userId, @RequestParam String userPw) throws Exception {
		SessionDto session = service.fiendUser(userId, userPw);
		if (session != null) {
			kainosSession.openSession(session);
			return KainosResponseEntity.builder().build().addData(session).colse();
		} else {
			return KainosResponseEntity.builder()
					.failResponse(new KainosException("Login Fail", HttpStatus.UNAUTHORIZED)).build().colse();
		}
	}

	@GetMapping(value = "api/logout")
	public ResponseEntity<?> logout() throws Exception {
		kainosSession.colseSession();
		return KainosResponseEntity.noneData();
	}

}
