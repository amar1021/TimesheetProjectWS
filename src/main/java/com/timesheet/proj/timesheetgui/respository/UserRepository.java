package com.timesheet.proj.timesheetgui.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.timesheet.proj.timesheetgui.entity.User;

@RestResource(exported = false)
public interface UserRepository extends JpaRepository<User, String> {

}