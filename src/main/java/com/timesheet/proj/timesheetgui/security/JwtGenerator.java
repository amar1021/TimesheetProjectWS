package com.timesheet.proj.timesheetgui.security;

import org.springframework.stereotype.Component;

import com.timesheet.proj.timesheetgui.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {
    public String generate(User jwtUser) {

        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUsername());
        claims.put("userName", String.valueOf(jwtUser.getUsername()));
        claims.put("role", jwtUser.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "timesheet")
                .compact();
    }
}
