package co.edu.uco.nose.buisness.domain;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class StateDomain extends Domain{
	
	private String name;
	
	public StateDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
	}

	public StateDomain(final UUID id) {
		super(id);
		setName(TextHelper.getDefault());
	}
	
	public StateDomain(final UUID id,final String name) {
		super(id);
		this.name = name;
	}

	


	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = TextHelper.getDefaultWithTrim(name);
	}
	
	

}