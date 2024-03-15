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
public class OrderMaster {
	
	@Id
	private String mobilePhoneNumber;
	@Id
	private String pathVariable;
	private String orderName;
	private String minimumAmount;
	private LocalDate dueDate;
	private LocalDate deliveryDate;
	private LocalDate createDate;
	
	
}
