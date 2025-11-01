package co.edu.uco.nose.business.business.rule.generics;
import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;

public class StringValueIsPresentRule implements Rule {
    @Override
    public void execute(Object... data) {
        if (ObjectHelper.isNull(data)) {
            var userMessage = "No se ha presentado un problema inesperado tratando de llevar a cabo la operacion deseada....";
            var technicalMessage = "No se recibieron los parametros requeridos para ejecutar la regla StringValueIsPresentRule";
            throw NoseException.create(userMessage, technicalMessage);
        }
        if (data.length < 2) {
            var userMessage = "se ha presentado un problema tratanddo de llevar acabo la operacion deseada....";
            var technicalMessage = " se recibieron dos parametros y llego una cantidad menos a esta requeridos para ejecutar la regla StringValueIsPresentRule";
            throw NoseException.create(userMessage, technicalMessage);
        }
        if(data.length<3) {
            var userMessage = "se ha presentado un problema tratanddo de llevar acabo la operacion deseada....";
            var technicalMessage = " se recibieron dos parametros y llego una cantidad menos a esta requeridos para ejecutar la regla StringValueIsPresentRule";
            throw NoseException.create(userMessage, technicalMessage);

        }
        var stringData = (String) data[0];
        var dataName = (String) data[1];
        boolean mustApplyTrim = (boolean) data[2];

        if(mustApplyTrim) ? TextHelper.isEmptyWithTrim(stringData): TextHelper.isEmptyWithTrim(dataName); {
            var userMessage = "";
            var technicalMessage = " se recibieron dos parametros y llego una cantidad menos a esta requeridos para ejecutar la regla StringValueIsPresentRule";
            throw NoseException.create(userMessage, technicalMessage);
    }

}
