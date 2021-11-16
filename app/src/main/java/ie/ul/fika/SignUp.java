package ie.ul.fika;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUp extends AppCompatActivity {
    private EditText username;
    private EditText fullname;
    private EditText email;
    private EditText password;
    private Button register;
    private TextView loginUser;

    ProgressBar progressBar;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.username);
        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        loginUser = findViewById(R.id.login_user);

        fStore = FirebaseFirestore.getInstance();

        //If user already a member, sends them to loginActivity
        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this, Login.class));
            }
        });

        // If user already exist, it sends them to main activity
        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtUsername = username.getText().toString().trim();
                String txtFullName = fullname.getText().toString();
                String txtEmail = email.getText().toString().trim();
                String txtPassword = password.getText().toString().trim();


                if(TextUtils.isEmpty(txtUsername)){
                    username.setError("Username is Required");
                    return;
                }
                if(TextUtils.isEmpty(txtFullName)){
                    fullname.setError("Name is Required");
                    return;
                }

                if(TextUtils.isEmpty(txtEmail)){
                    email.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(txtPassword)){
                    password.setError("Password is Required");
                return;
                }
                if (txtPassword.length() < 6) {
                    password.setError("Password too short, minimum 6 characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);


                // Register user to firebase

                fAuth.createUserWithEmailAndPassword(txtEmail,txtPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SignUp.this, "User created", Toast.LENGTH_SHORT);
                           // creating a method to store users in a collection via their userID.
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            // Can have more data here, lie birthday and so on.
                            user.put("username",txtUsername);
                            user.put("fName",txtFullName);
                            user.put("email",txtEmail);


                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "onSuccess: User profile created for" +userID);
                                    }

                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(SignUp.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }

        });

    }
}