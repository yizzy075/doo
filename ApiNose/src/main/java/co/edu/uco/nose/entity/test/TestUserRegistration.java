package co.edu.uco.nose.entity.test;

import co.edu.uco.nose.business.facede.UserFacade;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.dto.UserDTO;

public class TestUserRegistration {

    public static void main(String[] args) {
        try {
            var user = new UserDTO();

            var facade = new UserFacadeImpl();
            facade.registerNewUserInformation(user);

            System.out.println("Gane el semestre");
        }catch (NoseException e){
            System.out.println(e.getUserMessage());
            System.out.println(e.getTechnicalMessage());
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
