package rest.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rest.entities.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
	
	private User user;
	
	
}
