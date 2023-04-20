package com.cos.photogramstart.domain.subscribe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer>{

	@Modifying	// INSERT, DELETE, UPDATE 를 네이티브 쿼리로 작성하려면 해당 어노테이션 필요!!
	@Query(value = "INSERT INTO subscribe (fromUserId, toUserId, createDate) VALUES (:fromUserId, :toUserId, now())", nativeQuery = true)
	void mSubscribe(int fromUserId, int toUserId);	// 1(변경된 행의 개수가 리턴됨), -1
	
	@Modifying
	@Query(value = "DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId", nativeQuery = true)
	void mUnSubscribe(int fromUserId, int toUserId);	// 1, -1
	
	@Query(value = "select count(*) from subscribe where fromUserId = :pricipalId and toUserId = :pageUserId", nativeQuery = true)
	int mSubscribeState(int pricipalId, int pageUserId);
	
	@Query(value = "select count(*) from subscribe where fromUserId = :pageUserId", nativeQuery = true)
	int mSubscribeCount(int pageUserId);

}
