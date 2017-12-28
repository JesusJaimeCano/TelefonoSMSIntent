package e.jesus.telefonosmsintent;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    EditText numero, texto;
    Button marcar, enviar, llamarButtonLista, mensajeButtonLista;
    private SQLiteDatabase db;
    private Cursor cursorLlamada;
    private Cursor cursorMensaje;
    private ListAdapter adapterLlamada;
    private ListAdapter adapterMensaje;
    private ListView llamadaListView, mensajeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llamarButtonLista = findViewById(R.id.llamadaButtonLV);



        requestPermissions(new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS}, 43);
        numero = findViewById(R.id.numero);
        marcar = findViewById(R.id.llamar);
        marcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aMarcar = "tel:" + numero.getText().toString();
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(aMarcar)));

            }
        });

        /*
        llamarButtonLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Hey", Toast.LENGTH_SHORT).show();
            }
        });*/

        texto = findViewById(R.id.textoMensjae);
        enviar= findViewById(R.id.enviar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(numero.getText().toString(), null, texto.getText().toString(), null, null);
            }
        });

        llamadaListView = findViewById(R.id.listaLlamadas);
        mensajeListView = findViewById(R.id.listaMensajes);

        db = new DBHelper(this).getWritableDatabase();
        cursorLlamada = db.rawQuery("SELECT _id, numero, fecha, hora FROM llamada", null);
        cursorMensaje = db.rawQuery("SELECT _id, numero, texto, fecha, hora FROM mensaje", null);

        adapterLlamada = new SimpleCursorAdapter(this, R.layout.llamada_row, cursorLlamada, new String[]{"numero", "fecha", "hora"}, new int[]{R.id.numeroLlamadaTV, R.id.fechallamadaTV, R.id.horaLlamadaTV});
        llamadaListView.setAdapter(adapterLlamada);

    }
}
