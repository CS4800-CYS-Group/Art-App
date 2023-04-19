package edu.cpp.CYS;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cpp.CYS.model.Photo;
import edu.cpp.CYS.model.U;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    
   List<Photo> findByUser(U user);
}
