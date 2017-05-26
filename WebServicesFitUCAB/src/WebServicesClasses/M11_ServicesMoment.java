package WebServicesClasses;


import Domain.Moment;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import javax.ws.rs.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by jaorr on 22/05/17.
 */

@Path("M11_Moment")
public class M11_ServicesMoment {

    private Connection conn = bdConnect();
    private Gson gson = new Gson();
    private String respuesta;
    private ArrayList<Moment> arregloJson;


    /**
     * Funcion que devulve los momentos del dia registrados en la BD
     * @return Lista de momentos en formato json
     */
    @GET
    @Produces("application/json")
    public String obtenerMomentos() {

        String query = "Select * from get_momentos()";
        arregloJson = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            int i = 0;
            while(rs.next()) {
                arregloJson.add(new Moment());
                arregloJson.get(rs.getRow() - 1).set_description(rs.getString("momento"));
                arregloJson.get(rs.getRow() - 1).set_id(rs.getInt("momento_id"));
            }
            respuesta = gson.toJson(arregloJson);

        } catch (SQLException e) {
            e.printStackTrace();
            respuesta =  e.getMessage();
        }

        finally {
            bdClose();
            return respuesta;
        }
    }

    //esto no va a aqui , se puso momentaneamente.
    public Connection bdConnect()
    {
        Connection conn = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/FitUcabDB";
            conn = DriverManager.getConnection(url,"fitucab", "fitucab");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            bdClose();
            System.exit(1);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            bdClose();
            System.exit(2);
        }
        return conn;
    }

    public int bdClose(){
        try{
            conn.close();
        }

        catch (java.sql.SQLException e) {
            System.out.println("fallo cerrar");
            System.exit( 0);
        }
        return 1;
    }
}