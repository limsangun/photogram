package com.cos.photogramstart.domain.image;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImageRepository extends JpaRepository<Image, Integer>{
	
	@Query(value="select * from image where userId in (select toUserId from subscribe where fromUserId = :principalId)", nativeQuery = true)
	Page<Image> mStory(int principalId, Pageable pageable);
	
	@Query(value="select i.* from image i Inner join (select imageId, count(imageId) as likeCount from likes group by imageId) c on i.id = c.imageId order by likeCount desc", nativeQuery = true)
	List<Image> mPopular();
}
