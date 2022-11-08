package rest.business.responses;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class CreateOrganizationResponse extends BaseOutput{

	public CreateOrganizationResponse() {
		
	}

	public CreateOrganizationResponse(boolean success, String message) {
		super(success, message);
		
	}

}
