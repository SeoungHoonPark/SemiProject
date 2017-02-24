package Data;

import java.io.Serializable;
import java.util.Date;

public class BBBookData implements Serializable {
	public int bb_no;
	public String bb_name;
	public String bb_writer;
	public String bb_ownerid;
	public String bb_staus;
	public int bb_rcode;
  public String bb_date;
	public char	  bb_visibleYN;
  public String bb_com;



	@Override
	public String toString(){
		
		return bb_name;
	}
//	bb_no 	  number(10),
//	bb_name   varchar2(200),
//	bb_writer varchar2(200),
//	bb_ownerid varchar2(50),
//	bb_company varchar2(200),
//	bb_date	   date,
//	bb_status  char(4),
//	bb_visibleYN char(1),
//	bb_buyd	  varchar2(50)
}
