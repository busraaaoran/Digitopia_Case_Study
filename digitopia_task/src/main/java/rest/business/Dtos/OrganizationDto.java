package rest.business.Dtos;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rest.entities.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {
	
	private String organizationName;
	
	private Date createDate;
	
	private Date updateDate;
	
	private String createdBy;
	
	private String updatedBy;
	
	private String email;
	
	private String normalizedName;
	
	private String registryNumber;
	
	private int foundedYear;
	
	private String phone;
	
	private int companySize;
	
	private List<User> userList;
}
