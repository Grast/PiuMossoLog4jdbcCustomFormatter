/*
 * Copyright 2019 PurplelightFullmoon-Grast
 * (https://blog.naver.com/dldnjswo0417)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.purplelightfullmoon.PiuMossoLog4jdbcCustomFormatter;

import net.sf.log4jdbc.tools.LoggingType;

/**
 * 반각문자를 제외한, 전각문자의 단일문자길이를 반영해 resultsettable의 정렬을 바로잡는 클래스
 * @author 		PurplelightFullmoon
 * @version		1.0
 * @License		Apache License 2.0 (<a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>)
 */
public class PiuMossoLog4JdbcCustomFormatter extends PiuMossoSlf4jSpyLogDelegator {
	private LoggingType loggingType = LoggingType.DISABLED;

	private String sqlPrefix = "SQL:";

	public void setLoggingType(LoggingType loggingType) {
		this.loggingType = loggingType;
	}

	public void setSqlPrefix(String sqlPrefix) {
		this.sqlPrefix = sqlPrefix;
	}
}