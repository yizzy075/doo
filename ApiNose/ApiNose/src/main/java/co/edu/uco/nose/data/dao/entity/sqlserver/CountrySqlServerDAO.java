package co.edu.uco.nose.data.dao.entity.sqlserver;

import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.entity.CountryEntity;

import java.util.List;
import java.util.UUID;

public final class CountrySqlServerDAO implements CountryDAO {

    @Override
    public List<CountryEntity> findAll() {
        return List.of();
    }

    @Override
    public List<CountryEntity> findByFilter(CountryEntity filterEntity) {
        return List.of();
    }

    @Override
    public CountryEntity findById(UUID uuid) {
        return null;
    }
}
