package mk.nara.application.open;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kainos.framework.core.servlet.KainosResponseEntity;
import lombok.RequiredArgsConstructor;
import mk.nara.application.open.service.OpenSerivce;

@RestController
@RequiredArgsConstructor
public class OpenController {

	private final OpenSerivce service;

	/**
	 * 
	 * @param pathVariable
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "open/findopenorder/{pathVariable}")
	public ResponseEntity<?> findOpenOrder(@PathVariable String pathVariable) throws Exception {
		return KainosResponseEntity.builder().build()
				.addData("findOrderProduct", service.findOrderProduct(pathVariable))
				.addData("findOrder", service.findOrder(pathVariable))
				.colse();
	}
	
}
