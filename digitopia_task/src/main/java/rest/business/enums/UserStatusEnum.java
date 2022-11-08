package rest.business.enums;

public enum UserStatusEnum {
	ACTIVE(1), PENDING(2), DEACTIVATED(3), INVITED(4), PASSIVE(5), DELETED(6);
	
	private final int statusCode;
	private UserStatusEnum(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public static UserStatusEnum getValue(int value) {
		for(UserStatusEnum e : UserStatusEnum.values()) {
			if(e.statusCode == value) {
				return e;
			}
		}
		return null;
	}
	
}
