package com.example.demo.Controller;



import com.example.demo.Model.Users;
import com.example.demo.Services.RegisterUserService;
import com.example.demo.Services.UsersService;
import com.example.demo.jwt.JwtUtils;
import com.example.demo.payload.JwtResponse;
import com.example.demo.payload.LoginRequest;
import com.nimbusds.oauth2.sdk.Response;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class LoginController {
    @Autowired
    private final RegisterUserService registrationService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private final JwtUtils jwtTokenUtil;

    @Autowired
    private UsersService userDetailsService;

    //ajouter un utilisateur
    @PreAuthorize("hasAnyRole('ROLE_INGENIEUR')")
    @PostMapping(path = "/ajoutUser")
    public String register(@RequestBody Users request) {
        return registrationService.register(request);
    }

    //login
    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody LoginRequest loginRequest) throws Exception {

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(loginRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        Users user = (Users) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt,
                user.getId(),
                user.getEmail()));
    }

    /*@RequestMapping(path = "/logout", method = RequestMethod.POST)
    public Response logout(@Context HttpSession session) {

        session.removeAttribute("user");
        session.invalidate();
        if (session != null) {
            session = null;
        }
        return Response.status(200).entity(Status.OK.getReasonPhrase()).build();

    }*/
    //try
    @GetMapping("/username")
    public String userAccess() {
        System.out.println("hello");
        return "User Content.";
    }

    //try
    @GetMapping("/all")
    public String allAccess() {
        System.out.println("hello");
        return "Public Content.";
    }


}


