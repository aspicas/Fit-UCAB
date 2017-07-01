package edu.ucab.desarrollo.fitucab.domainLogicLayer.M11;

import edu.ucab.desarrollo.fitucab.common.Exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.Exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M11.IDaoFood;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;

import java.sql.SQLException;

/**
 * Created by charbel on 30/06/2017.
 */
public class getFoodAutoCommand extends Command {

    Entity _food;
    public String Respuesta;

    public getFoodAutoCommand(Entity food) {
        _food = food;
    }

    @Override
    public void execute() throws ListAllException, ListByIdException, NoSuchMethodException, SQLException
    {

        IDaoFood Daofood = DaoFactory.iniciarDaoFood();
        Respuesta = Daofood.getFoodAuto(_food);


    }
}
