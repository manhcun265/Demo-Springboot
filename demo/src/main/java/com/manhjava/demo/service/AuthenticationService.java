package com.manhjava.demo.service;

import com.manhjava.demo.dto.request.AuthenticationRequest;
import com.manhjava.demo.dto.request.IntrospectRequest;
import com.manhjava.demo.dto.request.LogoutRequest;
import com.manhjava.demo.dto.request.RefreshRequest;
import com.manhjava.demo.dto.response.AuthenticationResponse;
import com.manhjava.demo.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);

    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;

    void logout(LogoutRequest request) throws ParseException, JOSEException;

    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;
}