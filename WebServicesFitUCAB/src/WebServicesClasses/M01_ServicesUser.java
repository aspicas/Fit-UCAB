package WebServicesClasses;

import Domain.Sql;
import Domain.User;
import Domain.Registry;
import com.google.gson.Gson;
import org.postgresql.util.PSQLState;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

//imports para poder hacer el recuperar password
import javax.mail.MessagingException;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
//FIN de imports para poder hacer el recuperar password
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.*;
import java.util.Properties;
//imports para hacer el encrypt y decrypt
import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * Clase de Servicios Web del Modulo 01
 */
@Path("/M01_ServicesUser")
public class M01_ServicesUser {

    private Connection conn = bdConnect();
    private int RESULT_CODE_OK=200;
    private int RESULT_CODE_FAIL=500;
    private static String DEFAULT_ENCODING1="UTF-8";
    Gson gson = new Gson();

    public M01_ServicesUser() {

    }

    /**
     * Funcion encargada de realizar la encriptación de un password
     * @param password El password a ser encriptado
     * @return String encriptado con BASE64
     */
    private static String encryptPassword(@QueryParam("password") String password) {
        //Instanciamos un encriptador BASE64
        BASE64Encoder enc = new BASE64Encoder();
        try {
            //Utilizando la codificacion por defecto (UTF-8) encriptamos el string
            return enc.encode(password.getBytes(DEFAULT_ENCODING1));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * Funcion encargada de realizar la desencriptacion de un password
     * @param password El password a desencriptar
     * @return String con el contenido original antes de ser encriptado
     */
    private static String decryptPassword(String password) {
        //Instanciamos un decodificador de BASE64
        BASE64Decoder dec = new BASE64Decoder();
        try {
            //Realizamos la decodificacion mediante el proceso inverso de la encriptacion
            return new String(dec.decodeBuffer(password), DEFAULT_ENCODING1);
        } catch (IOException e) {
            return null;
        }
    }

    @GET
    @Path("/informationUser")
    @Produces("application/json")
    public String informationUser(@QueryParam("username") String userparam) {
        String query = "SELECT * FROM M01_INFORMACIONUSER('" + userparam + "')";
        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            User user = null;
            while (rs.next()) {
                String username = rs.getString("usuario");
                int id = rs.getInt("id");
                String password = rs.getString("pwd");
                String sexo = rs.getString("sex");
                String phone = rs.getString("phone");
                String email = rs.getString("mail");
                Date birtdate = rs.getDate("birthdate");

                user = new User(id, username, password, email, sexo, phone, birtdate);

            }

            return gson.toJson(user);

        } catch (Exception e) {
            return e.getMessage();
        }

    }

    /**
     * Metodo que es llamado a traves del web service para agregar a la base de datos los parametros recibidos
     *
     * @param username
     * @param password
     * @param email
     * @param sex
     * @param phone
     * @param weight
     * @param height
     * @return
     */
    @GET
    @Path("/insertRegistry")
    @Produces("application/json")
    public String insertUser(@QueryParam("username") String username,
                             @QueryParam("password") String password,
                             @QueryParam("email") String email,
                             @QueryParam("sex") String sex,
                             @QueryParam("phone") String phone,
                             @QueryParam("birthdate") String birthdate,
                             @QueryParam("weight") String weight,
                             @QueryParam("height") String height) {


        password=encryptPassword(password);
        String insertUserQuery = " SELECT * FROM M01_REGISTRAR('" + username + "','" + password + "','" + email + "','" + sex + "'" +
                ",'" + phone + "','" + birthdate + "','" + weight + "','" + height + "')";

        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(insertUserQuery);
            User user = null;
            Registry registry = null;

            int id = 0;
            while (rs.next()) {

                id = rs.getInt("m01_registrar");
                registry = new Registry(Float.parseFloat(weight), Float.parseFloat(height));
                user = new User(id, username, password, email, sex, phone, registry);

            }

            return gson.toJson(user);

        }
        catch (SQLException e) {
            return e.getSQLState();
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    /***
     * Metodo que elimina a un usuario
     * @param userparam
     * @return
     */
    @GET
    @Path("/deteleUser")
    @Produces("application/json")
    public String deleteUser(@QueryParam("username") String userparam){

        String query="SELECT M01_ELIMINARUSER('"+ userparam +"')";


        try{

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            int delete =0;

            while(rs.next()){

                delete = rs.getRow();

            }
            return gson.toJson(delete);
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }


    /**
     * Metodo que realiza cambios en el usuario
     * @param userparam
     * @param password
     * @param email
     * @param sex
     * @param phone
     * @return
     */
    @GET
    @Path("/updateUser")
    @Produces("application/json")
    public String updateUser(@QueryParam("username") String userparam,@QueryParam("password") String password,@QueryParam("email") String email,
    @QueryParam("sex") String sex ,@QueryParam("phone") String phone){

        String query="SELECT M01_MODIFICARUSER('"+userparam+"','"+password+"','"+email+"','"+sex+"'" +
                ",'"+phone+"')";

        try{

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            int update =0;

            while(rs.next()){

                update = rs.getRow();

            }
            return gson.toJson(update);
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }

<<<<<<< HEAD
=======

    /***
     * Metodo que con el email recuperas tu usuario y contraseña
     * @param email
     * @return
     */
    @GET
    @Path("/userView")
    @Produces("application/json")
    public String userOnly(@QueryParam("email") String email)
    {
        String insertUserQuery ="SELECT M01_RECUPERARPWD('" + email + "')";
        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(insertUserQuery);
            User user = null;
            while(rs.next()){

                String username1 = rs.getString("usuario");
                String password =rs.getString("pwd");
                int id =rs.getInt("id");

                user= new User(id,username1,password);

            }
            return gson.toJson(user);
        }
        catch(Exception e) {
            return e.getMessage();
        }

    }

>>>>>>> abdf9049455ff806f986f99ac5800920b4012c17

    /**
     * Metodo que es llamado a traves del web service para consultar un usuario existente en la base de datos
     * @param username
     * @param password
     * @return el usuario con los datos que trae la consulta
     */
    @GET
    @Path("/login_user")
    @Produces("application/json")
    public String getUser(@QueryParam("username") String username, @QueryParam("password") String password)
    {

        password= encryptPassword(password);


        String query="SELECT * FROM M01_INICIARSESION('"+username+"','"+password+"')";

        try{

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            int iniciosesion =0;

            while(rs.next()){

                 iniciosesion = rs.getInt("m01_iniciarsesion");

            }
            if (iniciosesion !=0){

                return gson.toJson(iniciosesion);
            }
            else {

                return gson.toJson(RESULT_CODE_FAIL);
            }
        }
        catch (NullPointerException e){
            return e.getMessage();
        }
        catch (SQLException e){
            String error= e.getSQLState();
            return e.getSQLState();
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Sevicio Web para poder enviar el correo al usuario con su password
     * @return por ahora retorna un String
     */
    @GET
    @Path("/restorePassword")
    @Produces("application/json")
    public String testEmail(@QueryParam("email") String email) {

        String query = "SELECT * FROM M01_RECUPERARPWD('" + email + "')";


        try {
            //Establecemos el usuario que es el correo que cree para hacer el recuperar
            final String username = "ds1617b@gmail.com";
            //la clave
            final String password = "fitucab2017";
            //Estas son las propiedades de seguridad de gmail
            Properties props = new Properties();
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            /*
             * EN ALGUNA PARTE DE AQUI ES DONDE DEBERIA HACER EL CAMBIO DE CLAVE POR
             * ALGUN STRING ALEATORIO Y ENCRIPTADO
             * Y LUEGO ENVIARLE EL STRING SIN ENCRIPTAR AL USUARIO
             */
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            User user = null;
            Boolean validaEmail = false;
            String usernameResult="";
            String passwordResult="";

            while (rs.next()) {
                validaEmail = true;
                usernameResult=rs.getString("usuario");
                passwordResult=rs.getString("password");
            }

            if(validaEmail==true) {

                passwordResult=decryptPassword(passwordResult);

                //Se crea la sesion para autenticar
                Session session = Session.getInstance(props,
                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username, password);
                            }
                        });

                //creamos un objeto MIME
                Message message = new MimeMessage(session);
                //ponemos el remitente
                message.setFrom(new InternetAddress("ds1617b@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        //aqui va el destinatario
                        InternetAddress.parse(email));
                //El tema del correo
                message.setSubject("Recuperar contraseña FitUCAB");
                //El contenido del correo
                message.setText(" Hola FitUcabista! /n" +
                                " tu usuario es:" + usernameResult +
                                "/n y tu clave:" + passwordResult+ "/n" +
                                " Ahora puedes seguir entrenando");
                //Enviamos
                Transport.send(message);
                //Aqui en adelante cualquier tipo de validacion

                return gson.toJson(RESULT_CODE_OK);
            }

            else {
                return gson.toJson(RESULT_CODE_FAIL);
            }


        }
        catch (SQLException e){
            return e.getSQLState();
        }
        catch (MessagingException e) {
            return e.getMessage();
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }


    //esto no va a aqui , se puso momentaneamente.
    public Connection bdConnect()
    {
        Connection conn = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/fitucab";
            conn = DriverManager.getConnection(url,"postgres", "123456");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(2);
        }
        return conn;
    }

}
