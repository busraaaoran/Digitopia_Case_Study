package rest.business.Dtos;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rest.entities.Invitation;
import rest.entities.Organization;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private Date createDate;
	
	private Date updateDate;
	
	private String createdBy;
	
	private String updatedBy;
	
	private String status;
	
	private String email;
	
	private String fullName;
	
	private String normalizedName;
	
	private List<Organization> organizationList;
	
	private List<Invitation> invitationList;
}
