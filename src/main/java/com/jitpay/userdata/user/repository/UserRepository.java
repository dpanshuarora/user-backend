package com.jitpay.userdata.user.repository;

import com.jitpay.userdata.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
