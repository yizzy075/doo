package co.edu.uco.HumanSolution.exception;

import co.edu.uco.HumanSolution.constants.MessagesEnum;


public class UsuarioException extends Exception {

    private final MessagesEnum messageEnum;

    // Constructor principal: con enum y detalle
    public UsuarioException(MessagesEnum messageEnum, String detalle) {
        super(messageEnum.getMensajeCompleto() + (detalle != null && !detalle.isEmpty() ? " - " + detalle : ""));
        this.messageEnum = messageEnum;
    }

    // Constructor secundario: mensaje libre + campo afectado
    public UsuarioException(String mensaje, String campoAfectado) {
        super(mensaje + (campoAfectado != null && !campoAfectado.isEmpty() ? " - " + campoAfectado : ""));
        this.messageEnum = null; // No se dispone de MessagesEnum para este caso
    }

    public MessagesEnum getMessageEnum() {
        return messageEnum;
    }

    public String getCodigo() {
        return messageEnum != null ? messageEnum.getCodigo() : "GEN-000";
    }

    public String getCampoAfectado() {
        return messageEnum != null ? messageEnum.getCampoAfectado() : null;
    }

    // ============================================
    // EXCEPCIONES ESPECÍFICAS
    // ============================================

    /**
     * Usuario-PO-01: Campo obligatorio faltante
     */
    public static class CampoObligatorioException extends UsuarioException {
        public CampoObligatorioException(MessagesEnum messageEnum) {
            super(messageEnum, messageEnum.getMensaje());
        }

        public CampoObligatorioException(MessagesEnum messageEnum, String detalle) {
            super(messageEnum, detalle);
        }
    }

    /**
     * Usuario-PO-02: Documento inválido
     */
    public static class DocumentoInvalidoException extends UsuarioException {
        public DocumentoInvalidoException() {
            super(MessagesEnum.USUARIO_PO_02, MessagesEnum.USUARIO_PO_02.getMensaje());
        }

        public DocumentoInvalidoException(String detalle) {
            super(MessagesEnum.USUARIO_PO_02, detalle);
        }
    }

    /**
     * Usuario-PO-03: Documento duplicado
     */
    public static class DocumentoDuplicadoException extends UsuarioException {
        public DocumentoDuplicadoException() {
            super(MessagesEnum.USUARIO_PO_03, MessagesEnum.USUARIO_PO_03.getMensaje());
        }

        public DocumentoDuplicadoException(String numeroDocumento) {
            super(MessagesEnum.USUARIO_PO_03, "Documento: " + numeroDocumento);
        }
    }

    /**
     * Usuario-PO-04: Correo duplicado
     */
    public static class CorreoDuplicadoException extends UsuarioException {
        public CorreoDuplicadoException() {
            super(MessagesEnum.USUARIO_PO_04, MessagesEnum.USUARIO_PO_04.getMensaje());
        }

        public CorreoDuplicadoException(String correo) {
            super(MessagesEnum.USUARIO_PO_04, "Correo: " + correo);
        }
    }

    /**
     * Usuario-PO-05: Formato de correo inválido
     */
    public static class FormatoCorreoInvalidoException extends UsuarioException {
        public FormatoCorreoInvalidoException() {
            super(MessagesEnum.USUARIO_PO_05, MessagesEnum.USUARIO_PO_05.getMensaje());
        }

        public FormatoCorreoInvalidoException(String detalle) {
            super(MessagesEnum.USUARIO_PO_05, detalle);
        }
    }

    /**
     * Usuario-PO-06: Rol inválido
     */
    public static class RolInvalidoException extends UsuarioException {
        public RolInvalidoException() {
            super(MessagesEnum.USUARIO_PO_06, MessagesEnum.USUARIO_PO_06.getMensaje());
        }

        public RolInvalidoException(String rol) {
            super(MessagesEnum.USUARIO_PO_06, "Rol proporcionado: " + rol);
        }
    }
}