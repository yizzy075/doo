package co.edu.uco.nose.business.assembler.dto.impl;

import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.dto.CountryDTO;
import co.edu.uco.nose.business.domain.CountryDomain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public final class CountryDTOAssembler implements DTOAssembler<CountryDTO, CountryDomain> {

    private static final DTOAssembler<CountryDTO, CountryDomain> instance =
    new CountryDTOAssembler();

    private CountryDTOAssembler(){


    }

    public static DTOAssembler<CountryDTO, CountryDomain> getCountryDTOAssembler(){
        return instance;
    }


    @Override
    public CountryDTO toDTO(final CountryDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new CountryDomain(UUIDHelper.getUUIDHelper().getDefault()));
        return new CountryDTO(domainTmp.getId(), domainTmp.getName());
    }

    @Override
    public CountryDomain toDomain(final CountryDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, new CountryDTO());
        return new CountryDomain(dtoTmp.getId(), dtoTmp.getName());
    }

    @Override
    public List<CountryDTO> toDTO(List<CountryDomain> domainList) {
        var dtoList = new ArrayList<CountryDTO>();
        for (var domain : ObjectHelper.getDefault(domainList, List.<CountryDomain>of())) {
            dtoList.add(toDTO(domain));
        }
        return dtoList;
    }
}
