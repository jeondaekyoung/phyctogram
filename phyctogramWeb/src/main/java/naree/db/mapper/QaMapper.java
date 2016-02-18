package naree.db.mapper;

import java.util.HashMap;
import java.util.List;

import naree.db.domain.Qa;

public interface QaMapper {

	//List<Qa> selectqaByMemberSeq(int member_seq, int pageCnt);

	List<Qa> selectqaByMemberSeq(HashMap<String, Object> map);

	int insertQa(Qa qa);

	List<Qa> listQa(HashMap<String, Object> map);
	
	int modifyQa(HashMap<String, Object> map);

	Qa selectByQaSeq(int qa_seq);

}
