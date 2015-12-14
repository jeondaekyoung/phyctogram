package naree.db.mapper;

import java.util.List;
import java.util.Map;

import naree.db.domain.Height;

public interface HeightMapper {

	int insertHeight(Height insHeight);

	List<Height> selectHeightByUser_seq(String user_seq);

	int deleteHeightByUserSeq(String user_seq);

	List<Height> selectHeightByUserSeqFT(Map<String, String> terms);

	String selectHeightSeq();

	int delectHeightByHeightSeq(String height_seq);
}
