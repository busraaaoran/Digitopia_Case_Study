package rest.business.abstracts;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;

import rest.business.Dtos.UserDto;
import rest.business.requests.CreateMembershipRequest;
import rest.business.requests.CreateOrganizationRequest;
import rest.business.requests.CreateUserRequest;
import rest.business.requests.DeleteUserRequest;
import rest.business.requests.GetByNormalizedNameRequest;
import rest.business.requests.GetUserByIdRequest;
import rest.business.requests.UserUpdateRequest;
import rest.business.responses.CreateMembershipResponse;
import rest.business.responses.CreateUserResponse;
import rest.business.responses.GetAllUserListResponse;
import rest.business.responses.GetUserByIdResponse;
import rest.business.responses.GetUsersByNormalizedNameResponse;
import rest.business.responses.UserDeleteResponse;
import rest.business.responses.UserUpdateResponse;
import rest.entities.Organization;
import rest.entities.User;

public interface UserService {
	
	//CRUD and additional endpoint operations
	
	GetAllUserListResponse getAll();
	
	CreateUserResponse add(CreateUserRequest request);
	
	UserUpdateResponse update(UserUpdateRequest request);
	
	UserDeleteResponse delete(DeleteUserRequest request);
	
	GetUsersByNormalizedNameResponse getByNormalizedName(GetByNormalizedNameRequest request);
	
	User getByEmail(String email);
	
	GetUserByIdResponse getById(GetUserByIdRequest request);
	
	UserDto convertUserToUserDto(User user);
	
	CreateMembershipResponse createMembership(CreateMembershipRequest request);
}
