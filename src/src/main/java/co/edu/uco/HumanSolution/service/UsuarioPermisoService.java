package co.edu.uco.HumanSolution.service;

import co.edu.uco.HumanSolution.model.UsuarioPermisoEntity;
import co.edu.uco.HumanSolution.model.Usuario;
import co.edu.uco.HumanSolution.repository.UsuarioPermisoRepository;
import co.edu.uco.HumanSolution.repository.ContratoRepository;
import co.edu.uco.HumanSolution.repository.EstadoSolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioPermisoService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioPermisoService.class);

    @Autowired
    private UsuarioPermisoRepository repository;

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private EstadoSolicitudRepository estadoSolicitudRepository;

    public UsuarioPermisoEntity solicitar(UsuarioPermisoEntity permiso) {
        logger.debug("Solicitando permiso para usuario: {}", permiso.getUsuario().getNumeroDocumento());

        if (permiso.getTipoPermiso() == null || permiso.getFechaInicio() == null || permiso.getFechaFin() == null) {
            throw new IllegalArgumentException("El tipo de permiso, fecha de inicio y fin son obligatorios");
        }

        if (permiso.getFechaInicio().isAfter(permiso.getFechaFin())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }

        if (repository.existsSolapamientoPermisos(
                permiso.getUsuario(),
                permiso.getFechaInicio(),
                permiso.getFechaFin())) {
            throw new IllegalArgumentException("Ya existe un permiso aprobado que se solapa con estas fechas");
        }

        permiso.setEstadoSolicitud(estadoSolicitudRepository.findByNombre("Pendiente")
                .orElseThrow(() -> new IllegalStateException("Estado 'Pendiente' no encontrado")));

        return repository.save(permiso);
    }

    public UsuarioPermisoEntity aprobar(UUID id) {
        UsuarioPermisoEntity permiso = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Permiso no encontrado"));

        if (!contratoRepository.findContratoVigenteByUsuarioAndFecha(
                permiso.getUsuario(), permiso.getFechaInicio()).isPresent()) {
            throw new IllegalArgumentException("El usuario no tiene contrato vigente para las fechas solicitadas");
        }

        permiso.setEstadoSolicitud(estadoSolicitudRepository.findByNombre("Aprobado")
                .orElseThrow(() -> new IllegalStateException("Estado 'Aprobado' no encontrado")));

        logger.info("Permiso aprobado: {}", id);
        return repository.save(permiso);
    }

    public UsuarioPermisoEntity rechazar(UUID id, String razon) {
        UsuarioPermisoEntity permiso = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Permiso no encontrado"));

        if (razon == null || razon.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe proporcionar una razón para rechazar el permiso");
        }

        permiso.setEstadoSolicitud(estadoSolicitudRepository.findByNombre("Rechazado")
                .orElseThrow(() -> new IllegalStateException("Estado 'Rechazado' no encontrado")));
        permiso.setObservacion(razon);

        logger.info("Permiso rechazado: {} - Razón: {}", id, razon);
        return repository.save(permiso);
    }

    public void cancelar(UUID id) {
        UsuarioPermisoEntity permiso = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Permiso no encontrado"));

        permiso.setEstadoSolicitud(estadoSolicitudRepository.findByNombre("Anulado")
                .orElseThrow(() -> new IllegalStateException("Estado 'Anulado' no encontrado")));

        repository.save(permiso);
        logger.info("Permiso cancelado: {}", id);
    }

    public List<UsuarioPermisoEntity> listarPorUsuario(Usuario usuario) {
        return repository.findByUsuario(usuario);
    }
}