/*
package ie.ul.fika;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Feed extends AppCompatActivity {
ImageView newPost, profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        newPost = findViewById(R.id.newPost);
        profile = findViewById(R.id.profile);

        // Sends to profile activity
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Profile.class));
            }
        });
        //For adding a new post.

    }
}
*/
