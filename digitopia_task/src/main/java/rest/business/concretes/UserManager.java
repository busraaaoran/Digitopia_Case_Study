package rest.business.concretes;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rest.business.Dtos.UserDto;
import rest.business.abstracts.UserService;
import rest.business.enums.UserStatusEnum;
import rest.business.requests.CreateMembershipRequest;
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
import rest.dataAccess.abstracts.OrganizationDao;
import rest.dataAccess.abstracts.UserDao;
import rest.entities.Organization;
import rest.entities.User;

@Service
@Transactional
public class UserManager implements UserService{
	
	//Dependency Injection
	private UserDao userDao;
	@Lazy
	private OrganizationDao organizationDao;
	
	@Autowired
	public UserManager(UserDao userDao, OrganizationDao organizationDao) {
		this.userDao = userDao;
		this.organizationDao = organizationDao;
	}
	

	@Override
	public GetAllUserListResponse getAll() {
		List<User> users = this.userDao.findAll();
		GetAllUserListResponse response = new GetAllUserListResponse();
		response.setUserList(this.generateUserDto(users));
		response.setMessage("Users listed");
		response.setSuccess(true);
		
		return response;
	}
	
	@Override
	public CreateUserResponse add(CreateUserRequest request) {
		
		CreateUserResponse response = this.validateInput(request);
		if(response.isSuccess()) {
			User user = this.setUserParameters(request);
			this.userDao.save(user);
			
		}
		return response;
	}

	
	@Override
	public UserUpdateResponse update(UserUpdateRequest request) {
		UserUpdateResponse response = new UserUpdateResponse();
		if(this.userDao.findById(request.getUser().getId()).get()==null) {
			response.setMessage("There is no such user!");
			response.setSuccess(false);
			return response;
		}
		request.getUser().setUpdateDate(new Date());
		this.userDao.save(request.getUser());
		return response;
	}

	@Override
	public GetUsersByNormalizedNameResponse getByNormalizedName(GetByNormalizedNameRequest request) {
		
		GetUsersByNormalizedNameResponse response = new GetUsersByNormalizedNameResponse();
		List<UserDto> userList = new ArrayList<>();
		
		List<User> users = this.userDao.findAll();
		for(User user: users) {
			if(user.getNormalizedName().equals(request.getNormalizedName())) {
				userList.add(this.convertUserToUserDto(user));
			}
		}
		response.setUserList(userList);
		response.setMessage("Users with same normalized name are listed.");
		response.setSuccess(true);
		return response;
		
	}

	@Override
	public User getByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetUserByIdResponse getById(GetUserByIdRequest request) {
		GetUserByIdResponse response = new GetUserByIdResponse();
		try {
		User user = this.userDao.findById(request.getUser_id()).get();
		UserDto userDto = this.convertUserToUserDto(user);
		response.setUser(userDto);
		response.setMessage("User found");
		response.setSuccess(true);
		}
		catch(NoSuchElementException e){
			response.setMessage("User not found");
			response.setSuccess(false);
		}
		
		return response;
	}
	

	@Override
	public UserDeleteResponse delete(DeleteUserRequest request) {

		UserDeleteResponse response = new UserDeleteResponse();
		
		User user = this.userDao.findById(request.getUserId()).get();
		if(user==null) {
			response.setMessage("No such user to delete");
			response.setSuccess(false);
			return response;
		}
		user.setStatusId(6);
		this.userDao.save(user);
		
		response.setMessage("User deleted successfully.");
		response.setSuccess(true);
		return response;
		
	}

	private void setIds(User user) {
		UUID uuid = UUID.randomUUID();
		user.setId(uuid);
		user.setCreatedBy(uuid);
		user.setUpdatedBy(uuid);
	}


	@Override
	public UserDto convertUserToUserDto(User user) {
		
		UserDto userDto = new UserDto();
		userDto.setCreateDate(user.getCreateDate());
		userDto.setCreatedBy(this.userDao.findById(user.getCreatedBy()).get().getFullName());
		userDto.setEmail(user.getEmail());
		UserStatusEnum status = UserStatusEnum.getValue(user.getStatusId());
		if(status != null) {userDto.setStatus(status.name());}
		userDto.setUpdateDate(user.getUpdateDate());
		userDto.setUpdatedBy(this.userDao.findById(user.getUpdatedBy()).get().getFullName());
		userDto.setNormalizedName(user.getNormalizedName());
		userDto.setFullName(user.getFullName());
		userDto.setOrganizationList(user.getOrganizationList());
		userDto.setInvitationList(user.getInvitationList());
		return userDto;
	}

	private CreateUserResponse validateInput(CreateUserRequest request) {
		
		CreateUserResponse response = new CreateUserResponse(true,"user added successfully");
		if(!StringUtils.isAlphaSpace(request.getFullName())) {
			response.setMessage("Full Name can contain only letters!!!");
			response.setSuccess(false);
			return response;
		}
		User user = this.userDao.getUserByEmail(request.getEmail());
		if(user!=null) {
			response.setMessage("There is already a user with that email!!!");
			response.setSuccess(false);
			return response;
		}
		
		@SuppressWarnings("null")
		String email = user.getEmail();
		String regexPattern = "^(.+)@(\\S+)$";
	    ;
	    if(email==null) {
	    	response.setMessage("Email field is required Please enter your email!!!");
			response.setSuccess(false);
			return response;
	    }
		if(!UserManager.patternMatches(email, regexPattern)) {
			response.setMessage("Email form is unsupported!!!");
			response.setSuccess(false);
			return response;
		}
		
		return response;
	}

	private User setUserParameters(CreateUserRequest request) {
		
		User user = new User();
		this.setIds(user);
		user.setCreateDate(new Date());
		user.setEmail(request.getEmail());
		user.setFullName(request.getFullName());
		user.setStatusId(request.getStatusId());
		user.setNormalizedName(this.generateNormalizedName(request.getFullName()));
		
		return user;
	}
	
	public static boolean patternMatches(String emailAddress, String regexPattern) {
	    return Pattern.compile(regexPattern)
	      .matcher(emailAddress)
	      .matches();
	}
	
	private String generateNormalizedName(String fullName) {
		return Normalizer.normalize(fullName, Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "")
				.replaceAll(" ", "")
				.toLowerCase();
		
	}

	private List<UserDto> generateUserDto(List<User> users){
		List<UserDto> userDtos = new ArrayList<>();
		for(User user: users) {
			UserDto userDto = this.convertUserToUserDto(user);
			userDtos.add(userDto);
		}
		return userDtos;
	}


	@Override
	
	public CreateMembershipResponse createMembership(CreateMembershipRequest request) {
		
		CreateMembershipResponse response = new CreateMembershipResponse();
		User user = this.userDao.findById(request.getUserId()).get();
		Organization organization = this.organizationDao.findById(request.getOrganizationId()).get();
		
		user.getOrganizationList().add(organization);
		this.userDao.save(user);
		
		response.setMessage("Membership created");
		response.setSuccess(true);
		return response;
	}

}
