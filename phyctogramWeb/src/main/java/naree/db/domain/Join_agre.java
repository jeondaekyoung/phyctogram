package naree.db.domain;

import java.sql.Date;

public class Join_agre {

	private int join_agre_seq;
	private String use_stplat_agre_at;
	private Date use_stplat_agre_de;
	private String indvdlinfo_trtmnt_polcy_agre_at;
	private Date indvdlinfo_trtmnt_polcy_de;
	private int member_seq;
	public int getJoin_agre_seq() {
		return join_agre_seq;
	}
	public void setJoin_agre_seq(int join_agre_seq) {
		this.join_agre_seq = join_agre_seq;
	}
	public String getUse_stplat_agre_at() {
		return use_stplat_agre_at;
	}
	public void setUse_stplat_agre_at(String use_stplat_agre_at) {
		this.use_stplat_agre_at = use_stplat_agre_at;
	}
	public Date getUse_stplat_agre_de() {
		return use_stplat_agre_de;
	}
	public void setUse_stplat_agre_de(Date use_stplat_agre_de) {
		this.use_stplat_agre_de = use_stplat_agre_de;
	}
	public String getIndvdlinfo_trtmnt_polcy_agre_at() {
		return indvdlinfo_trtmnt_polcy_agre_at;
	}
	public void setIndvdlinfo_trtmnt_polcy_agre_at(String indvdlinfo_trtmnt_polcy_agre_at) {
		this.indvdlinfo_trtmnt_polcy_agre_at = indvdlinfo_trtmnt_polcy_agre_at;
	}
	public Date getIndvdlinfo_trtmnt_polcy_de() {
		return indvdlinfo_trtmnt_polcy_de;
	}
	public void setIndvdlinfo_trtmnt_polcy_de(Date indvdlinfo_trtmnt_polcy_de) {
		this.indvdlinfo_trtmnt_polcy_de = indvdlinfo_trtmnt_polcy_de;
	}
	public int getMember_seq() {
		return member_seq;
	}
	public void setMember_seq(int member_seq) {
		this.member_seq = member_seq;
	}
	@Override
	public String toString() {
		return "Join_agre [join_agre_seq=" + join_agre_seq + ", use_stplat_agre_at=" + use_stplat_agre_at
				+ ", use_stplat_agre_de=" + use_stplat_agre_de + ", indvdlinfo_trtmnt_polcy_agre_at="
				+ indvdlinfo_trtmnt_polcy_agre_at + ", indvdlinfo_trtmnt_polcy_de=" + indvdlinfo_trtmnt_polcy_de
				+ ", member_seq=" + member_seq + "]";
	}
	
	
}
