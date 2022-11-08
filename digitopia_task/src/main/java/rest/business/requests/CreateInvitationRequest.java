package rest.business.requests;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvitationRequest {
	
	private UUID userId;
	
	private String invitationMessage;
	
	private UUID createdBy;
	
	private UUID updatedBy;
}
