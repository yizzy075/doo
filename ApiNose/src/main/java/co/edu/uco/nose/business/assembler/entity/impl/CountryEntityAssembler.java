package co.edu.uco.nose.business.assembler.entity.impl;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.CountryDomain;
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.entity.CountryEntity;
import java.util.ArrayList;
import java.util.List;

public final class CountryEntityAssembler implements EntityAssembler<CountryEntity, CountryDomain> {

    private static final EntityAssembler<CountryEntity, CountryDomain> INSTANCE = new CountryEntityAssembler();
    public static EntityAssembler<CountryEntity, CountryDomain> getCountryEntityAssembler() {
        return INSTANCE;
    }
    @Override
    public CountryEntity toEntity(final CountryDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, CountryDomain.getDefaultObject());
        return new CountryEntity(domainTmp.getId(), domainTmp.getName());
    }

    @Override
    public CountryDomain toDomain(final CountryEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, CountryEntity.getDefaultObject());
        return new CountryDomain(entityTmp.getId(), entityTmp.getName());
    }

    @Override
    public List<CountryEntity> toEntityList(final List<CountryDomain> domainList) {
        var list = new ArrayList<CountryEntity>();
        if (domainList == null) return list;
        for (var domain : domainList) {
            list.add(toEntity(domain));
        }
        return list;
    }

    @Override
    public List<CountryDomain> toDomainList(final List<CountryEntity> entityList) {
        var list = new ArrayList<CountryDomain>();
        if (entityList == null) return list;
        for (var entity : entityList) {
            list.add(toDomain(entity));
        }
        return list;
    }
}
