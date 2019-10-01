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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.log4jdbc.ResultSetCollector;
import net.sf.log4jdbc.Slf4jSpyLogDelegator;

/**
 * 전각문자의 정렬을 담당하는 클래스로 resultSetCollected 메소드를 재정의한 클래스
 * @author 		PurplelightFullmoon
 * @version		1.0
 * @License		Apache License 2.0 (<a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>)
 */
public class PiuMossoSlf4jSpyLogDelegator extends Slf4jSpyLogDelegator {
	private final Logger resultSetTableLogger = LoggerFactory.getLogger("jdbc.resultsettable");

	public PiuMossoSlf4jSpyLogDelegator() {}

	@Override
	public void resultSetCollected(ResultSetCollector resultSetCollector) {
		new PiuMossoResultSetCollectorPrinter(resultSetTableLogger).printResultSet(resultSetCollector);
	}
}