package co.edu.uco.nose.business.business.validator;

import co.edu.uco.nose.business.business.rule.generics.StringValueIsPresentRule;
import co.edu.uco.nose.business.domain.UserDomain;
import org.springframework.validation.Validator;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import java.io.IOException;

public  class ValidateDataUserConsistencyForRegisterNewInformation implements Validator<UserDomain> {

    @Override
    public void validate(UserDomain data) {


    }

    private void validateEmptyData(final UserDomain data) {

        private String idNumber;
        private String firstName;
        private String secondName;
        private String firstSurname;
        private String secondSurname;

        StringValueIsPresentRule.executeRule(data.getIdNumber(), "Numero de identificacion", true);
        StringValueIsPresentRule.executeRule(data.getFirstName(), "Primer Nombre", true);
        StringValueIsPresentRule.executeRule(data.getFirstSurname(), "Primer apellido", true);
    }
}