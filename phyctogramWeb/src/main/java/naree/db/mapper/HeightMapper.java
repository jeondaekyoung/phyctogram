package naree.db.mapper;

import java.util.List;

import naree.db.domain.Height;

public interface HeightMapper {

	int insertHeight(Height insHeight);

	List<Height> selectHeightByUser_seq(String user_seq);
}
