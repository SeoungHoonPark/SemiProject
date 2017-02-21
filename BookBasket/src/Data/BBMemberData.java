package Data;

public class BBMemberData extends BBMainData{
	
	public int no;
	public String id;
	public String pw;
	public String name;
	public String email;
	public String phone;
	public String jdate;
	
	public String getId(){
		return id;
	}

	@Override
	public String toString() {
		return "BBMemberData [no=" + no + ", id=" + id + ", pw=" + pw + ", name=" + name + ", email=" + email
				+ ", phone=" + phone + ", jdate=" + jdate + "]";
	}
}
