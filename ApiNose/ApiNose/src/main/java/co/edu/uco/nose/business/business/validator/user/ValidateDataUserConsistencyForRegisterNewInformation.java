package co.edu.uco.nose.business.business.validator.user;

import co.edu.uco.nose.business.business.rule.generics.StringFormatValueIsValidRule;
import co.edu.uco.nose.business.business.rule.generics.StringLengthValueIsValidRule;
import co.edu.uco.nose.business.business.rule.generics.StringRangeValueIsValidRule;
import co.edu.uco.nose.business.business.rule.generics.StringValueIsPresentRule;
import co.edu.uco.nose.business.business.validator.Validator;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscuting.helper.TextHelper;

public class ValidateDataUserConsistencyForRegisterNewInformation implements Validator {

    private static final Validator instance = new ValidateDataUserConsistencyForRegisterNewInformation();

    // ==================== CONSTANTES ====================
    private static final String FIELD_ID_NUMBER = "Numero de identificacion";
    private static final String FIELD_FIRST_NAME = "Primer nombre";
    private static final String FIELD_SECOND_NAME = "Segundo nombre";
    private static final String FIELD_FIRST_SURNAME = "Primer apellido";
    private static final String FIELD_SECOND_SURNAME = "Segundo apellido";

    private ValidateDataUserConsistencyForRegisterNewInformation() {
        super();
    }

    public static void executeValidation(final Object... data) {
        instance.validate(data);
    }

    @Override
    public void validate(final Object... data) {
        var userDomainData = (UserDomain) data[0];

        // Valid empty data
        validateEmptyData(userDomainData);

        // Valid data length
        validateDataLength(userDomainData);

        // Valid data format
        validateDataFormat(userDomainData);

        // Valid data valid range
        validateDataRange(userDomainData);
    }

    // ==================== VALIDACIONES ====================

    private void validateEmptyData(final UserDomain data) {
        StringValueIsPresentRule.executeRule(data.getIdNumber(), FIELD_ID_NUMBER, true);
        StringValueIsPresentRule.executeRule(data.getFirstName(), FIELD_FIRST_NAME, true);
        StringValueIsPresentRule.executeRule(data.getFirstSurname(), FIELD_FIRST_SURNAME, true);
    }

    private void validateDataLength(final UserDomain data) {
        StringLengthValueIsValidRule.executeRule(data.getIdNumber(), FIELD_ID_NUMBER, 1, 50, true);
        StringLengthValueIsValidRule.executeRule(data.getFirstName(), FIELD_FIRST_NAME, 1, 100, true);

        if (!TextHelper.isEmpty(data.getSecondName())) {
            StringLengthValueIsValidRule.executeRule(data.getSecondName(), FIELD_SECOND_NAME, 1, 100, true);
        }

        StringLengthValueIsValidRule.executeRule(data.getFirstSurname(), FIELD_FIRST_SURNAME, 1, 100, true);

        if (!TextHelper.isEmpty(data.getSecondSurname())) {
            StringLengthValueIsValidRule.executeRule(data.getSecondSurname(), FIELD_SECOND_SURNAME, 1, 100, true);
        }
    }

    private void validateDataFormat(final UserDomain data) {
        StringFormatValueIsValidRule.executeRule(data.getFirstName(), FIELD_FIRST_NAME);
        StringFormatValueIsValidRule.executeRule(data.getFirstSurname(), FIELD_FIRST_SURNAME);
    }

    private void validateDataRange(final UserDomain data) {
        StringRangeValueIsValidRule.executeRule(data.getFirstName(), FIELD_FIRST_NAME, "A", "Z");
        StringRangeValueIsValidRule.executeRule(data.getFirstSurname(), FIELD_FIRST_SURNAME, "A", "Z");
    }
}
