package com.example.lap_lenovo.persistenciadeobjetos;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main2Activity extends AppCompatActivity {


    Button btn, btn2;
    EditText et, et2, et3, et4;
    Contacto contacto;
    int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn = (Button) findViewById(R.id.button13);
        btn2 = (Button) findViewById(R.id.button14);
        et = (EditText) findViewById(R.id.editText15);
        et2 = (EditText) findViewById(R.id.editText16);
        et3 = (EditText) findViewById(R.id.editText17);
        et4 = (EditText) findViewById(R.id.editText18);

        contacto = (Contacto) getIntent().getExtras().getSerializable("contacto");
        posicion = getIntent().getExtras().getInt("posicion");
        Toast.makeText(getApplicationContext(), contacto.nombre, Toast.LENGTH_LONG).show();
        et.setText(contacto.nombre);
        et2.setText(contacto.apellido);
        et3.setText(contacto.numero);
        et4.setText(contacto.correo);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contacto.nombre = et.getText().toString();
                contacto.apellido = et2.getText().toString();
                contacto.numero = et3.getText().toString();
                contacto.correo = et4.getText().toString();
                MainActivity.lista.remove(posicion);
                MainActivity.lista.add(posicion, contacto);
                guardarArchivo();
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.lista.remove(posicion);
                guardarArchivo();
                finish();
            }
        });
    }


    public void guardarArchivo() {
        try {
            if (hasExternalStorage()) {
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "obj.obj");
                ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file, false));
                stream.writeObject(MainActivity.lista);
                stream.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean hasExternalStorage() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}