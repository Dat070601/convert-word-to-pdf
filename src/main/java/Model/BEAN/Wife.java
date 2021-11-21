package Model.BEAN;

public class Wife {
	private String name;
	private String address;
	private int alive;
	private String password;
	
	public Wife(String name, String address, int alive, String password) {
		super();
		this.name = name;
		this.address = address;
		this.alive = alive;
		this.password = password;
	}
	public Wife()
	{
		
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int isAlive() {
		return alive;
	}
	public void setAlive(int alive) {
		this.alive = alive;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}