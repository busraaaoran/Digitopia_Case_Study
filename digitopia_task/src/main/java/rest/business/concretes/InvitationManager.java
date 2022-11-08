package rest.business.concretes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rest.business.Dtos.InvitationDto;
import rest.business.abstracts.InvitationService;
import rest.business.enums.InvitationStatusEnum;
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
import rest.dataAccess.abstracts.InvitationDao;
import rest.dataAccess.abstracts.UserDao;
import rest.entities.Invitation;
import rest.entities.User;

@Service
@Transactional
public class InvitationManager implements InvitationService{

	private InvitationDao invitationDao;
	
	private UserDao userDao;
	
	public InvitationManager(InvitationDao invitationDao, UserDao userDao) {
		this.invitationDao = invitationDao;
		this.userDao = userDao;
	}
	
	@Override
	public CreateInvitationResponse add(CreateInvitationRequest request) {
		
		CreateInvitationResponse response = validateInvitation(request);
		if(response.isSuccess()) {
			Invitation invitation = this.setInvitationParameters(request);
			this.invitationDao.save(invitation);
		}

		return response;
		
	}

	@Override
	public InvitationUpdateResponse update(InvitationUpdateRequest request) {
		InvitationUpdateResponse response = new InvitationUpdateResponse();
		
		if(this.invitationDao.findById(request.getInvitation().getId()).get()==null) {
			response.setMessage("No such invitation!");
			response.setSuccess(false);
			return response;
		}
		
		request.getInvitation().setUpdateDate(new Date());
		this.invitationDao.save(request.getInvitation());
		
		response.setMessage("Invitation updated successfully");
		response.setSuccess(true);
		return response;
		
	}

	@Override
	public DeleteInvitationResponse delete(DeleteInvitationRequest request) {
		
		DeleteInvitationResponse response = new DeleteInvitationResponse();
		
		Invitation invitation = this.invitationDao.findById(request.getInvitationId()).get();
		if(invitation == null) {
			response.setMessage("No such invitation");
			response.setSuccess(false);
			return response;
		}
		
		this.invitationDao.delete(invitation);
		response.setMessage("Invitation deleted successfully");
		response.setSuccess(true);
		return response;
		
	}

	@Override
	public Invitation getById(UUID invitation_id) {
		// TODO Auto-generated method stub
		return null;
	}


	private CreateInvitationResponse validateInvitation(CreateInvitationRequest request) {
		
		CreateInvitationResponse response = new CreateInvitationResponse(true,"Invitation sended successfully.");
		
		User user = this.userDao.findById(request.getUserId()).get();
		List<Invitation> invitations = user.getInvitationList();
		
		for(Invitation invitation : invitations) {
			if(invitation.getStatusId() == 3) {
				return new CreateInvitationResponse(false, "There is already a PENDING invitation!");
			}
			if(invitation.getStatusId() == 2) {
				return new CreateInvitationResponse(false, "Invitation was REJECTED before, cannot reinvite!");
			}
		}
		
		return response;
	}
	
	private Invitation setInvitationParameters(CreateInvitationRequest request) {
		
		Invitation invitation = new Invitation();
		invitation.setId(UUID.randomUUID());
		invitation.setCreateDate(new Date());
		invitation.setCreatedBy(request.getCreatedBy());
		invitation.setUpdatedBy(request.getUpdatedBy());
		invitation.setInvitationMessage(request.getInvitationMessage());
		//invitation.setUser(this.userDao.findById(request.getUserId()).get());
		invitation.setStatusId(3);
		
		return invitation;
	}

	@Override
	public InvitationDto convertInvitationToInvitationDto(Invitation invitation) {
		
		InvitationDto invitationDto = new InvitationDto();
		invitationDto.setCreateDate(invitation.getCreateDate());
		invitationDto.setCreatedBy(this.userDao.findById(invitation.getCreatedBy()).get().getFullName());
		invitationDto.setUpdatedBy(this.userDao.findById(invitation.getUpdatedBy()).get().getFullName());
		invitationDto.setUpdateDate(invitation.getUpdateDate());
		InvitationStatusEnum status = InvitationStatusEnum.getValue(invitation.getStatusId());
		if(status!=null) {
			invitationDto.setStatus(status.name());
		}
		invitationDto.setInvitationMessage(invitation.getInvitationMessage());
		//invitationDto.setUser(invitation.getUser());
		
		return invitationDto;
	}

	@Override
	public GetAllInvitationsByUserIdResponse getInvitationsByUserId(GetInvitationsByUserIdRequest request) {
		
		GetAllInvitationsByUserIdResponse response = new GetAllInvitationsByUserIdResponse();
		
		User user = this.userDao.findById(request.getUserId()).get();
		List<InvitationDto> invitationDtos = new ArrayList<>();
		
		for(Invitation invitation: user.getInvitationList()) {
			invitationDtos.add(this.convertInvitationToInvitationDto(invitation));
		}
		response.setInvitationList(invitationDtos);
		response.setMessage("Invitations listed");
		response.setSuccess(true);
		
		return response;
		
	}

	@Override
	public SendInvitationResponse sendInvitation(SendInvitationRequest request) {
		
		SendInvitationResponse response = new SendInvitationResponse();
		
		User user = this.userDao.findById(request.getUserId()).get();
		Invitation invitation = this.invitationDao.findById(request.getInvitationId()).get();
		
		if(user.getInvitationList().contains(invitation)) {
			response.setMessage("There is already a PENDING invitation");
			response.setSuccess(false);
			return response;
		}
		
		invitation.setUser(user);
		this.invitationDao.save(invitation);
		
		response.setMessage("Invitation sended");
		response.setSuccess(true);
		return response;
	}
	
	
}
