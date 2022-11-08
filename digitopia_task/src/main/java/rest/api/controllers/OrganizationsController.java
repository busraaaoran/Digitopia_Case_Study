package rest.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rest.business.abstracts.OrganizationService;
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

@RestController
@RequestMapping("/api/organizations")
public class OrganizationsController {
	
	@Autowired
	private OrganizationService organizationService;
	
	@PostMapping("/add")
	public CreateOrganizationResponse add(@RequestBody CreateOrganizationRequest request) {
		return this.organizationService.add(request);
	}
	
	@GetMapping("/getall")
	public GetAllOrganizationsResponse getAll() {
		return this.organizationService.getAll();
	}
	
	@PostMapping("/getbyid")
	public GetOrganizationByIdResponse getOrganizationById(@RequestBody GetOrganizationByIdRequest request) {
		return this.organizationService.getById(request);
	}
	
	@PutMapping("/update")
	public OrganizationUpdateResponse update(@RequestBody OrganizationUpdateRequest request) {
		return this.organizationService.update(request);
	}
	
	@PostMapping("/delete")
	public DeleteOrganizationResponse delete(@RequestBody DeleteOrganizationRequest request) {
		return this.organizationService.delete(request);
	}
	
	@PostMapping("/getOrganizationsByNormalizedName")
	public GetOrganizationsByNormalizedNameResponse getOrganizationsByNormalizedName(@RequestBody GetByNormalizedNameRequest request) {
		return this.organizationService.getByNormalizedName(request);
	}
	
}
