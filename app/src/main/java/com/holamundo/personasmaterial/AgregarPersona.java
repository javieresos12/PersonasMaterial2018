package com.holamundo.personasmaterial;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Random;

public class AgregarPersona extends AppCompatActivity {
    private EditText txtCedula, txtNombre, txtApellido;
    private Spinner cmbsexo;
    private ArrayAdapter<String> adapter;
    private String opc[];
    private ArrayList<Integer> fotos;
    private ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_persona);

        txtCedula = findViewById(R.id.txtCedula);
        txtApellido = findViewById(R.id.txtApellido);
        txtNombre = findViewById(R.id.txtNombre);
        cmbsexo = findViewById(R.id.cmbSexo);
        foto = findViewById(R.id.foto);

        fotos = new ArrayList<>();
        fotos.add(R.drawable.images);
        fotos.add(R.drawable.images2);
        fotos.add(R.drawable.images3);

        opc = getResources().getStringArray(R.array.sexo);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,opc);
        cmbsexo.setAdapter(adapter);
    }

    public int fotoAleatoria(){
        int fotoSeleccionada;
        Random r = new Random();
        fotoSeleccionada = r.nextInt(this.fotos.size());
        return fotos.get(fotoSeleccionada);
    }

    public void guardar(View v){
        String ced, nomb, appell, foto, id;
        int  sexo;
        //foto = this.fotoAleatoria();

        id = Datos.getId();
        foto = id+".jpg";
        ced = txtCedula.getText().toString();
        nomb = txtNombre.getText().toString();
        appell = txtApellido.getText().toString();
        sexo = cmbsexo.getSelectedItemPosition();
        Persona p = new Persona(id,foto,ced,nomb,appell,sexo);
        p.guardar();
        limpiar();
        Snackbar.make(v,getResources().getString(R.string.guardado_exitoso),Snackbar.LENGTH_SHORT)
                .show();

    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(AgregarPersona.this,Principal.class);
        startActivity(i);
    }

    public void limpiar(){
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        cmbsexo.setSelection(0);
        txtCedula.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }


    public void limpiar(View v){
        limpiar();
    }

    public void seleccionar_foto(View v){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,
                "Seleccionar Foto"),1);
    }
}
