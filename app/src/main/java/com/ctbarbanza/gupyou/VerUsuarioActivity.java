package com.ctbarbanza.gupyou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ctbarbanza.gupyou.mockup.UserController;
import com.ctbarbanza.gupyou.models.User;

import java.util.ArrayList;
import java.util.List;


public class VerUsuarioActivity extends AppCompatActivity {

    private Context ctx;

    UserController controller;
    List<User> usuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_usuario);

        //muestraUsuarios();
        verUsuario();
    }

    private void muestraUsuarios() {
        for (User u : usuarios) {
            Log.d("USER", u.toString());


        }

    }


    public void verUsuario() {

        final EditText nombreUsuario = findViewById(R.id.profile_name);
       final ImageView imagenUsuario = findViewById(R.id.img_perfil);
       final TextView valoraciones = findViewById(R.id.txt_Valoraciones);
       final TextView comentarios = findViewById(R.id.txt_Comentarios);

        controller = UserController.init();

        usuarios = controller.getAllUsers();

        for (User u : usuarios) {

            Log.d("USER", u.toString());
            if (u.uid == null) {

                comentarios.setText((CharSequence) "No hay comentarios para este Usuario");
                valoraciones.setText((CharSequence) "No hay comentarios para este Usuario");
                System.out.println("Usuario no encontrado");
            } else if (u.uid.equalsIgnoreCase(String.valueOf(nombreUsuario))) {


                comentarios.setText((CharSequence) controller.getComentarios(nombreUsuario.toString()));
                valoraciones.setText((CharSequence) controller.getValoraciones(nombreUsuario.toString()));
                imagenUsuario.setImageIcon(Icon.createWithContentUri(u.img));




            }

        }


    }
}
