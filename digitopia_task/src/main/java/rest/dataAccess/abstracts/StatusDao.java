package rest.dataAccess.abstracts;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rest.entities.Status;

@Repository
public interface StatusDao extends JpaRepository<Status, Integer> {
	
	@Query("select s from Status s where s.id = :id")
	Status findStatusById(@Param("id") int id);
}
