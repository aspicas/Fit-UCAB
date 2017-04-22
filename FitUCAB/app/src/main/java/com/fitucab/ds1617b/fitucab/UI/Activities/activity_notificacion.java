package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.fitucab.ds1617b.fitucab.R;

import java.util.Locale;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class activity_notificacion extends AppCompatActivity {

    String correo;
    String contraseña;
    Session session;
    Configuration config = new Configuration();
    String LOCALE_ESPANOL = "es";
    String LOCALE_ENGLISH = "en";
    Locale locale;
    Button enviar, lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacion);

        correo= "aagilazer@gmail.com";
        contraseña="25216467";

        enviar=(Button)findViewById(R.id.enviar); //obteniendo la id del boton
        enviar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v){

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); //agregando politicas para que se envie el correo
                StrictMode.setThreadPolicy(policy); //agregando la politica

                Properties properties = new Properties(); // Ésta clase es la encargada de almacenar las propiedades de la conexión que vamos a establecer con el servidor de correo Saliente SMTP.
                properties.put("mail.smtp.host","smtp.googlemail.com");//se coloca el servidor de correo electronico
                properties.put("mail.smtp.socketFactory.port","465"); //aqui se agrega el socket para recibir respuesta del servidor de gmail
                properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory"); //seguridad ssl potocolo para qu se envie de forma segurala informacion
                properties.put("mail.smtp.auth","true"); //autenticas
                properties.put("mail.smtp.port","465"); //socket puerto de gmail

                //Autenticar correo:
                try{
                    session= Session.getDefaultInstance(properties, new Authenticator() {
                    @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                           return new PasswordAuthentication(correo,contraseña);
                        }
                    });
                    //Verificar que la sesion no sea nula
                    if(session!= null){
                        MimeMessage message = new MimeMessage(session);//Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(correo)); //Emisor: quien enviara el correo
                        message.setSubject("ESTE ES EL ASUNTO"); //aqui se envia el asunto
                        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("aagilazer_95@hotmail.com"));//Receptor: el correo que le llegara el mensaje  min 11:46
                        message.setContent("hola este es un mensaje", "text/html; charset=utf-8");//Aqui se coloca el mensaje. despues de la coma va el  formato del mensaje

                    //Enviar correo:
                        Transport.send(message);

                    }

                }

                catch (Exception e){
                    e.printStackTrace(); }



            }
        });

        lang = (Button)findViewById(R.id.idioma);

        lang.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        showDialog();
                    }});
    }

    /**
     * Muestra una ventana de dialogo para elegir el nuevo idioma de la aplicacion
     * Cuando se hace clic en uno de los idiomas, se cambia el idioma de la aplicacion
     * y se recarga la actividad para ver los cambios
     * */
    private void showDialog(){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(getResources().getString(R.string.lang_btn));
        //obtiene los idiomas del array de string.xml
        String[] types = getResources().getStringArray(R.array.languages);
        b.setItems(types, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                switch(which){
                    case 0:
                        locale = new Locale("en");
                        config.locale =locale;
                        break;
                    case 1:
                        locale = new Locale("es");
                        config.locale =locale;
                        break;
                }
                getResources().updateConfiguration(config, null);
                Intent refresh = new Intent(activity_notificacion.this, activity_notificacion.class);
                startActivity(refresh);
                finish();
            }

        });
        b.show();
    }
}

