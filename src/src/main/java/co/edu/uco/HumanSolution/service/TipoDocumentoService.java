package co.edu.uco.HumanSolution.service;

import co.edu.uco.HumanSolution.model.TipoDocumentoEntity;
import co.edu.uco.HumanSolution.repository.TipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TipoDocumentoService {

    private static final Logger logger = LoggerFactory.getLogger(TipoDocumentoService.class);

    @Autowired
    private TipoDocumentoRepository repository;

    public TipoDocumentoEntity crear(TipoDocumentoEntity tipoDocumento) {
        logger.debug("Creando tipo de documento: {}", tipoDocumento.getNombre());

        if (tipoDocumento.getNombre() == null || tipoDocumento.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del tipo de documento es obligatorio");
        }

        if (tipoDocumento.getDescripcion() == null || tipoDocumento.getDescripcion().trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción del tipo de documento es obligatoria");
        }

        if (repository.existsByNombre(tipoDocumento.getNombre())) {
            throw new IllegalArgumentException("Ya existe un tipo de documento con ese nombre");
        }

        return repository.save(tipoDocumento);
    }

    public List<TipoDocumentoEntity> listarTodos() {
        return repository.findAll();
    }

    public Optional<TipoDocumentoEntity> buscarPorId(UUID id) {
        return repository.findById(id);
    }

    public void eliminar(UUID id) {
        repository.deleteById(id);
    }
}