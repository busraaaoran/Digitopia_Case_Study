package rest.business.responses;

public class DeleteInvitationResponse extends BaseOutput{

	public DeleteInvitationResponse() {
		super();
	}

	public DeleteInvitationResponse(boolean success, String message) {
		super(success, message);
	}

}
