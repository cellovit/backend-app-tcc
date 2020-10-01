package br.org.tcc.enums;

public enum ColumnType {

	None(0), //
	Categorical(1), //
	Date(2), //
	Numerical(3);

	public final int value;

	private ColumnType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
