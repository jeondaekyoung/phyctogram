package naree.db.mapper;

import java.util.List;
import java.util.Map;

import naree.db.domain.Height;

public interface HeightMapper {

	int insertHeight(Height insHeight);

	List<Height> selectHeightByUser_seq(String user_seq);

	int deleteHeightByUserSeq(String user_seq);

	List<Height> selectHeightByUserSeqFT(Map<String, Object> terms);

	String selectHeightSeq();

	int deleteHeightByHeightSeq(String height_seq);

	int insertHeightAnd(Height height);
	
	List<Height> selectHeightForGraphByUserSeq(int user_seq);

	String selectRankByHeight(Height h);
	
	int selectMaxRankByHeight(Height h);

	double selectAveHeightByHeight(Height h);

	int selectExistHeightByUserSeq(int user_seq);
}