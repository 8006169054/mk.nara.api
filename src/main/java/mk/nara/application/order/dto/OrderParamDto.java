package mk.nara.application.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderParamDto {
	
	private String mobilePhoneNumber;
	private String pathVariable;
	private String orderName;
	private String minimumAmount;
	private String dueDate;
	private String dueTime;
	private String deliveryDate;
	
}
