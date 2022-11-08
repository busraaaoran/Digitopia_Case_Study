package rest.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rest.entities.Organization;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationUpdateRequest {
	
	private Organization organization;
}
