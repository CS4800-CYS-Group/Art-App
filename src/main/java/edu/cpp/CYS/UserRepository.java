package edu.cpp.CYS;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cpp.CYS.model.U;

public interface UserRepository extends JpaRepository<U, Long> {

    U findByEmail(String email);

}
