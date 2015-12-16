package naree.db.mapper;

import java.util.List;

import naree.db.domain.Commnty;
import naree.db.domain.SqlCommntyListView;

public interface CommntyMapper {

	int insertCommnty(Commnty commnty);

	List<SqlCommntyListView> selectCommntyLatest(int pageCntFirstIndex);

	List<SqlCommntyListView> selectCommntyPopular(int pageCntFirstIndex);

}
