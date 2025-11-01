package co.edu.uco.HumanSolution.constants;

/**
 * Enumerador simplificado para mensajes de negocio usados por las excepciones.
 * Se implementan el código, el mensaje y el campo afectado, con getters.
 */
public enum MessagesEnum {
    // Mensajes de validación de usuario
    USUARIO_PO_01("USUARIO-PO-01", "Campo obligatorio faltante", "campo"),
    USUARIO_PO_01_NOMBRE("USUARIO-PO-01-N", "Nombre es obligatorio", "nombre"),
    USUARIO_PO_01_APELLIDO("USUARIO-PO-01-A", "Apellido es obligatorio", "apellido"),
    USUARIO_PO_01_DOCUMENTO("USUARIO-PO-01-D", "Número de documento es obligatorio", "documento"),
    USUARIO_PO_01_CORREO("USUARIO-PO-01-C", "Correo es obligatorio", "correo"),
    USUARIO_PO_01_ROL("USUARIO-PO-01-R", "Rol es obligatorio", "rol"),
    USUARIO_PO_02("USUARIO-PO-02", "Documento inválido", "documento"),
    USUARIO_PO_03("USUARIO-PO-03", "Documento duplicado", "documento"),
    USUARIO_PO_04("USUARIO-PO-04", "Correo duplicado", "correo"),
    USUARIO_PO_05("USUARIO-PO-05", "Formato de correo inválido", "correo"),
    USUARIO_PO_06("USUARIO-PO-06", "Rol inválido", "rol"),

    // Mensajes de base de datos
    DB_CONEXION_EXITOSA("DB-00", "Conexión a la base de datos establecida", "database"),
    DB_ERROR_DRIVER("DB-01", "Driver de PostgreSQL no encontrado", "database"),
    DB_ERROR_CONEXION("DB-02", "Error al establecer la conexión a la base de datos", "database"),
    DB_ERROR_CONSULTA("DB-03", "Error al consultar la base de datos", "database"),
    DB_ERROR_INSERCION("DB-04", "Error al insertar en la base de datos", "database"),
    DB_CONEXION_CERRADA("DB-05", "Conexión a la base de datos cerrada", "database"),

    // Mensajes de operaciones
    USUARIO_NO_ENCONTRADO("USUARIO-OP-01", "Usuario no encontrado", "documento"),
    USUARIO_REGISTRADO_EXITOSAMENTE("USUARIO-OP-02", "Usuario registrado exitosamente", "usuario");

    private final String codigo;
    private final String mensaje;
    private final String campoAfectado;

    MessagesEnum(String codigo, String mensaje, String campoAfectado) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.campoAfectado = campoAfectado;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getCampoAfectado() {
        return campoAfectado;
    }

    public String getMensajeCompleto() {
        return String.format("%s: %s", codigo, mensaje);
    }
}
