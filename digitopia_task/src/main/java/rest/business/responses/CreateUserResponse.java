package rest.business.responses;


public class CreateUserResponse extends BaseOutput {

	public CreateUserResponse() {
		super();
		
	}

	public CreateUserResponse(boolean success, String message) {
		super(success, message);
		
	}
	
}
