package es.cmaj.crennis.domain;

public class Player {
	private String name;
	private String telephone;
	private String email;

	public Player() {
		this(null, null, null);
	}

	public Player(String name) {
		this(name, null, null);
	}

	public Player(String name, String telephone, String email) {
		super();

		this.name = name;
		this.telephone = telephone;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Player [name=");
		builder.append(name);
		if (telephone != null) {
			builder.append(", telephone=").append(telephone);
		}
		if (email != null) {
			builder.append(", email=").append(email);
		}
		builder.append("]");
		return builder.toString();
	}
}
