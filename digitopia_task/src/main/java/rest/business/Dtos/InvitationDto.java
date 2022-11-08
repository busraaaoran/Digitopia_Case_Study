package rest.business.Dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rest.entities.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvitationDto {
	
	private Date createDate;
	
	private Date updateDate;
	
	private String createdBy;
	
	private String updatedBy;
	
	private String status;
	
	private User user;
	
	private String invitationMessage;

}
