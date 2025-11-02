package co.edu.uco.nose.business.business.validator;

import co.edu.uco.nose.business.domain.UserDomain;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.validation.Validator;
import java.io.IOException;

public  class ValidateDataUserConsistencyForRegisterNewInformation implements Validator<UserDomain> {

    @Override
    public void validate(final UserDomain data){
        //valid empty data
        //valid data length
        //valid data format
        //valid data valid range

    }

    private void validateEmptyData(final UserDomain data){
        private String idNumber;
        private String firstName;
        private String secondName;
        private String firstSurname;
        private String secondSurname;

    }

    @Override
    public void reset() {

    }

    @Override
    public void validate(Source source, Result result) throws SAXException, IOException {

    }

    @Override
    public void setErrorHandler(ErrorHandler errorHandler) {

    }

    @Override
    public ErrorHandler getErrorHandler() {
        return null;
    }

    @Override
    public void setResourceResolver(LSResourceResolver resourceResolver) {

    }

    @Override
    public LSResourceResolver getResourceResolver() {
        return null;
    }
}
