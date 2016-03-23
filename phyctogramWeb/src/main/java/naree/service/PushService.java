package naree.service;

import java.util.List;

public interface PushService {
	/**
	 * selectBox 목록 불러오기
	 * @param pageCnt
	 * @return
	 */
	List<String> selectBoxList();
}
