package mk.nara.application.open.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class OpenParamDto {
	
	FindOrderProduct findOrderProduct;
	FindOrder findOrder;
	
	@Builder
	@Data
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	public static class FindOrder {
		String pathVariable;
		@JsonFormat(pattern="###,###")
		String minimumAmount;
		LocalDateTime dueDate;
		
		public boolean getCheckDueDate() {
			return LocalDateTime.now().isAfter(dueDate);
		}
	}
	
	@Builder
	@Data
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	public static class FindOrderProduct {
		String productName;
		@JsonFormat(pattern="###,###")
		String priceProduct;
		String productQuantity;
		String orderQuantity;
	}

}
