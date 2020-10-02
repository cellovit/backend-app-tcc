package br.org.tcc.enums;

public enum ChartType {

	Bar(0), //
	Column(1), //
	Line(2), //
	Scatter(3), //
	Pie(4);

	public final int value;

	private ChartType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
