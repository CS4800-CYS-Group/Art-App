package edu.cpp.CYS;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cpp.CYS.model.U;

//allows you to perform  database operations like inserting new users, updating existing ones, deleting users and querying for user data
public interface UserRepository extends JpaRepository<U, Long> {

    U findByUsername(String username);

}
