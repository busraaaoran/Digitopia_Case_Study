package rest.dataAccess.abstracts;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rest.entities.Organization;

@Repository
public interface OrganizationDao extends JpaRepository<Organization, UUID>{
	
	@Query("select o from Organization o where o.registryNumber = :registryNumber")
	Organization getByRegistryNumber(@Param("registryNumber") String registryNumber);
	
	@Query("select o from Organization o where o.email = :email")
	Organization getOrganizationByEmail(@Param("email") String email);
	
	@Query("select o from Organization o where o.normalizedName =  :normalizedName")
	Organization getOrganizationByNormalizedName(@Param("normalizedName") String normalizedName);
	
	//List<Organization> getOrganizationsByUserId(UUID user_id);
}
