package co.edu.uco.nose.business.business.validator;

import co.edu.uco.nose.business.domain.UserDomain;

import javax.xml.validation.Validator;

public class ValidateDataUserConsistencyForRegisterNewInformation implements Validator<UserDomain> {

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
}
