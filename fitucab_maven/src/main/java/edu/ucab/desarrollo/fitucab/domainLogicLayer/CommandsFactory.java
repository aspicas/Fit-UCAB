package edu.ucab.desarrollo.fitucab.domainLogicLayer;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CheckPasswordEmailCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CheckUserCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CreateUserCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.CreateTrainingCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.FillChartCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.LevelUpCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.ScoreCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Fabrica de comandos
 */
public class CommandsFactory {

    // Comandos LOGIN M01
    static public CreateUserCommand instanciateCreateUserCmd(Entity user){
        return new CreateUserCommand(user);
    }
    static public CheckUserCommand instanciateCheckUserCmd(String user, String password){
        return new CheckUserCommand(user, password);
    }
    static public CheckPasswordEmailCommand instanciateCheckPasswordEmailCmd(String email){
        return new CheckPasswordEmailCommand(email);
    }

    // Comandos M06

    static public CreateTrainingCommand instanciateCreateTrainingCmd(Entity training){
        return new CreateTrainingCommand(training);
    }

    static public DeleteTrainingCommand instanciateDeleteTrainingCmd(Entity training){
        return new DeleteTrainingCommand(training);
    }

    static public ShareTrainingCommand instanciateShareTrainingCmd(Entity training) {
        return new ShareTrainingCommand(training);
    }

    static public AddActivitiesToTrainingCommand instanciateAddActivitiesToTrainingCmd(Entity training) {
        return new AddActivitiesToTrainingCommand(training);
    }

    static public RemoveActivitiesFromTrainingCommand instanciateRemoveActivitiesFromTrainingCmd(Entity training) {
        return new RemoveActivitiesFromTrainingCommand(training);
    }

    static public ChangeTrainingNameCommand instanciateChangeTrainingNameCmd(Entity training) {
        return new ChangeTrainingNameCommand(training);
    }

    static public CheckTrainingCommand instanciateCheckTrainingCmd(int trainingId, int userId) {
        return new CheckTrainingCommand(trainingId, userId);
    }

    /**
     * Metodo para instanciar el comando GetAllTraining
     * @param training
     * @return el comando GetAllTraining
     */
    public static Command instanciateGetAllTrainingCmd( Entity training )
    {
        return new GetAllTrainingCommand(training);
    }

    /**
     * Metodo para instanciar el comando GetTrainingDetail
     * @param training
     * @return el comando GetTrainingDetail
     */
    public static Command instanciateGetTrainingDetailCmd (Entity training){
        return new GetTrainingDetailCommand(training);
    }

    //Modulo 9
    static public AchieveChallengeCommand instanciateAchieveChallengeCmd(int id){
        return new AchieveChallengeCommand(id);
    }

    static public ScoreCommand instanciateScoreCmd(int id){
        return new ScoreCommand(id);
    }

    static public FillChartCommand instanciateFillChartCmd(int id){
        return new FillChartCommand(id);
    }

    static public LevelUpCommand instanciateLevelUpCmd(int id){
        return new LevelUpCommand(id);
    }

    static  public List<Entity> getChallenges(){return new ArrayList<Entity>();}
    //Fin Modulo 9


}

