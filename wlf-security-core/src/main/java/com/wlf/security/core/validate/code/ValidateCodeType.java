package com.wlf.security.core.validate.code;

import com.wlf.security.core.properties.Constants;

public enum ValidateCodeType {

	IMAGE{
		@Override
		public String getParamNameOnValidate() {
			return Constants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
		}
	},
	SMS{
		@Override
		public String getParamNameOnValidate() {
			return Constants.DEFAULT_PARAMETER_NAME_CODE_SMS;
		}
	};
	
	public abstract String getParamNameOnValidate();
	
}
