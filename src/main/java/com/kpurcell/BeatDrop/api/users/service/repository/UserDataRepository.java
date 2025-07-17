package com.kpurcell.BeatDrop.api.users.service.repository;

import com.kpurcell.BeatDrop.api.users.service.data.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDataRepository extends CrudRepository<User, Long> {
}
