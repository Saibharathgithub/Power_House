package com.kanerika.powerhouse.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kanerika.powerhouse.Resource.MaildetailsPojo;

@Repository
public interface MaildetailsRepository extends JpaRepository<MaildetailsPojo, String> {

	@Query(value = "SELECT * FROM public.powerhouse  WHERE "
			+ "mail_body LIKE CONCAT('%' , :query , '%')", nativeQuery = true)
	List<MaildetailsPojo> searchDetails(String query);

}
