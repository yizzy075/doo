package co.edu.uco.HumanSolution.service;

import co.edu.uco.HumanSolution.model.UsuarioDocumentoEntity;
import co.edu.uco.HumanSolution.model.Usuario;
import co.edu.uco.HumanSolution.repository.UsuarioDocumentoRepository;
import co.edu.uco.HumanSolution.repository.TipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioDocumentoService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioDocumentoService.class);

    @Autowired
    private UsuarioDocumentoRepository repository;

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    public UsuarioDocumentoEntity subir(UsuarioDocumentoEntity documento) {
        logger.debug("Subiendo documento para usuario: {}", documento.getUsuario().getNumeroDocumento());

        if (documento.getTipoDocumento() == null || documento.getFecha() == null) {
            throw new IllegalArgumentException("El tipo de documento y la fecha son obligatorios");
        }

        if (!tipoDocumentoRepository.existsById(documento.getTipoDocumento().getId())) {
            throw new IllegalArgumentException("El tipo de documento no existe en el catálogo");
        }

        if (repository.existsByUsuarioAndTipoDocumentoAndFecha(
                documento.getUsuario(),
                documento.getTipoDocumento(),
                documento.getFecha())) {
            throw new IllegalArgumentException("Ya existe un documento de ese tipo para esa fecha");
        }

        return repository.save(documento);
    }

    public List<UsuarioDocumentoEntity> listarPorUsuario(Usuario usuario) {
        return repository.findByUsuario(usuario);
    }

    public UsuarioDocumentoEntity validar(UUID id) {
        UsuarioDocumentoEntity documento = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Documento no encontrado"));

        logger.info("Documento validado: {}", id);
        return repository.save(documento);
    }

    public void eliminar(UUID id) {
        repository.deleteById(id);
    }
}