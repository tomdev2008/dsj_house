package com.dsj.modules.system.enums;

public enum ServiceTypeEnums {
	PROPERTYRIGHT("权证过户业务",1),
	FINANCE("金融业务",2);
	
	private String serviceName;
	private Integer value;
	
	private ServiceTypeEnums(String serviceName,Integer value) {
		this.serviceName = serviceName;
        this.value = value;        
    }

	public String getServiceName() {
		return serviceName;
	}


	public Integer getValue() {
		return value;
	}
	
	public static String parse(int value) {
        for (ServiceTypeEnums os : ServiceTypeEnums.values()) {
            if (value == os.getValue()) {
                return os.getServiceName();
            }
        }
        return null;
    }
}
