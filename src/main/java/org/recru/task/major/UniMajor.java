package org.recru.task.major;

public enum UniMajor {
	COMPUTER_SCIENCE, MATHEMATICS, PHYSICS, ECONOMICS, BUSINESS, ENGINEERING, MBA;

	public static boolean isSupported(String value) {
		UniMajor[] majors = UniMajor.values();
		value = value.toUpperCase()
		             .replace(" ", "_");
		for (UniMajor major : majors) {
			if (major.name()
			         .equals(value)) {
				return true;
			}
		}
		return false;
	}

	public static UniMajor fromValue(String value) {
		return UniMajor.valueOf(value.toUpperCase()
		                             .replace(" ", "_"));
	}
}
