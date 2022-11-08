package rest.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.business.abstracts.StatusService;
import rest.dataAccess.abstracts.StatusDao;
import rest.entities.Status;

@Service
public class StatusManager implements StatusService{

	@Autowired
	private StatusDao statusDao;
	
	public StatusManager(StatusDao statusDao) {
		this.statusDao = statusDao;
	}
	
	@Override
	public List<Status> getAll() {
		List<Status> status = this.statusDao.findAll();
		return status;
	}

	@Override
	public Status getById(int status_id) {
		Status status = this.statusDao.findStatusById(status_id);
		
		return status;
	}

	@Override
	public void add(Status status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Status status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(UUID status_id) {
		// TODO Auto-generated method stub
		
	}


}
