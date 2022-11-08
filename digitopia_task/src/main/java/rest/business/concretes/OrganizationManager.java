package rest.business.concretes;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rest.business.Dtos.OrganizationDto;
import rest.business.Dtos.UserDto;
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
import rest.dataAccess.abstracts.OrganizationDao;
import rest.dataAccess.abstracts.UserDao;
import rest.entities.Organization;
import rest.entities.User;

@Service
@Transactional
public class OrganizationManager implements OrganizationService {

	private OrganizationDao organizationDao;
	@Lazy
	private UserDao userDao;
	public OrganizationManager(OrganizationDao organizationDao,UserDao userDao) {
		this.organizationDao = organizationDao;
		this.userDao = userDao;
	}
	
	@Override
	public GetAllOrganizationsResponse getAll() {
		List<Organization> organizations = this.organizationDao.findAll();
		GetAllOrganizationsResponse response = new GetAllOrganizationsResponse();
		response.setOrganizationList(this.generateOrganizationDto(organizations));
		response.setMessage("Organizations listed");
		response.setSuccess(true);
		
		return response;
	}

	@Override
	public CreateOrganizationResponse add(CreateOrganizationRequest request) {
		
		CreateOrganizationResponse response = this.validateInput(request);
		if(response.isSuccess()) {
			Organization organization = this.setOrganizationParameters(request);
			this.organizationDao.save(organization);
		}
		return response;
	}

	@Override
	public OrganizationUpdateResponse update(OrganizationUpdateRequest request) {
		
		OrganizationUpdateResponse response = new OrganizationUpdateResponse();
		
		if(this.organizationDao.findById(request.getOrganization().getId()).get()==null) {
			response.setMessage("There is no such organization!");
			response.setSuccess(false);
			return response;
		}
		
		request.getOrganization().setUpdateDate(new Date());
		this.organizationDao.save(request.getOrganization());
		
		response.setMessage("Organization updated successfully");
		response.setSuccess(true);
		return response;
	}

	@Override
	public DeleteOrganizationResponse delete(DeleteOrganizationRequest request) {
		
		DeleteOrganizationResponse response = new DeleteOrganizationResponse();
		Organization organization = this.organizationDao.findById(request.getOrganizationId()).get();
		if(organization==null) {
			response.setMessage("No such organization!");
			response.setSuccess(false);
			return response;
		}
		
		this.organizationDao.delete(organization);
		
		response.setMessage("Organization deleted successfully!");
		response.setSuccess(true);
		return response;
	}

	@Override
	public GetOrganizationByIdResponse getById(GetOrganizationByIdRequest request) {
		GetOrganizationByIdResponse response = new GetOrganizationByIdResponse();
		
		try {
			Organization organization = this.organizationDao.findById(request.getOrganization_id()).get();
			OrganizationDto organizationDto = this.convertOrganizationToOrganizationDto(organization);
			
			response.setMessage("Organization found");
			response.setSuccess(true);
			response.setOrganization(organizationDto);
		}
		catch(NoSuchElementException e) {
			response.setMessage("Organization not found");
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public GetOrganizationsByNormalizedNameResponse getByNormalizedName(GetByNormalizedNameRequest request) {
		GetOrganizationsByNormalizedNameResponse response = new GetOrganizationsByNormalizedNameResponse();
		
		List<Organization> organizations = this.organizationDao.findAll();
		List<OrganizationDto> organizationDtos = new ArrayList<>();
		
		for(Organization organization: organizations) {
			if(organization.getNormalizedName().equals(request.getNormalizedName())) {
				organizationDtos.add(this.convertOrganizationToOrganizationDto(organization));
			}
		}
		response.setMessage("Organizations with same normalized name are listed.");
		response.setSuccess(true);
		response.setOrganizationList(organizationDtos);
		return response;
	}

	
	private void setId(Organization organization) {
		UUID uuid = UUID.randomUUID();
		organization.setId(uuid);
		//organization.setCreatedBy(uuid);
		//organization.setUpdatedBy(uuid);
	}

	@Override
	public List<Organization> getOrganizationList(UUID user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrganizationDto convertOrganizationToOrganizationDto(Organization organization) {
		
		OrganizationDto orgDto = new OrganizationDto();
		
		orgDto.setCreateDate(organization.getCreateDate());
		orgDto.setUpdateDate(organization.getUpdateDate());
		orgDto.setEmail(organization.getEmail());
		orgDto.setRegistryNumber(organization.getRegistryNumber());
		orgDto.setCompanySize(organization.getCompanySize());
		orgDto.setFoundedYear(organization.getFoundedYear());
		orgDto.setPhone(organization.getPhone());
		orgDto.setUserList(organization.getUserList());
		orgDto.setCreatedBy(this.userDao.findById(organization.getCreatedBy()).get().getFullName());
		orgDto.setUpdatedBy(this.userDao.findById(organization.getUpdatedBy()).get().getFullName());
		orgDto.setNormalizedName(organization.getNormalizedName());
		orgDto.setUserList(organization.getUserList());
		orgDto.setOrganizationName(organization.getOrganizationName());
		
			
		return orgDto;
	}
	
	private CreateOrganizationResponse validateInput(CreateOrganizationRequest request) {
		
		CreateOrganizationResponse response = new CreateOrganizationResponse(true,"Organization added successfully.");
		if(!StringUtils.isAlphanumeric(request.getOrganizationName())) {
			System.out.println(request.getOrganizationName());
			response.setMessage("Organization Name can only be alphanumeric!!!");
			response.setSuccess(false);
			return response;
		}
		if(!StringUtils.isAlphanumeric(request.getRegistryNumber())) {
			response.setMessage("Registry Number can only be alphanumeric!!!");
			response.setSuccess(false);
			return response;
		}
		
		Organization org = this.organizationDao.getOrganizationByEmail(request.getEmail());
		if(org!=null) {
			response.setMessage("There is already an organization with that email!!!");
			response.setSuccess(false);
			return response;
		}
		
		Organization org1 = this.organizationDao.getByRegistryNumber(request.getRegistryNumber());
		if(org1!=null) {
			response.setMessage("There is already an organization with that registry number!!!");
			response.setSuccess(false);
			return response;
		}
		
		return response;
	}
	
	private Organization setOrganizationParameters(CreateOrganizationRequest request) {
		
		Organization org = new Organization();
		org.setId(UUID.randomUUID());
		org.setCreateDate(new Date());
		org.setEmail(request.getEmail());
		org.setCreatedBy(request.getCreatedBy());
		org.setUpdatedBy(request.getUpdatedBy());
		org.setOrganizationName(request.getOrganizationName());
		org.setNormalizedName(this.generateNormalizedName(request.getOrganizationName()));
		org.setCompanySize(request.getCompanySize());
		org.setRegistryNumber(request.getRegistryNumber());
		org.setFoundedYear(request.getFoundedYear());
		org.setPhone(request.getPhone());
		
		return org;
	}
	
	private String generateNormalizedName(String orgName) {
		return Normalizer.normalize(orgName, Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "")
				.replaceAll(" ", "")
				.toLowerCase();
		
	}

	private List<OrganizationDto> generateOrganizationDto(List<Organization> organizations){
		List<OrganizationDto> organizationDtos = new ArrayList<>();
		for(Organization organization: organizations) {
			OrganizationDto organizationDto  = this.convertOrganizationToOrganizationDto(organization);
			organizationDtos.add(organizationDto);
		}
		return organizationDtos;
	}
}
