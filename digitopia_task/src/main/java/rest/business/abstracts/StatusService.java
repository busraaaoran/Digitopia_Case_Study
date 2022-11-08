package rest.business.abstracts;

import java.util.List;
import java.util.UUID;

import rest.entities.Status;

public interface StatusService {
	
	List<Status> getAll();
	
	Status getById(int status_id);
	
	void add(Status status);
	
	void update(Status status);
	
	void delete(UUID status_id);
}
