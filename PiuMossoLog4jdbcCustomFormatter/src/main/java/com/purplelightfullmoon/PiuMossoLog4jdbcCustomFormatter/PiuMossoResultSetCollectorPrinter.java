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

import java.util.List;

import org.slf4j.Logger;

import net.sf.log4jdbc.ResultSetCollector;
import net.sf.log4jdbc.ResultSetCollectorPrinter;

/**
 * 전체 문자열의 길이에서 전각문자의 길이를 임의로 2로 처리해 리절트셋 쿼리출력의 정렬을 정의하는 클래스
 * @author 		PurplelightFullmoon
 * @version		1.0
 * @License		Apache License 2.0 (<a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>)
 */
public class PiuMossoResultSetCollectorPrinter extends ResultSetCollectorPrinter {
	private Logger log;

	private StringBuffer sb = new StringBuffer();
	
	public PiuMossoResultSetCollectorPrinter(Logger log) {
		super(log);
		
		this.log = log;
	}

	public void printResultSet(ResultSetCollector resultSetCollector) {
		
		int columnCount = resultSetCollector.getColumnCount();
		int maxLength[] = new int[columnCount];

		for (int column = 1; column <= columnCount; column++) {
			maxLength[column - 1] = resultSetCollector.getColumnName(column)
					.replaceAll("[\u0100-\uFFFD]", "aa")
					.length();
		}
		if (resultSetCollector.getRows() != null) {
			for (List<Object> printRow : resultSetCollector.getRows()) {
				int colIndex = 0;
				for (Object v : printRow) {
					if (v != null) {
						int length = v.toString().replaceAll("[\u0100-\uFFFD]", "aa").length();
						if (length > maxLength[colIndex]) {
							maxLength[colIndex] = length;
						}
					}
					colIndex++;
				}
			}
		}
		for (int column = 1; column <= columnCount; column++) {
			maxLength[column - 1] = maxLength[column - 1] + 1;
		}

		print("|");
		for (int column = 1; column <= columnCount; column++) {
			print(padRight("-", maxLength[column - 1]).replaceAll(" ", "-")
					+ "|");
		}
		println();
		print("|");
		for (int column = 1; column <= columnCount; column++) {
			print(padRight(resultSetCollector.getColumnName(column),
					maxLength[column - 1] - countKoLetter(resultSetCollector.getColumnName(column)))
					+ "|");
		}
		println();
		print("|");
		for (int column = 1; column <= columnCount; column++) {
			print(padRight("-", maxLength[column - 1]).replaceAll(" ", "-")
					+ "|");
		}
		println();
		if (resultSetCollector.getRows() != null) {
			for (List<Object> printRow : resultSetCollector.getRows()) {
				int colIndex = 0;
				print("|");
				for (Object v : printRow) {
					print(padRight(v == null ? "null" : v.toString(),
							maxLength[colIndex] - countKoLetter(v.toString()))
							+ "|");
					colIndex++;
				}
				println();
			}
		}
		print("|");
		for (int column = 1; column <= columnCount; column++) {
			print(padRight("-", maxLength[column - 1]).replaceAll(" ", "-")
					+ "|");
		}
		println();
		resultSetCollector.reset();
	}
	
	public static String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}

	public static String padLeft(String s, int n) {
		return String.format("%1$#" + n + "s", s);
	}
	
	private void println() {
		log.info(sb.toString());
		sb.setLength(0);
	}

	private void print(String s) {
		sb.append(s);
	}
	
	private int countKoLetter(String value) {
		return value.length() - value.replaceAll("[\u0100-\uFFFD]", "").length();
	}
}