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
public class CommonUser {

	@Id
	private String mobilePhoneNumber;
	private String username;
	private String password;
	private String authority;
	private String usageStatus;
	private LocalDate createDate;
	
	public String getAuthority() {
		if(authority.equals("A")) return "어드민";
		else if(authority.equals("B")) return "일반사용자";
		else if(authority.equals("어드민")) return "A";
		else if(authority.equals("일반사용자")) return "B";
		else return "";
	}
	
}
