package rest.business.abstracts;

import java.util.UUID;

import rest.business.Dtos.InvitationDto;
import rest.business.requests.CreateInvitationRequest;
import rest.business.requests.DeleteInvitationRequest;
import rest.business.requests.GetInvitationsByUserIdRequest;
import rest.business.requests.InvitationUpdateRequest;
import rest.business.requests.SendInvitationRequest;
import rest.business.responses.CreateInvitationResponse;
import rest.business.responses.DeleteInvitationResponse;
import rest.business.responses.GetAllInvitationsByUserIdResponse;
import rest.business.responses.InvitationUpdateResponse;
import rest.business.responses.SendInvitationResponse;
import rest.entities.Invitation;

public interface InvitationService {
	
	CreateInvitationResponse add(CreateInvitationRequest request);
	
	InvitationUpdateResponse update(InvitationUpdateRequest request);
	
	DeleteInvitationResponse delete(DeleteInvitationRequest request);
	
	Invitation getById(UUID invitation_id);
	
	GetAllInvitationsByUserIdResponse getInvitationsByUserId(GetInvitationsByUserIdRequest request);
	
	InvitationDto convertInvitationToInvitationDto(Invitation invitation);
	
	SendInvitationResponse sendInvitation(SendInvitationRequest request);
	

}
