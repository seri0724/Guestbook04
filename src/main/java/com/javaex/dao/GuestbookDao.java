package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao { //guestbook.xml와 관련, 두개 잘 비교!!
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> getList() {
		
		List<GuestbookVo> list = sqlSession.selectList("guestbook.getList");
		return list;
	}
	public void insert(GuestbookVo vo) {
		
		sqlSession.insert("guestbook.insert", vo);
	}
	public int delete(int no, String password) { //Map사용 이유 : 파라미터가 하나일때 받아올 수 있는데
		int result = 0;                          //지금 no,password 두개 값을 넘겨야 해서 map사용
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no); //key 값, value 값
		map.put("password", password);
		result = sqlSession.delete("guestbook.delete", map);
		return result;
	}
}