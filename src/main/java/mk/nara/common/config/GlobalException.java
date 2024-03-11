package mk.nara.common.config;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kainos.framework.core.context.KainosMessageAccessor;
import kainos.framework.core.lang.KainosBusinessException;
import kainos.framework.core.lang.KainosException;
import kainos.framework.core.servlet.KainosResponseEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalException {

	@Autowired
	private KainosMessageAccessor message;

	/**
	 * 시스템 오류
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	private ResponseEntity<?> systemException(Exception ex) {
		log.error("GlobalException Exception {}", ex);
		/* Exception 메세지, 타입 표현방식 */
		KainosException kainosException = new KainosException(message.getMessage("common.system.error"), HttpStatus.INTERNAL_SERVER_ERROR);
		kainosException.setType(message.getMessageType("common.system.error"));
		return KainosResponseEntity.builder().failResponse(kainosException).build().colse();

		/* Exception 메세지 표현방식 */
//    	return KainosResponseEntity.builder().failResponse(new KainosException(message.getMessage("common.system.error"), HttpStatus.INTERNAL_SERVER_ERROR)).build().colse();
		/* Exception 표현방식 */
//      return KainosResponseEntity.builder().failResponse(new KainosException(ex, HttpStatus.INTERNAL_SERVER_ERROR)).build().colse();
	}

	/**
	 * 로그인 실패
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(AuthenticationException.class)
	private ResponseEntity<?> authenticationException(AuthenticationException ex) {
		KainosException kainosException = new KainosException("User information is incorrect. Please check again.", HttpStatus.OK);
		kainosException.setType("E");
		return KainosResponseEntity.builder().failResponse(kainosException).build().colse();
	}

	/**
	 * 시스템별 비즈니스 오류
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(KainosBusinessException.class)
	private ResponseEntity<?> kainosCustomerException(KainosBusinessException ex) {
		KainosException kainosException = new KainosException(
				message.getMessageConvert(ex.getMessageId(), ex.getArguments()), HttpStatus.OK);
		kainosException.setType(message.getMessageType(ex.getMessageId()));
		return KainosResponseEntity.builder().failResponse(kainosException).build().colse();
	}

	/**
	 * 파라미터 벨리데이션
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	private ResponseEntity<?> validException(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		String validMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
		KainosException kainosException = new KainosException(validMessage, HttpStatus.BAD_REQUEST);
		kainosException.setType(message.getMessageType("common.system.error"));
		return KainosResponseEntity.builder().failResponse(kainosException).build().colse();
	}

	/**
	 * 파라미터 벨리데이션
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler({ MissingServletRequestParameterException.class })
	private ResponseEntity<?> validRequestParameterException(MissingServletRequestParameterException ex) {
		KainosException kainosException = new KainosException(
				"[ " + ex.getParameterName() + " ]" + " parameter is missing", HttpStatus.BAD_REQUEST);
		kainosException.setType(message.getMessageType("common.system.error"));
		return KainosResponseEntity.builder().failResponse(kainosException).build().colse();
	}

}
