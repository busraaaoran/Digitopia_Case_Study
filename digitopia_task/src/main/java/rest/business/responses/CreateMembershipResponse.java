package rest.business.responses;

public class CreateMembershipResponse extends BaseOutput {

	public CreateMembershipResponse() {
		super();
	}

	public CreateMembershipResponse(boolean success, String message) {
		super(success, message);
	}

}
