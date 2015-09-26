package wsilva.com.br.mobileos.entity;

import java.io.Serializable;

public class EntityVO implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int EntityId;
	
	public int getEntityId() {
		return EntityId;
	}
	public void setEntityId(int entityId) {
		EntityId = entityId;
	}
	
}
