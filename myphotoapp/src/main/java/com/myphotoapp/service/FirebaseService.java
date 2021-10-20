package com.myphotoapp.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.myphotoapp.model.FirebaseUser;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FirebaseService<idToken> {

    public FirebaseUser authenticate(String idToken) throws IOException, FirebaseAuthException {

        String uid = FirebaseAuth.getInstance().verifyIdToken(idToken).getUid();
        String email = FirebaseAuth.getInstance().verifyIdToken(idToken).getEmail();

        return new FirebaseUser(uid, "Milan Pavasiya",email);
    }
}
