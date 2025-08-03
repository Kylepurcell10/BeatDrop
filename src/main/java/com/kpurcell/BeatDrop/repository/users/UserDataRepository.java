package com.kpurcell.BeatDrop.repository.users;

import com.kpurcell.BeatDrop.api.users.service.data.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDataRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmailAddress(String emailAddress);
}
