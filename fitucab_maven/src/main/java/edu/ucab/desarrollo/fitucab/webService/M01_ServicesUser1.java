package edu.ucab.desarrollo.fitucab.webService;

/**
 * Created by elberg on 28/06/17.
 */

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.User;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M01.DaoUser;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CheckUserCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CreateUserCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.RecoverPasswordCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Clase de Servicios Web del Modulo 01
 */
@Path("/M01_ServicesUser1")
public class M01_ServicesUser1 {
    Entity _response;

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(M01_ServicesUser1.class);

    Gson gson = new Gson();

    public M01_ServicesUser1() {}

    /**
     * Metodo que es llamado a traves del web service para consultar un usuario
     * existente en la base de datos
     * @return el usuario con los datos que trae la consulta
     */
    @GET
    @Path("/login_user")
    @Produces("application/json")
    public String getUser(@QueryParam("username") String username,
                          @QueryParam("password") String password)
    {
        Entity userObject = EntityFactory.createUser(username,password);
        CheckUserCommand cmd = CommandsFactory.instanciateCheckUserCmd(userObject);
        try {
            _response = cmd.run();
            return gson.toJson(_response);
        }
        catch (Exception e){
            return null ;
        }
    }


    /**
     * Metodo para registrar al usuario
     * @param username
     * @param password
     * @param email
     * @param sex
     * @param phone
     * @param birthdate
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
                             @QueryParam("weight") int weight,
                             @QueryParam("height") int height) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        java.sql.Date sqlDate = null;

        try {
            date = sdf.parse(birthdate);
            sqlDate = new java.sql.Date(date.getTime());

        }
        catch (java.text.ParseException e){

        }


        Entity createUserObject = EntityFactory.createUser(username, password, email,
                                                 sex, phone, sqlDate, weight, height);

        System.out.print("Debug: "+"EL USUARIO ES " + username);
        System.out.print("Debug: "+"EL PASS ES " + password);
        System.out.print("Debug: "+"EL EMAIL ES " + email);
        System.out.print("Debug: "+"EL Sex ES " + sex);
        System.out.print("Debug: "+"EL PHONE ES " + phone);
        System.out.print("Debug: "+"EL BIRTHDATE ES " + sqlDate);
        System.out.print("Debug: "+"EL WHEIGHT ES " + weight);
        System.out.print("Debug: "+"EL HEIGHT ES " + height);


        CreateUserCommand cmd = CommandsFactory.instanciateCreateUserCmd(createUserObject);

        try
        {
            //TODO: PENDIENTE - EL RETURN ES UN STRING, HABRA QUE VER EN LA APLICACION LO QUE NECESITA REALMENTE
            _response = cmd.run();
            User us = (User) _response;
            return gson.toJson( createUserObject );
        }
        catch ( Exception e )
        {
            return gson.toJson( null );
        }

    }

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
        //Duda sobre si es necesario crear el objeto recupUserPswObject
        
        Entity recupUserPswObject = EntityFactory.createUser(email);
        RecoverPasswordCommand cmd = CommandsFactory.instanciateRecoverPasswordCmd(email);

        try
        {
            cmd.execute();
            return gson.toJson( true );
        }
        catch ( Exception e )
        {
            return gson.toJson( false );
        }


    }

}
