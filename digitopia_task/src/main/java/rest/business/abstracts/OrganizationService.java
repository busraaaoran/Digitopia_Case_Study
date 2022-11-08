package rest.business.abstracts;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fasterxml.jackson.annotation.JsonIgnore;

import rest.business.Dtos.OrganizationDto;
import rest.business.Dtos.UserDto;
import rest.business.requests.CreateOrganizationRequest;
import rest.business.requests.DeleteOrganizationRequest;
import rest.business.requests.GetByNormalizedNameRequest;
import rest.business.requests.GetOrganizationByIdRequest;
import rest.business.requests.OrganizationUpdateRequest;
import rest.business.responses.CreateOrganizationResponse;
import rest.business.responses.DeleteOrganizationResponse;
import rest.business.responses.GetAllOrganizationsResponse;
import rest.business.responses.GetOrganizationByIdResponse;
import rest.business.responses.GetOrganizationsByNormalizedNameResponse;
import rest.business.responses.OrganizationUpdateResponse;
import rest.entities.Organization;
import rest.entities.User;

public interface OrganizationService {
	GetAllOrganizationsResponse getAll();
	
	CreateOrganizationResponse add(CreateOrganizationRequest request);
	
	OrganizationUpdateResponse update(OrganizationUpdateRequest request);
	
	DeleteOrganizationResponse delete(DeleteOrganizationRequest request);
	
	GetOrganizationByIdResponse getById(GetOrganizationByIdRequest request);
	
	List<Organization> getOrganizationList(UUID user_id);
	
	GetOrganizationsByNormalizedNameResponse getByNormalizedName(GetByNormalizedNameRequest request);
	
	@JsonIgnore
	OrganizationDto convertOrganizationToOrganizationDto(Organization organization);
}
