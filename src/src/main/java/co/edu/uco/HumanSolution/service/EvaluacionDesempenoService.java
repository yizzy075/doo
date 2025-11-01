package co.edu.uco.HumanSolution.service;

import co.edu.uco.HumanSolution.model.EvaluacionDesempenoEntity;
import co.edu.uco.HumanSolution.model.Usuario;
import co.edu.uco.HumanSolution.repository.EvaluacionDesempenoRepository;
import co.edu.uco.HumanSolution.repository.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class EvaluacionDesempenoService {

    private static final Logger logger = LoggerFactory.getLogger(EvaluacionDesempenoService.class);

    @Autowired
    private EvaluacionDesempenoRepository repository;

    @Autowired
    private ContratoRepository contratoRepository;

    public EvaluacionDesempenoEntity registrar(EvaluacionDesempenoEntity evaluacion) {
        logger.debug("Registrando evaluación para usuario: {}", evaluacion.getUsuario().getNumeroDocumento());

        if (evaluacion.getFecha() == null || evaluacion.getCalificacion() == null) {
            throw new IllegalArgumentException("La fecha y calificación son obligatorias");
        }

        if (repository.existsByUsuarioAndFecha(evaluacion.getUsuario(), evaluacion.getFecha())) {
            throw new IllegalArgumentException("Ya existe una evaluación para ese usuario en esa fecha");
        }

        if (evaluacion.getFecha().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de evaluación no puede ser futura");
        }

        if (!contratoRepository.findContratoVigenteByUsuarioAndFecha(
                evaluacion.getUsuario(), evaluacion.getFecha()).isPresent()) {
            throw new IllegalArgumentException("El usuario no tiene contrato vigente en esa fecha");
        }

        if (evaluacion.getCalificacion() < 1 || evaluacion.getCalificacion() > 10) {
            throw new IllegalArgumentException("La calificación debe estar entre 1 y 10");
        }

        return repository.save(evaluacion);
    }

    public List<EvaluacionDesempenoEntity> listarPorUsuario(Usuario usuario) {
        return repository.findByUsuario(usuario);
    }

    public EvaluacionDesempenoEntity modificar(UUID id, EvaluacionDesempenoEntity evaluacion, String justificacion) {
        EvaluacionDesempenoEntity existente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evaluación no encontrada"));

        if (justificacion == null || justificacion.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe proporcionar justificación para modificar");
        }

        existente.setCalificacion(evaluacion.getCalificacion());
        existente.setObservacion(evaluacion.getObservacion());

        logger.info("Evaluación modificada - Justificación: {}", justificacion);
        return repository.save(existente);
    }

    public void eliminar(UUID id, String justificacion) {
        if (justificacion == null || justificacion.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe proporcionar justificación para eliminar");
        }

        logger.warn("Evaluación eliminada - Justificación: {}", justificacion);
        repository.deleteById(id);
    }
}