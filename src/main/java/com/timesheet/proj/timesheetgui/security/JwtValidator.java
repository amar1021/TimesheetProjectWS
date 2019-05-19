package com.timesheet.proj.timesheetgui.security;

import org.springframework.stereotype.Component;

import com.timesheet.proj.timesheetgui.entity.User;
import com.timesheet.proj.timesheetgui.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {


    private String secret = "timesheet";

    public User validate(String token) {

    	User user = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            user = new User();
            
            user.setUsername(body.getSubject());
            user.setId(Long.parseLong((String) body.get("userId")));
            user.setRole((String) body.get("role"));
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return user;
    }
}
