package co.edu.uco.HumanSolution.service;

import co.edu.uco.HumanSolution.model.TipoHoraExtraEntity;
import co.edu.uco.HumanSolution.repository.TipoHoraExtraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TipoHoraExtraService {

    private static final Logger logger = LoggerFactory.getLogger(TipoHoraExtraService.class);

    @Autowired
    private TipoHoraExtraRepository repository;

    public TipoHoraExtraEntity crear(TipoHoraExtraEntity tipoHoraExtra) {
        logger.debug("Creando tipo de hora extra: {}", tipoHoraExtra.getNombre());

        if (tipoHoraExtra.getNombre() == null || tipoHoraExtra.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del tipo de hora extra es obligatorio");
        }

        if (tipoHoraExtra.getRecargo() == null || tipoHoraExtra.getRecargo() <= 0) {
            throw new IllegalArgumentException("El porcentaje de recargo es obligatorio y debe ser positivo");
        }

        if (repository.existsByNombre(tipoHoraExtra.getNombre())) {
            throw new IllegalArgumentException("Ya existe un tipo de hora extra con ese nombre");
        }

        return repository.save(tipoHoraExtra);
    }

    public List<TipoHoraExtraEntity> listarTodos() {
        return repository.findAll();
    }

    public Optional<TipoHoraExtraEntity> buscarPorId(UUID id) {
        return repository.findById(id);
    }
}