package com.nekokittygames.cctoirc;

public class IRCCard {

	private String type;
	
	private String value;
	private String keep;
	private int draw;
	private int pick;
	private String source;
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the keep
	 */
	public String getKeep() {
		return keep;
	}
	/**
	 * @param keep the keep to set
	 */
	public void setKeep(String keep) {
		this.keep = keep;
	}
	/**
	 * @return the draw
	 */
	public int getDraw() {
		return draw;
	}
	/**
	 * @param draw the draw to set
	 */
	public void setDraw(int draw) {
		this.draw = draw;
	}
	/**
	 * @return the pick
	 */
	public int getPick() {
		return pick;
	}
	/**
	 * @param pick the pick to set
	 */
	public void setPick(int pick) {
		this.pick = pick;
	}
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IRCCard [");
		if (type != null) {
			builder.append("type=");
			builder.append(type);
			builder.append(", ");
		}
		if (value != null) {
			builder.append("value=");
			builder.append(value);
			builder.append(", ");
		}
		if (keep != null) {
			builder.append("keep=");
			builder.append(keep);
			builder.append(", ");
		}
		builder.append("draw=");
		builder.append(draw);
		builder.append(", pick=");
		builder.append(pick);
		builder.append(", ");
		if (source != null) {
			builder.append("source=");
			builder.append(source);
		}
		builder.append("]");
		return builder.toString();
	}
	
	
}
