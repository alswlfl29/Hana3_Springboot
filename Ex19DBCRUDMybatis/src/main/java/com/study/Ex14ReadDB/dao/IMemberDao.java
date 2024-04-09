package com.study.Ex14ReadDB.dao;

import com.study.Ex14ReadDB.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// XML 이랑 맵핑되는 용도 -> @Mapper 붙여주기!
// @Mapper: 인터페이스 DAO와 MyBatis XML과 연결하는 용도
@Mapper
public interface IMemberDao {
    // list(select *)
    public List<MemberDto> list();
    // select count(*)
    public int count();

    // insert
    public int insert(MemberDto dto);
    public int insertMap(HashMap map);

    // select where id=:id
    public Optional<MemberDto> findById(int id);
    // update
    public int update(MemberDto dto);
    // delete
    public int delete(int id);
    // map.put("id", id);
    // map.put("userId", userId);
    public int deleteMap(int id, String userId);
    public int deleteMap(Map map);
}
