package rest.api.controllers;

import org.springframework.web.bind.annotation.RestController;

import rest.business.abstracts.UserService;
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
import rest.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/users")
public class UsersController {
	
	private UserService userService;
	
	@Autowired
	public UsersController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/add")
	public CreateUserResponse add(@RequestBody CreateUserRequest request) {
		return this.userService.add(request);
	}
	
	@GetMapping("/getall")
	public GetAllUserListResponse getAll() {
		return this.userService.getAll();
	}
	
	@PostMapping("/getbyid")
	public GetUserByIdResponse getUserById(@RequestBody GetUserByIdRequest request) {
		return this.userService.getById(request);
	}
	
	@PostMapping("/createMembership")
	public CreateMembershipResponse createMembership(@RequestBody CreateMembershipRequest request) {
		return this.userService.createMembership(request);
	}
	
	@PutMapping("/update")
	public UserUpdateResponse update(@RequestBody UserUpdateRequest request) {
		return this.userService.update(request);
	}
	
	@PostMapping("/delete")
	public UserDeleteResponse delete(@RequestBody DeleteUserRequest request) {
		return this.userService.delete(request);
	}
	
	@PostMapping("/usersByNormalizedName")
	public GetUsersByNormalizedNameResponse getUsersByNormalizedName(@RequestBody GetByNormalizedNameRequest request) {
		return this.userService.getByNormalizedName(request);
	}
	
}
