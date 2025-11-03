package co.edu.uco.nose.data.dao.entity.sqlserver;
import co.edu.uco.nose.data.dao.entity.StateDAO;
import co.edu.uco.nose.entity.StateEntity;

import java.util.List;
import java.util.UUID;

public final class StateSqlServerDAO implements StateDAO{


    @Override
    public List<StateEntity> findAll() {
        return List.of();
    }

    @Override
    public List<StateEntity> findByFilter(StateEntity filterEntity) {
        return List.of();
    }

    @Override
    public StateEntity findById(UUID uuid) {
        return null;
    }
}
