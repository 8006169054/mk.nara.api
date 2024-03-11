package mk.nara.common.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductManagement {

	@Id
	private int seq;
	@Id
	private String mobilePhoneNumber;
	
//	@EmbeddedId 
//	private Constraints Key;
	private String productName;
	private String priceProduct;
	private String productDescription;
	private String usageStatus;
	private LocalDate createDate;
	
//	@Builder
//	@Data
//	@ToString
//	@AllArgsConstructor
//	@NoArgsConstructor
//	@Embeddable
//	static class Constraints {
//		private int seq;
//		private String mobilePhoneNumber;
//	}
	
}
