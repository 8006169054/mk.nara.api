package mk.nara.common.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct {
	
	@Id
	private String mobilePhoneNumber;
	@Id
	private String pathVariable;
	@Id
	private int seq;
	private String productName;
	private String priceProduct;
	private String productQuantity;
	private String orderQuantity;
	private LocalDate createDate;
	
	
}
