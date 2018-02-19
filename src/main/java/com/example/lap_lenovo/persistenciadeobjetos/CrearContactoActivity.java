package com.example.lap_lenovo.persistenciadeobjetos;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CrearContactoActivity extends AppCompatActivity {

    Button btn;
    EditText et,et2,et3,et4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_contacto);
        btn=(Button)findViewById(R.id.button2);
        et=(EditText)findViewById(R.id.editText);
        et2=(EditText)findViewById(R.id.editText2);
        et3=(EditText)findViewById(R.id.editText3);
        et4=(EditText)findViewById(R.id.editText4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.lista.add(new Contacto(et.getText().toString(),et2.getText().toString(),
                        et3.getText().toString(),et4.getText().toString()));
                guardarArchivo();
            }
        });
    }

    public void guardarArchivo() {
        try {
            if (hasExternalStorage()) {
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "obj.obj");
                ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file,false));
                stream.writeObject(MainActivity.lista);
                stream.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean hasExternalStorage(){
        String status= Environment.getExternalStorageState();
        if(status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

}
