package rest.dataAccess.abstracts;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rest.business.Dtos.InvitationDto;
import rest.entities.Invitation;

@Repository
public interface InvitationDao extends JpaRepository<Invitation, UUID> {
	/*
	@Query("select * from Invitation i where i.userId= :userId")
	List<InvitationDto> getInvitationsByUserId(UUID userId);
	*/
}
