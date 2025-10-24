package co.edu.uco.HumanSolution.service;

import co.edu.uco.HumanSolution.model.Usuario;
import co.edu.uco.HumanSolution.repository.UsuarioRepository;
import co.edu.uco.HumanSolution.exception.UsuarioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Service
public class UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Registra un nuevo usuario en la base de datos
     */
    public Usuario registrarUsuario(Usuario usuario) throws UsuarioException {
        logger.info("Intentando registrar usuario: {}", usuario.getCorreo());

        // Validar datos antes de guardar
        usuario.validar();

        // Verificar duplicados
        Optional<Usuario> usuarioExistentePorCorreo = usuarioRepository.findByCorreo(usuario.getCorreo());
        if (usuarioExistentePorCorreo.isPresent()) {
            throw new UsuarioException("El correo ya está registrado", "correo");
        }

        Optional<Usuario> usuarioExistentePorDocumento = usuarioRepository.findByNumeroDocumento(usuario.getNumeroDocumento());
        if (usuarioExistentePorDocumento.isPresent()) {
            throw new UsuarioException("El número de documento ya está registrado", "numeroDocumento");
        }

        // Guardar usuario
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        logger.info("Usuario registrado exitosamente con ID: {}", usuarioGuardado.getId());

        return usuarioGuardado;
    }
}
