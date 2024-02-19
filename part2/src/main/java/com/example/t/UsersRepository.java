package com.example.t;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<Users,Long> {
  boolean findByFirstNameAndSecondNameAndLastName(String firstName, String secondName, String lastName);
}
