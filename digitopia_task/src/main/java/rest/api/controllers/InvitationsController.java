package rest.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rest.business.abstracts.InvitationService;
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

@RestController
@RequestMapping("/api/invitations")
public class InvitationsController {
	
	private InvitationService invitationService;
	
	public InvitationsController(InvitationService invitationService) {
		this.invitationService = invitationService;
	}
	
	@PostMapping("/getInvitationsByUserId")
	public GetAllInvitationsByUserIdResponse getInvitations(@RequestBody GetInvitationsByUserIdRequest request) {
		return this.invitationService.getInvitationsByUserId(request);
	}
	
	@PostMapping("/sendInvitation")
	public SendInvitationResponse sendInvitation(@RequestBody SendInvitationRequest request) {
		return this.invitationService.sendInvitation(request);
	}
	
	@PostMapping("/add")
	public CreateInvitationResponse add(@RequestBody CreateInvitationRequest request) {
		return this.invitationService.add(request);
	}
	
	@PutMapping("/update")
	public InvitationUpdateResponse update(@RequestBody InvitationUpdateRequest request) {
		return this.invitationService.update(request);
	}
	
	@PostMapping("/delete")
	public DeleteInvitationResponse delete(@RequestBody DeleteInvitationRequest request) {
		return this.invitationService.delete(request);
	}

}
