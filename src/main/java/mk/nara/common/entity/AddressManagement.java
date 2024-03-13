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
public class AddressManagement {
	
	@Id
	private String mobilePhoneNumber;
	@Id
	private String addressName;
	private String groupName;
	private LocalDate createDate;
	
}
