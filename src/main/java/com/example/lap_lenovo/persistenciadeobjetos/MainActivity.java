package com.example.lap_lenovo.persistenciadeobjetos;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn;
    ListView lv;
    static ArrayList<Contacto> lista=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button)findViewById(R.id.button);
        lv=(ListView)findViewById(R.id.listView);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("contacto",lista.get(i));
                intent.putExtra("posicion",i);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,CrearContactoActivity.class);
                startActivity(intent);
            }
        });

        cargarArchivo();
        actualizarLista();

    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarArchivo();
        actualizarLista();
    }

    public void cargarArchivo(){
        try{
            File file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"obj.obj");
            if(hasExternalStorage()&&file.exists()){
            ObjectInputStream stream=new ObjectInputStream(new FileInputStream(file));
            lista= (ArrayList<Contacto>) stream.readObject();
            stream.close();
            }

        }catch(Exception e){}

    }

    public void actualizarLista(){
        ArrayList<String> nombres=new ArrayList<>();
        for (Contacto c:lista){
            nombres.add(c.nombre);
        }
        ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,nombres);
        lv.setAdapter(adapter);
    }

    public boolean hasExternalStorage(){
        String status= Environment.getExternalStorageState();
        if(status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
            return false;
    }

}
