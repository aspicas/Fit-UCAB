package edu.ucab.desarrollo.fitucab.domainLogicLayer.M11;

import edu.ucab.desarrollo.fitucab.common.Exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.Exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.Exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

import java.sql.SQLException;

/**
 * Created by JoseA2R on 2/7/17.
 */
public class InsertDiet extends Command {

    Entity Diet;
    public String Respuesta;
    @Override
    public void execute() throws ListAllException, ListByIdException, NoSuchMethodException, SQLException, BdConnectException {

    }
}
