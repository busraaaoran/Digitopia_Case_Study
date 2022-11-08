package rest.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import rest.business.Dtos.OrganizationDto;

@EqualsAndHashCode(callSuper=false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrganizationByIdResponse extends BaseOutput{
	
	private OrganizationDto organization;
}
