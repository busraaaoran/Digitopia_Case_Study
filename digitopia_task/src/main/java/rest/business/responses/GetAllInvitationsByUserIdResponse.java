package rest.business.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import rest.business.Dtos.InvitationDto;

@EqualsAndHashCode(callSuper=false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllInvitationsByUserIdResponse extends BaseOutput {
	
	private List<InvitationDto> invitationList;
}
