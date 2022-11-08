package rest.business.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import rest.business.Dtos.UserDto;

@EqualsAndHashCode(callSuper=false)
@Data
@AllArgsConstructor
public class GetUsersByNormalizedNameResponse extends BaseOutput{
	
	private List<UserDto> userList;

	public GetUsersByNormalizedNameResponse() {
		super();
	}

	public GetUsersByNormalizedNameResponse(boolean success, String message, List<UserDto> userList) {
		super(success, message);
		this.userList = userList;
	}

	public GetUsersByNormalizedNameResponse(boolean success, String message) {
		super(success, message);
	}
}
