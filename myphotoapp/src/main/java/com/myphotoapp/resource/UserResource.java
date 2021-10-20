package com.myphotoapp.resource;


import com.google.firebase.auth.FirebaseAuthException;
import com.myphotoapp.exception.InvalidIdToken;
import com.myphotoapp.exception.RestrictedInfoException;
import com.myphotoapp.model.FirebaseUser;
import com.myphotoapp.model.User;
import com.myphotoapp.service.FirebaseService;
import com.myphotoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private FirebaseService firebaseService;

    // Get all users
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    // get current user data
    @GetMapping("/current")
    @CrossOrigin
    public User getById(@RequestHeader("idToken") String idToken) throws IOException, FirebaseAuthException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);

        return userService.getById(idToken, firebaseUser.getEmail());
    }

    // Add new user
    @PostMapping
    public User saveUser(@RequestBody @Valid User user, @RequestHeader(name = "idToken") String idToken) throws IOException, FirebaseAuthException, InvalidIdToken {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        if(firebaseUser != null){
            return userService.saveUser(user);
        }
        if(idToken == null){
            throw new InvalidIdToken();
        }
        return null;
    }

    // Update User
    @PutMapping
    public User updateUser(@RequestBody User user, @RequestHeader(name = "idToken") String idToken) throws IOException, FirebaseAuthException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        if(firebaseUser != null){
            return userService.updateUser(user);
        }
        return null;
    }

    // Delete User
    @DeleteMapping
    public void deleteUser(@RequestParam ("id") String id, @RequestHeader(name = "idToken") String idToken) throws IOException, FirebaseAuthException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        if(firebaseUser != null){
            userService.deleteUser(id);
        }
    }

    // Find user by ID
    @GetMapping("/find-user-by-id")
    public User getUserById(@RequestParam("id") String id){
        return userService.findUserById(id);
    }


    // Find user by name
    @GetMapping("/find-user-by-name")
    public List<User> getUserByName(@RequestParam("name") String name) throws RestrictedInfoException {
        if(name.equalsIgnoreCase("root")){
            throw new RestrictedInfoException();
        }
        return userService.findAllByName(name);
    }

    // get user by email
    @GetMapping("/find-user-by-email")
    public List<User> getUserByEmail(@RequestParam("email") String email) {
        return userService.findAllByEmail(email);
    }

    // Register new user
    @PostMapping("/register")
    public User registerUser(@RequestBody @Valid User user) {
        return userService.registerUser(user);
    }

    // Invalid IdToken Custom Exception
    @ExceptionHandler(InvalidIdToken.class)
    public String invalidIdToken(InvalidIdToken ex){
        return ex.getMessage();
    }

    // User Profile Photo
    @PutMapping("me/profilePhoto")
    @CrossOrigin
    public User updateProfilePhotoUrl(@RequestParam("profilePhotoUrl") String profilePhotoUrl, @RequestHeader("idToken") String idToken) throws IOException, FirebaseAuthException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);

        return userService.updateProfilePhotoUrl(profilePhotoUrl, firebaseUser.getEmail());
    }
}
