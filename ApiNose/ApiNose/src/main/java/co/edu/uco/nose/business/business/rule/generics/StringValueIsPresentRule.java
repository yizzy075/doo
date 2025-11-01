package co.edu.uco.nose.business.business.rule.generics;
import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;

public class StringValueIsPresentRule implements Rule {
    @Override
    public void execute(final Object... data) {
        if (ObjectHelper.isNull(data)) {
            var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operacion deseada....";
            var technicalMessage = "No se recibieron los parametros requeridos para ejecutar la regla stringValueIsPresentRule";
            throw NoseException.create(userMessage, technicalMessage);

        }

        if (data.length < 3) {
            var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operacion deseada....";
            var technicalMessage = "Se requerian tres parametros y llego una cantidad menor a esta requeridos para ejecutar la regla StringValueIsPresentRule";
            throw NoseException.create(userMessage, technicalMessage);

        }

        var stringData = (String) data[0];
        var dataName = (String) data[1];
        var mustApplyTrim = (boolean) data[2];
    }
}