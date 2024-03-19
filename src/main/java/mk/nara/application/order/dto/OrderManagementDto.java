package mk.nara.application.order.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mk.nara.common.entity.OrderProduct;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderManagementDto {

	private OrderParamDto orderMaster;
	private List<OrderProduct> orderProducts;
}
