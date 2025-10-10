package co.edu.uco.nose.crosscuting.messagecatalog;

import co.edu.uco.nose.crosscuting.helper.TextHelper;

public enum MessagesEnum {
	USER_ERROR_SQL_CONNECTION_IS_EMPTY("Conexion contra la uente de informacion vacia","La conexion requerida para llevar acabo la operacion contra la fuente de informacion deseada esta vacia. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion."),
    TECHNICAL_ERROR_SQL_CONNECTION_IS_EMPTY("Conexion contra la fuente de informacion deseada nula","La conexion requerida para llevar acabo la operacion contra la base de datos llego nula."),

    USER_ERROR_SQL_CONNECTION_IS_CLOSED("Conexion contra la fuente de informacion cerrada","La conexion requerida para llevar acabo la operacion contra la fuente de informacion deseada esta cerrada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion."),
    TECHNICAL_ERROR_SQL_CONNECTION_IS_CLOSED("Conexion contra la fuente de informacion deseada cerrada","La conexion requerida para llevar acabo la operacion contra la base de datos esta cerrada."),

    USER_ERROR_SQL_CONNECTION_IS_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS("Problema inesperado validadndo el estado de la conexion contra la fuente de datos deseada","Se ha presentado un problema inesperado tratando de validar el estado de la conexion requerida para llevar acabo la operacion contra la fuente de informacion deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion."),
    TECHNICAL_ERROR_SQL_CONNECTION_IS_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS("Error inesperado validando si la conexion contra la base de datos estaba abierta ","Se presento un error de tipo SQLEXCEPTION al valdar si la conexion contra base de datos estaba o no abierta. Por favor valide la consola de errores para revisar con detalle el problema presentado.");


    private String title;
	private String content;

    private MessagesEnum(final String title, final String content) {
        setTitle(title);
        setContent(content);
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(final String title) {
        this.title = TextHelper.getDefaultWithTrim(title);
    }

    public String getContent() {
        return content;
    }

    private void setContent(final String content) {
        this.content = TextHelper.getDefaultWithTrim(content);
    }
}
