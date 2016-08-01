package genius.sqldb.model;

import genius.sqldb.annotation.Column;
import genius.sqldb.annotation.PrimaryKey;

public class User {
	@PrimaryKey(isAutoGenerate=true)
	private long id;
	private String name;
	@Column(defaultValue="1")
	private int age;
	
	public User() {
	}
	
	public User(String name) {
		super();
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "{id=" + id + ",name=" + name + "}";
	}
}
