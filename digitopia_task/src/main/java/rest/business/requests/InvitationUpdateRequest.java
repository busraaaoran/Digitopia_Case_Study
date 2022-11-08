package rest.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rest.entities.Invitation;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvitationUpdateRequest {
	
	private Invitation invitation;
}
