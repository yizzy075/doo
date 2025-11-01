package co.edu.uco.HumanSolution.service;

import co.edu.uco.HumanSolution.model.UsuarioHoraExtraEntity;
import co.edu.uco.HumanSolution.model.Usuario;
import co.edu.uco.HumanSolution.repository.UsuarioHoraExtraRepository;
import co.edu.uco.HumanSolution.repository.EstadoSolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioHoraExtraService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioHoraExtraService.class);

    @Autowired
    private UsuarioHoraExtraRepository repository;

    @Autowired
    private EstadoSolicitudRepository estadoSolicitudRepository;

    public UsuarioHoraExtraEntity registrar(UsuarioHoraExtraEntity horaExtra) {
        logger.debug("Registrando hora extra para usuario: {}", horaExtra.getUsuario().getNumeroDocumento());

        if (horaExtra.getFecha() == null || horaExtra.getTipoHoraExtra() == null || horaExtra.getHoras() == null) {
            throw new IllegalArgumentException("La fecha, tipo y cantidad de horas son obligatorios");
        }

        if (horaExtra.getFecha().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("No se pueden registrar horas extra en fechas futuras");
        }

        if (repository.existsByUsuarioAndFechaAndTipoHoraExtra(
                horaExtra.getUsuario(),
                horaExtra.getFecha(),
                horaExtra.getTipoHoraExtra())) {
            throw new IllegalArgumentException("Ya existe un registro de hora extra para esa fecha y tipo");
        }

        horaExtra.setEstadoSolicitud(estadoSolicitudRepository.findByNombre("Pendiente")
                .orElseThrow(() -> new IllegalStateException("Estado 'Pendiente' no encontrado")));

        return repository.save(horaExtra);
    }

    public UsuarioHoraExtraEntity aprobar(UUID id) {
        UsuarioHoraExtraEntity horaExtra = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hora extra no encontrada"));

        horaExtra.setEstadoSolicitud(estadoSolicitudRepository.findByNombre("Aprobado")
                .orElseThrow(() -> new IllegalStateException("Estado 'Aprobado' no encontrado")));

        logger.info("Hora extra aprobada: {}", id);
        return repository.save(horaExtra);
    }

    public List<UsuarioHoraExtraEntity> listarPorUsuario(Usuario usuario) {
        return repository.findByUsuario(usuario);
    }

    public List<UsuarioHoraExtraEntity> listarAprobadas(Usuario usuario) {
        return repository.findByUsuarioAndEstadoSolicitud_Nombre(usuario, "Aprobado");
    }

    public void pagar(UUID id) {
        UsuarioHoraExtraEntity horaExtra = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hora extra no encontrada"));

        if (!"Aprobado".equals(horaExtra.getEstadoSolicitud().getNombre())) {
            throw new IllegalArgumentException("Solo se pueden pagar horas extra aprobadas");
        }

        logger.info("Hora extra pagada: {}", id);
    }
}