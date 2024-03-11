package mk.nara.application.accout.dto;

import java.io.Serializable;

import kainos.framework.core.session.KainosSession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("serial")
public class SessionDto implements KainosSession, Serializable{
	
	private String name;
	private String mobilePhoneNumber;
	private String authority;
	
	@Override
	public String getUserId() {
		return String.valueOf(this.mobilePhoneNumber);
	}

	@Override
	public String getUserName() {
		return this.name;
	}

}
