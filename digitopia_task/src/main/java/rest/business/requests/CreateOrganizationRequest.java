package rest.business.requests;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrganizationRequest {
	
	private String organizationName;
	
	private String registryNumber;
	
	private String email;
	
	private int foundedYear;
	
	private String phone;
	
	private int companySize;
	
	private UUID createdBy;
	
	private UUID updatedBy;
}
