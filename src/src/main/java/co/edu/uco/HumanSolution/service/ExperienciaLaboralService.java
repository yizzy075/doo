package co.edu.uco.HumanSolution.service;

import co.edu.uco.HumanSolution.model.ExperienciaLaboralEntity;
import co.edu.uco.HumanSolution.model.Usuario;
import co.edu.uco.HumanSolution.repository.ExperienciaLaboralRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExperienciaLaboralService {

    private static final Logger logger = LoggerFactory.getLogger(ExperienciaLaboralService.class);

    @Autowired
    private ExperienciaLaboralRepository repository;

    public ExperienciaLaboralEntity agregar(ExperienciaLaboralEntity experiencia) {
        logger.debug("Agregando experiencia laboral: {} - {}", experiencia.getEmpresa(), experiencia.getCargo());

        if (experiencia.getEmpresa() == null || experiencia.getCargo() == null || experiencia.getFechaInicio() == null) {
            throw new IllegalArgumentException("La empresa, cargo y fecha de inicio son obligatorios");
        }

        if (experiencia.getFechaFin() != null && experiencia.getFechaInicio().isAfter(experiencia.getFechaFin())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }

        if (repository.existsByUsuarioAndEmpresaAndCargoAndFechaInicio(
                experiencia.getUsuario(),
                experiencia.getEmpresa(),
                experiencia.getCargo(),
                experiencia.getFechaInicio())) {
            throw new IllegalArgumentException("Ya existe una experiencia laboral con esos datos");
        }

        return repository.save(experiencia);
    }

    public List<ExperienciaLaboralEntity> listarPorUsuario(Usuario usuario) {
        return repository.findByUsuario(usuario);
    }

    public ExperienciaLaboralEntity editar(UUID id, ExperienciaLaboralEntity experiencia) {
        ExperienciaLaboralEntity existente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Experiencia laboral no encontrada"));

        existente.setEmpresa(experiencia.getEmpresa());
        existente.setCargo(experiencia.getCargo());
        existente.setFechaInicio(experiencia.getFechaInicio());
        existente.setFechaFin(experiencia.getFechaFin());

        return repository.save(existente);
    }

    public void eliminar(UUID id) {
        repository.deleteById(id);
    }
}