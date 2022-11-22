package org.recru.task.subject;

public enum UniSubject {
	MATH,
	ENGLISH,
	PHYSICS,
	DATABASES,
	JAVA_PROGRAMMING,
	ALGORITHMS,
	OPERATING_SYSTEMS,
	NETWORKS,
	DESIGN_PATTERNS,
	ECONOMICS,
	JAPANESE,
	GERMAN,
	SPANISH,
	ITALIAN,
	CHINESE;

	public static boolean isSupported(String value) {
		UniSubject[] subjects = UniSubject.values();
		value = value.toUpperCase()
		             .replace(" ", "_");
		for (UniSubject subject : subjects) {
			if (subject.name()
			           .equals(value)) {
				return true;
			}
		}
		return false;
	}

	public static UniSubject fromValue(String value) {
		return UniSubject.valueOf(value.toUpperCase()
		                               .replace(" ", "_"));
	}
}
