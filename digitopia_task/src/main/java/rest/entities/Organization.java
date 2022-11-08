package rest.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="organization")
public class Organization {
	
	@Id
	@Column(name="organization_id")
	private UUID id;
	
	@Column(name="create_date")
	private Date createDate;
	
	@Column(name="update_date")
	private Date updateDate;
	
	@Column(name="created_by")
	private UUID createdBy;
	
	@Column(name="updated_by")
	private UUID updatedBy;
	
	@Column(name="organization_name")
	private String organizationName;
	
	@Column(name="normalized_name")
	private String normalizedName;
	
	@Column(name="registry_number")
	private String registryNumber;
	
	@Column(name="email")
	private String email;
	
	@Column(name="founded_year")
	private int foundedYear;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="company_size")
	private int companySize;
	
	@ManyToMany(fetch = FetchType.EAGER,mappedBy = "organizationList")
	@JsonIgnore
	private List<User> userList = new ArrayList<>();
	
	
}
