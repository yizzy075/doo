package co.edu.uco.nose.business.business.rule.generics;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;

public final class StringFormatValueIsValidRule implements Rule {

    private static final Rule instance = new StringFormatValueIsValidRule();

    private StringFormatValueIsValidRule() {
        super();
    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(final Object... data) {

        if (ObjectHelper.isNull(data) || data.length < 2) {
            var userMessage = "Ocurrio un problema validando el formato del texto.";
            var technicalMessage = "No se recibieron los parametros requeridos para ejecutar la regla StringFormatValueIsValidRule.";
            throw NoseException.create(userMessage, technicalMessage);
        }

        final var value = (String) data[0];
        final var fieldName = (String) data[1];

        // si está vacío no valida formato, ya lo maneja StringValueIsPresentRule
        if (TextHelper.isEmpty(value)) {
            return;
        }

        // aca podés ajustar el regex segun el tipo de campo
        // este permite solo letras (incluyendo acentos y ñ) y espacios
        final var regex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$";

        if (!value.matches(regex)) {
            var userMessage = "El campo " + fieldName + " contiene caracteres no validos.";
            var technicalMessage = "El valor '" + value + "' no cumple el formato esperado (solo letras y espacios).";
            throw NoseException.create(userMessage, technicalMessage);
        }
    }
}
