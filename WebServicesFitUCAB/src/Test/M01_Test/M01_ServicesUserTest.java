
import Domain.Sql;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;


public class M01_ServicesUserTest {


    Sql Connn = new Sql();
    ResultSet rs;

    @Before
    public void setUp() throws Exception {

        String insertUserQuery =" SELECT M01_REGISTRAR('"+"dan"+"','"+"dan"+"','"+"dan"+"','"+"dan"+"'" +
                ",'"+"dan"+"','"+"1993-06-11"+"','"+1.0+"','"+1.0+"')";


        try {


            rs =Connn.sql(insertUserQuery);
        }
        catch (Exception e){}

    }

    @After
    public void tearDown() throws Exception {

        String query="SELECT M01_ELIMINARUSER('"+ "dan" +"')";
        String query1="SELECT M01_ELIMINARUSER('"+ "dan1" +"')";

        try {


            ResultSet rs =Connn.sql(query);
            ResultSet rs2 = Connn.sql(query1);
        }
        catch (Exception e){}
    }


    @Test
    public void informationUserTest() throws URISyntaxException {


        try {
            URI prueba = new URI("http://localhost:8888/WebServicesFitUCAB_war_exploded/M01_ServicesUser/informationUser?username=dan");

            given().accept(ContentType.JSON).when().get(prueba).then()
                    .assertThat().statusCode(HttpStatus.SC_OK);
            String json = given().accept(ContentType.JSON).when()
                    .get(prueba).thenReturn().body().asString();
            assertNotNull(json);


        }
        catch (URISyntaxException e){

        }
        catch (Exception e){}
    }
    @Test
    public void insertUserTest() throws URISyntaxException {

        try {
            URI prueba = new URI("http://localhost:8888/" +
                    "WebServicesFitUCAB_war_exploded/M01_ServicesUser/" +
                    "insertRegistry?" +
                    "username=dan1" +
                    "&password=dan1" +
                    "&email=dan1" +
                    "&sex=dan1" +
                    "&phone=12a3456" +
                    "&birthdate=12/06/1993" +
                    "&weight=1" +
                    "&height=1");
            given().accept(ContentType.JSON).when().get(prueba).then()
                    .assertThat().statusCode(HttpStatus.SC_OK);
            URI prueba2 = new URI("http://localhost:8888/" +
                    "WebServicesFitUCAB_war_exploded/" +
                    "M01_ServicesUser/" +
                    "deteleUser?username=dan1");
            given().accept(ContentType.JSON).when().get(prueba2).then()
                    .assertThat().statusCode(HttpStatus.SC_OK);

        }
        catch (URISyntaxException e) {
            e.printStackTrace();

        }
        catch (Exception e){

        }
    }
    @Test
    public void deleteUserTest() throws URISyntaxException {
        try {
            URI prueba = new URI("http://localhost:8888/" +
                    "WebServicesFitUCAB_war_exploded/M01_ServicesUser/" +
                    "insertRegistry?" +
                    "username=dan12" +
                    "&password=dan12" +
                    "&email=dan12" +
                    "&sex=dan12" +
                    "&phone=12a3456" +
                    "&birthdate=12/06/1993" +
                    "&weight=1" +
                    "&height=1");
            given().accept(ContentType.JSON).when().get(prueba).then()
                    .assertThat().statusCode(HttpStatus.SC_OK);
            URI prueba2 = new URI("http://localhost:8888/" +
                    "WebServicesFitUCAB_war_exploded/" +
                    "M01_ServicesUser/" +
                    "deteleUser?username=dan12");
            given().accept(ContentType.JSON).when().get(prueba2).then()
                    .assertThat().statusCode(HttpStatus.SC_OK);

        } catch (URISyntaxException e) {
            e.printStackTrace();

        } catch (Exception e) {

        }
    }
    @Test
    public void updateUserTest() throws Exception {
        try {
            URI prueba = new URI("http://localhost:8888/WebServicesFitUCAB_war_exploded/M01_ServicesUser/" +
                    "updateUser?username=dan&password=dan&email=dan&sex=dannnnn&phone=dann&birthdate=1993-06-11");
            Response response = given().accept(ContentType.JSON).when().get(prueba);
            System.out.println(response.asString() );


            String json = given().accept(ContentType.JSON).when()
                    .get(prueba).thenReturn().body().asString();
            int jsonNumero = Integer.parseInt(json);
            assertEquals(1,jsonNumero);


        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        catch (Exception e){

        }
    }


    @Test
    public void getUserTest() throws Exception {
        try {

            URI prueba = new URI("http://localhost:8888/WebServicesFitUCAB_war_exploded/" +
                    "M01_ServicesUser/login_user?username=dan&password=dan");

            given().accept(ContentType.JSON).when().get(prueba).then()
                    .assertThat().statusCode(HttpStatus.SC_OK);
            URI prueba2 = new URI("http://localhost:8888/WebServicesFitUCAB_war_exploded/" +
                    "M01_ServicesUser/login_user?username=dan&password=dannnnn");
            String json = given().accept(ContentType.JSON).when()
                    .get(prueba2).thenReturn().body().asString();
            int noEncontrado = Integer.parseInt(json);
            assertEquals(500,noEncontrado);


        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        catch (Exception e){

        }


    }


        //Para despues
    @Test
    public void testEmailTest() throws Exception {
    }





}