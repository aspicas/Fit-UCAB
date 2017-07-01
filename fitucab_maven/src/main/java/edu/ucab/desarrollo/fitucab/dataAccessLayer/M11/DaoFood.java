package edu.ucab.desarrollo.fitucab.dataAccessLayer.M11;

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.Exceptions.AddException;
import edu.ucab.desarrollo.fitucab.common.Exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Food;
import edu.ucab.desarrollo.fitucab.common.entities.Sql;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by charbel on 24/06/2017.
 */
public class DaoFood extends Dao implements IDaoFood {

    //Atributo que se utiliza para transformar a formado JSON las consultas.
    private Gson gson = new Gson();
    private String response;
    private ArrayList<Food> jsonArray;
    String username;
    String calorie;

    public DaoFood() {  }

    @Override
    public void Create(Entity e) {

    }

    @Override
    public Entity create(Entity e) throws AddException {
        return null;
    }

    @Override
    public Entity read(Entity e) {
        return null;
    }

    @Override
    public Entity update(Entity e) {
        return null;
    }



    @Override
    public String getFoodPer(Entity e) throws SQLException, BdConnectException {

        String query = "SELECT * FROM m11_get_alimentos_person(?)";
        jsonArray = new ArrayList<>();
        Food food = (Food) e;
        username= String.valueOf(food.get_id());

        Connection conn = Dao.getBdConnect();
        PreparedStatement stm = conn.prepareStatement(query);
        stm.setString(1, username);
        ResultSet rs = stm.executeQuery();

        while(rs.next()){
            jsonArray.add(new Food());
            jsonArray.get(jsonArray.size()).set_foodName(rs.getString("nombre_comida"));
            jsonArray.get(jsonArray.size() - 1).set_foodWeight(rs.getString("peso_comida"));
            jsonArray.get(jsonArray.size() - 1).set_foodCalorie(rs.getString("calorias_comida"));
            jsonArray.get(jsonArray.size() - 1).set_id(rs.getInt("id_alimento"));
        }




        return  gson.toJson(jsonArray);
    }

    @Override
    public String getFoodAll(Entity e) throws SQLException, BdConnectException {

        Food food = (Food) e;
        username= String.valueOf(food.get_id());

        String query = "select * from m11_get_todos_alimentos(?)";
        jsonArray = new ArrayList<>();

        Connection conn = Dao.getBdConnect();
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, username);
        ResultSet rs = st.executeQuery();

        while(rs.next()) {
            jsonArray.add(new Food());
            jsonArray.get(jsonArray.size() - 1).set_foodName(rs.getString("nombre_comida"));
            // revisar string
            jsonArray.get(jsonArray.size() - 1).set_foodCalorie(rs.getString("calorias_comida"));
            jsonArray.get(jsonArray.size() - 1).set_foodWeight(rs.getString("peso_comida"));
            jsonArray.get(jsonArray.size() - 1).set_id(rs.getInt("id_alimento"));
        }

        response = gson.toJson(jsonArray);
        return response;
    }

    @Override
    public String getSugge(Entity e) throws SQLException, BdConnectException {

        Food food = (Food) e;
        String query = "select * from m11_get_alimentos_sugerencia(?, ?)";
        jsonArray = new ArrayList<>();
        username = String.valueOf(food.get_id());
        calorie = food.get_foodCalorie();
        Connection conn = Dao.getBdConnect();
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, username);
        st.setInt(2, Integer.parseInt(calorie));
        ResultSet rs = st.executeQuery();

        while(rs.next()) {
            jsonArray.add(new Food());
            jsonArray.get(jsonArray.size() - 1).set_foodName(rs.getString("nombre_comida"));
            // revisar string
            jsonArray.get(jsonArray.size() - 1).set_foodCalorie(rs.getString("calorias_comida"));
            jsonArray.get(jsonArray.size() - 1).set_foodWeight(rs.getString("peso_comida"));
            jsonArray.get(jsonArray.size() - 1).set_id(rs.getInt("id_alimento"));
        }

        response = gson.toJson(jsonArray);



        return response;
    }



    @Override
    public String getFoodAuto(Entity e) throws SQLException, BdConnectException {

        Food food = (Food) e;
        String query = "select * from m11_get_todos_alimentos_autocompletar(?)";
        jsonArray = new ArrayList<>();
        username  = String.valueOf(food.get_id());
        Connection conn = Dao.getBdConnect();
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, username);
        ResultSet rs = st.executeQuery();

        while(rs.next()) {

            Food aux = (Food) EntityFactory.CreateFood();
            aux.set_id(rs.getInt("id_alimento"));
            aux.set_foodName(rs.getString("nombre_comida"));
            aux.set_foodCalorie(rs.getString("calorias_comida"));
            aux.set_foodWeight(rs.getString("peso_comida"));
            jsonArray.add(aux);

 /*
            jsonArray.add((Food) EntityFactory.CreateFood());

            jsonArray.get(jsonArray.size() - 1).set_id(rs.getInt("id_alimento"));
            jsonArray.get(jsonArray.size() - 1).set_foodName(rs.getString("nombre_comida"));
            // revisar string
            jsonArray.get(jsonArray.size() - 1).set_foodCalorie(rs.getString("calorias_comida"));
            jsonArray.get(jsonArray.size() - 1).set_foodWeight(rs.getString("peso_comida"));
  */

        }
            response = gson.toJson(jsonArray);



        return response;
    }




    @Override
    public String DeletPerFood(Entity e) throws SQLException, BdConnectException {
        Map<String, String> response = new HashMap<String, String>();
        String query = "select * from m11_elimina_alimento_person(?, ?)";
        Food food = (Food) e;


        Connection conn = Dao.getBdConnect();
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, food.get_foodName());
        st.setInt(2, food.get_id());
        st.executeQuery();
        response.put("data", "Se elimino el alimento de forma exitosa");

        return gson.toJson(response);
    }




    @Override
    public String updatePerso(Entity e) throws SQLException, BdConnectException {
        Map<String, String> response = new HashMap<String, String>();
        Food food = (Food) e;
        String query = "select * from m11_act_alimento_person(?, ?, ?, ?)";


        Connection conn = Dao.getBdConnect();
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, food.get_foodName());
        st.setInt(2, Integer.parseInt(food.get_foodWeight()));
        st.setInt(3, Integer.parseInt(food.get_foodCalorie()));
        st.setInt(4, food.get_id());
        st.executeQuery();
        response.put("data", "Se actualizo el alimento de forma exitosa");


        return gson.toJson(response);
    }



    @Override
    public String InsertarAlimen(Entity e) throws SQLException, BdConnectException {
        Map<String, String> response = new HashMap<String, String>();

        Food food = (Food) e;
        String query = "select m11_inserta_alim_person(? , ?, ?, ?, ?)";

        Connection conn = Dao.getBdConnect();
        PreparedStatement st = conn.prepareStatement(query);

        st.setString(1, food.get_foodName());
        st.setInt(2, Integer.parseInt(food.get_foodCalorie()));
        st.setInt(3, Integer.parseInt(food.get_foodWeight()));
        st.setBoolean(4 , food.get_foodPersonalized());
        st.setInt(5, food.get_id());

        st.executeQuery();
        response.put("data", "Se insertaron los alimentos de forma exitosa");

        return gson.toJson(response);
    }


}
