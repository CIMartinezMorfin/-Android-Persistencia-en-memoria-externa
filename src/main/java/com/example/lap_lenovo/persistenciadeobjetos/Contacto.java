package com.example.lap_lenovo.persistenciadeobjetos;

import java.io.Serializable;

/**
 * Created by Lap-Lenovo on 13/10/2017.
 */

public class Contacto implements Serializable{

    String nombre,apellido,numero,correo;
    Contacto(String nombre,String...datos){
        this.nombre=nombre;
        apellido=datos[0];
        numero=datos[1];
        correo=datos[2];
    }


}
