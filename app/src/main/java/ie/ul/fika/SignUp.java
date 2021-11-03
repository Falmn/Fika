package ie.ul.fika;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.regex.Pattern;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {
    private TextInputLayout textInputName;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputPassword1;
    private TextInputLayout textInputPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        textInputName = findViewById(R.id.UserName);
        textInputEmail = findViewById(R.id.email);
        textInputPassword1 = findViewById(R.id.password1);
        textInputPassword2 = findViewById(R.id.password2);

    }
    public void confirmInput(View view) {
        boolean validation = validateName() && validateEmail() && validatePassword1() && validatePassword2();

        if (validation) {

        }

    }
    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString();

        if (emailInput.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textInputEmail.setError("Please enter a valid email address");
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }


    }
    private boolean validateName() {

        String nameInput = textInputName.getEditText().getText().toString();
        if (nameInput.isEmpty()) {
            textInputName.setError("field can't be empty");
            return false;
        } else {
            textInputName.setError(null);
            return true;
        }
    }

    private boolean validatePassword1(){
        String passwordInput = textInputPassword1.getEditText().getText().toString();

        if (passwordInput.isEmpty()){
            textInputPassword1.setError("field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()){
            textInputPassword1.setError("Password too weak");
            return false;
        } else {
            textInputPassword1.setError(null);
            return true;
        }
    }

    private boolean validatePassword2(){
        String passwordInput = textInputPassword2.getEditText().getText().toString();

        if (passwordInput.isEmpty()){
            textInputPassword2.setError("field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()){
            textInputPassword2.setError("Password too weak");
            return false;
        } else {
            textInputPassword2.setError(null);
            return true;
        }
    }

    private boolean passwordequal(){
        String passwordcheck = textInputPassword1.getEditText().getText().toString();
        String passwordcheck2 = text
        if ()
    }


}