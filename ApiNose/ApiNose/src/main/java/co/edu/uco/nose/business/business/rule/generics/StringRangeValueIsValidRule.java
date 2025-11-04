package co.edu.uco.nose.business.business.rule.generics;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;

public final class StringRangeValueIsValidRule implements Rule {

    private static final Rule instance = new StringRangeValueIsValidRule();

    private StringRangeValueIsValidRule() {
        super();
    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(final Object... data) {

        if (ObjectHelper.isNull(data) || data.length < 4) {
            var userMessage = "Ocurrio un problema validando el rango de texto.";
            var technicalMessage = "No se recibieron los parametros requeridos para ejecutar la regla StringRangeValueIsValidRule.";
            throw NoseException.create(userMessage, technicalMessage);
        }

        final var value = (String) data[0];
        final var fieldName = (String) data[1];
        final var minValue = (String) data[2];
        final var maxValue = (String) data[3];

        if (TextHelper.isEmpty(value)) {
            return; // si está vacío, ya lo valida otra regla (StringValueIsPresentRule)
        }

        if (value.compareToIgnoreCase(minValue) < 0 || value.compareToIgnoreCase(maxValue) > 0) {
            var userMessage = "El campo " + fieldName + " no esta dentro del rango permitido (" + minValue + " - " + maxValue + ").";
            var technicalMessage = "El valor '" + value + "' esta fuera del rango al comparar con '" + minValue + "' y '" + maxValue + "'.";
            throw NoseException.create(userMessage, technicalMessage);
        }
    }
}
