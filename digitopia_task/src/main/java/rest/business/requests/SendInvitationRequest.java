package rest.business.requests;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendInvitationRequest {
	
	private UUID userId;
	
	private UUID invitationId;
}
