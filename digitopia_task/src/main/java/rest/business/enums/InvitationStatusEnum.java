package rest.business.enums;

public enum InvitationStatusEnum {
	ACCEPTED(1), REJECTED(2), PENDING(3);
	
	private final int statusCode;
	private InvitationStatusEnum(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public static InvitationStatusEnum getValue(int value) {
		for(InvitationStatusEnum e : InvitationStatusEnum.values()) {
			if(e.statusCode == value) {
				return e;
			}
		}
		return null;
	}
}
