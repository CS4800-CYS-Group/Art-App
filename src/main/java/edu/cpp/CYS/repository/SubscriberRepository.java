package edu.cpp.CYS.repository;

import edu.cpp.CYS.model.Subscriber;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    List<Subscriber> findByEmail(String email);
    List<Subscriber> findByFirstName(String firstName);
}
