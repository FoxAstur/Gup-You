package com.ctbarbanza.gupyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ctbarbanza.gupyou.models.User;
import com.orhanobut.hawk.Hawk;

public class PerfilUsuarioActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_perfil_usuario);

        if (Hawk.contains("user")){
            user = Hawk.get("user");
        }

        DbController.get(user.uid);


        updateProfileData();

        final Button btnVerPerfil = (Button) findViewById(R.id.btn_ver_perfil);
        btnVerPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),VerUsuarioActivity.class);
                startActivityForResult(intent,0);
            }
        });

    }

    private void updateProfileData() {

        User nUser = new User();
        nUser.uid       = this.user.uid;
        nUser.instagram = "INSTAGRAM-01";
        nUser.facebook  = "FACEBOOK-01";
        nUser.google    = this.user.google;
        nUser.img       = this.user.img;
        nUser.name      = this.user.name;
        nUser.nick      = this.user.nick;

        DbController.saveUser(nUser);

    }
}
