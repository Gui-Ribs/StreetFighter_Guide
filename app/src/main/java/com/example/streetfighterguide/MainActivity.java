package com.example.streetfighterguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LoginActivity login;
    TextView nam;
    SharedPreferences preferences;
    DrawerLayout drawerLayout;

    private static final String SHARED_MAIN = "Kmain";
    private static final String SHARED_NAME = "Kname";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nam = findViewById(R.id.naP);
        drawerLayout = findViewById(R.id.drawer_layout);

        preferences = getSharedPreferences(SHARED_MAIN , MODE_PRIVATE);

        String none = preferences.getString(SHARED_NAME, null);

        nam.setText(none);


    }

    public void ClickMenu(View view) {
        openDR(drawerLayout);
    }

    public static void openDR(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo (View view) {
        closeDR(drawerLayout);
    }

    public static void closeDR(DrawerLayout drawerLayout) {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

    }

    public void ClickClose(View view) {
        closeDR(drawerLayout);
    }

    public void ClickHome(View view) {
        closeDR(drawerLayout);
        recreate();
    }

    public void ClickMapa(View view) {
        closeDR(drawerLayout);
        redirectUrl("https://streetfighter.fandom.com/wiki/Street_Fighter_Wiki");
    }

    public void ClickEventos(View view) {
        closeDR(drawerLayout);
        redirectUrl("https://arena.rtp.pt/street-fighter/");
    }

    public void ClickNotificacao(View view) {
        closeDR(drawerLayout);
        gotoActivity(this,NotificationsActivity.class);
    }

    public void ClickCompartilhar(View view) {
        closeDR(drawerLayout);
        shareOptions();
    }

    public void ClickComprar(View view) {
        redirectUrl("https://streetfightermotoparts.com.br/produtos/acessorios/?v=7ace2f45dad9");
    }

    public void redirectUrl(String urlR) {
        Uri url = Uri.parse(urlR);
        startActivity(new Intent(Intent.ACTION_VIEW, url));
    }

    public boolean shareOptions() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Compartilhe a aplicação para mais pessoas");
        startActivity(Intent.createChooser(intent, "Compartilhar"));
        return true;
    }

    public static void gotoActivity (Activity activity, Class Class) {
        Intent intent = new Intent(activity, Class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }


}