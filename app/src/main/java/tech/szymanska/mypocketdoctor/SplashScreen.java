package tech.szymanska.mypocketdoctor;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import tech.szymanska.mypocketdoctor.home.Home;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread timer = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException iEx) {
                iEx.printStackTrace();
            } finally {
                SplashScreen.this.startActivity(new Intent(SplashScreen.this, Home.class));
            }
        });
        timer.start();
    }
}
