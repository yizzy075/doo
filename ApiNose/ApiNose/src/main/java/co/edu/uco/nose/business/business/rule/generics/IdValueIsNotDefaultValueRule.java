package co.edu.uco.nose.business.business.rule.generics;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;

import java.util.UUID;

public final class IdValueIsNotDefaultValueRule implements Rule {

    private static final Rule instance = new IdValueIsNotDefaultValueRule();

    // ==================== CONSTANTES ====================
    private static final UUID DEFAULT_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    private static final String FIELD_PREFIX = "El campo ";

    private IdValueIsNotDefaultValueRule() {
        super();
    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(final Object... data) {

        if (ObjectHelper.isNull(data) || data.length < 2) {
            var userMessage = "Ocurrio un problema validando el identificador.";
            var technicalMessage = "No se recibieron los parametros requeridos para ejecutar la regla IdValueIsNotDefaultValueRule.";
            throw NoseException.create(userMessage, technicalMessage);
        }

        final var id = (UUID) data[0];
        final var fieldName = (String) data[1];

        if (ObjectHelper.isNull(id)) {
            var userMessage = FIELD_PREFIX + fieldName + " no puede ser nulo.";
            var technicalMessage = FIELD_PREFIX + fieldName + " es nulo y no se puede validar correctamente en IdValueIsNotDefaultValueRule.";
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (DEFAULT_UUID.equals(id)) {
            var userMessage = FIELD_PREFIX + fieldName + " no puede tener el valor por defecto.";
            var technicalMessage = FIELD_PREFIX + fieldName + " es igual al UUID por defecto (00000000-0000-0000-0000-000000000000).";
            throw NoseException.create(userMessage, technicalMessage);
        }
    }
}
