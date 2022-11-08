package rest.business.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import rest.business.Dtos.OrganizationDto;

@EqualsAndHashCode(callSuper=false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllOrganizationsResponse extends BaseOutput{
	
	private List<OrganizationDto> organizationList;

}
