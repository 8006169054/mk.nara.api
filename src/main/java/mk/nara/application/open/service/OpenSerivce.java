package mk.nara.application.open.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import mk.nara.application.open.dto.OpenParamDto;
import mk.nara.application.open.repository.OpenRepository;

@Service
@RequiredArgsConstructor
public class OpenSerivce {

	private final OpenRepository repository;
	
	/**
	 * 
	 * @param pathVariable
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<OpenParamDto.FindOrderProduct> findOrderProduct(String pathVariable){
		return repository.findOrderProduct(pathVariable);
	}
	
	@Transactional(readOnly = true)
	public OpenParamDto.FindOrder findOrder(String pathVariable){
		return repository.findOrder(pathVariable);
	}
	
	@Transactional(readOnly = true)
	public List<String> findAddr(String pathVariable){
		return repository.findAddr(pathVariable);
	}
	
}
