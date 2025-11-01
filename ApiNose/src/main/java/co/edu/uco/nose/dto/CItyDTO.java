package co.edu.uco.nose.dto;

import java.util.UUID;

 frontend
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscuting.helpers.TextHelper;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;
public final class CityDTO {

    private UUID id;
    private String name;
    private StateDTO state;

    public static final CityDTO DEFAULT = new CityDTO();

    public static CityDTO getDefaultObject() {
        return DEFAULT;
    }

    public CityDTO() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
        setState(StateDTO.getDefaultObject());
    }

    public CityDTO(final UUID id) {
        setId(id);
        setName(TextHelper.getDefault());
        setState(StateDTO.getDefaultObject());
    }

    public CityDTO(final UUID id, final String name, final StateDTO state) {
        setId(id);
        setName(name);
        setState(state);
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }

    public StateDTO getState() {
        return state;
    }

    public void setState(final StateDTO state) {
        this.state = ObjectHelper.getDefault(state, StateDTO.getDefaultObject());
    }

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class CityDTO extends DTO{
	
	private String name;
	
	
	
	public CityDTO() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
	}

	public CityDTO(final UUID id) {
		super(id);
		setName(TextHelper.getDefault());
	}
	
	public CityDTO(final UUID id,final String name) {
		super(id);
		this.name = name;
	}

	


	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = TextHelper.getDefaultWithTrim(name);
	}main
}
