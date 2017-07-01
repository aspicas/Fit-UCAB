package edu.ucab.desarrollo.fitucab.domainLogicLayer.M09;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import org.slf4j.LoggerFactory;

/**
 * Comando para traer la puntuacion de todos los retos
 * @author David Garcia, Juan Mendez, Mario Salazar
 * @version 2.0
 */
public class ScoreCommand extends Command{
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(ScoreCommand.class);
    private Entity _score;

    public ScoreCommand(Entity score) {
        _score = score;
    }

    //TODO: Falta execute
    public void execute() throws NoSuchMethodException {
        try{

        }
        catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error);
            logger.error("Error: ", error);
        }
    }

    public Entity Return() {
        return null;
    }
}
