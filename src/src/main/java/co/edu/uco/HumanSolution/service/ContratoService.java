package co.edu.uco.HumanSolution.service;

import co.edu.uco.HumanSolution.model.ContratoEntity;
import co.edu.uco.HumanSolution.model.Usuario;
import co.edu.uco.HumanSolution.repository.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContratoService {

    private static final Logger logger = LoggerFactory.getLogger(ContratoService.class);

    @Autowired
    private ContratoRepository repository;

    public ContratoEntity registrar(ContratoEntity contrato) {
        logger.debug("Registrando contrato para usuario: {}", contrato.getUsuario().getNumeroDocumento());

        if (contrato.getSueldo() == null || contrato.getFechaInicio() == null) {
            throw new IllegalArgumentException("El salario y la fecha de inicio son obligatorios");
        }

        if (contrato.getFechaFin() != null && contrato.getFechaInicio().isAfter(contrato.getFechaFin())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }

        if (repository.existsContratoActivoByUsuario(contrato.getUsuario())) {
            throw new IllegalArgumentException("El usuario ya tiene un contrato vigente");
        }

        contrato.setActivo(true);
        return repository.save(contrato);
    }

    public List<ContratoEntity> listarTodos() {
        return repository.findAll();
    }

    public Optional<ContratoEntity> buscarPorId(UUID id) {
        return repository.findById(id);
    }

    public List<ContratoEntity> buscarPorUsuario(Usuario usuario) {
        return repository.findByUsuario(usuario);
    }

    public ContratoEntity finalizar(UUID id, String razon) {
        ContratoEntity contrato = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrato no encontrado"));

        if (razon == null || razon.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe proporcionar una razón para finalizar el contrato");
        }

        contrato.setFechaFin(LocalDate.now());
        contrato.setActivo(false);

        logger.info("Contrato finalizado - Razón: {}", razon);
        return repository.save(contrato);
    }

    public boolean existeContratoVigente(Usuario usuario, LocalDate fecha) {
        return repository.findContratoVigenteByUsuarioAndFecha(usuario, fecha).isPresent();
    }
}