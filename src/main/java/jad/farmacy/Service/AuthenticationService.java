package jad.farmacy.Service;


import jad.farmacy.Entity.Role;
import jad.farmacy.Entity.RoleEnum;
import jad.farmacy.Entity.Store;
import jad.farmacy.Entity.User;
import jad.farmacy.Repository.RoleRepository;
import jad.farmacy.Repository.StoreRepository;
import jad.farmacy.Repository.UserRepository;
import jad.farmacy.dto.LoginUserDto;
import jad.farmacy.dto.RegisterUserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;
    private final StoreRepository storeRepository;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository,
            StoreRepository storeRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository=roleRepository;
        this.storeRepository=storeRepository;
    }

    public User signup(RegisterUserDto input) {

        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);
        Optional<Store> optionalStore = storeRepository.findById(input.getStoreID());
        if (optionalRole.isEmpty()) {
            return null;
        }
        if(optionalStore.isEmpty()){
            return null;
        }

        User user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        // Encrypt the password before saving
        String encodedPassword = passwordEncoder.encode(input.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(optionalRole.get());

        user.setStore(optionalStore.get());
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getEmail(),
                            input.getPassword()
                    )
            );
        } catch (AuthenticationException e) {

            throw new BadCredentialsException("Invalid username or password", e);
        }

        // Retrieve the user from the database
        return userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
