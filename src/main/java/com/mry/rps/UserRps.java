package com.mry.rps;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.mry.entity.User;

public interface UserRps extends JpaSpecificationExecutor<User>, PagingAndSortingRepository<User, Integer> {

	User findByName(String username);
}
