package com.cos.photogramstart.domain.likes;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.subscribe.Subscribe;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
		uniqueConstraints = {
				@UniqueConstraint(
						name="likes_uk",	//복합키명
						columnNames = {"imageId","userId"}	//컬럼명
				)
		}
)
public class Likes { // mariadb랑 mysql 은 like가 키워드이기때문에 Likes 로 만들었음

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// 무한 참조됨
	@JoinColumn(name = "imageId")
	@ManyToOne
	private Image image;
	
	@JsonIgnoreProperties({"images"})
	@JoinColumn(name = "userId")
	@ManyToOne
	private User user;
	
	// nativeQuery (insert) 사용하면 데이터안들어감 직접 nativeQuery에 now() 해줘야함
	private LocalDateTime createDate;
	
	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
