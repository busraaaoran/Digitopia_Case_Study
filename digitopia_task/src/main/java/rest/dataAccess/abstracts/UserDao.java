package rest.dataAccess.abstracts;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rest.entities.User;

@Repository
public interface UserDao extends JpaRepository<User, UUID>{
	
	//PQL
	@Query("select u from User u where u.email = :email")
	User getUserByEmail(@Param("email") String email);
	
	//could not solve the lazily initialization here
	
	@Query("select u from User u join fetch u.invitationList where u.normalizedName = :normalizedName")
	User getUserByNormalizedName(@Param("normalizedName") String normalizedName);
	
	//List<User> getUsersByOrganizationId(UUID organization_id);
}
