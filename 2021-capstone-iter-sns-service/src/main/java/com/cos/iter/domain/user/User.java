package com.cos.iter.domain.user;

import com.cos.iter.web.dto.FollowRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@SqlResultSetMapping(
		name = "FollowRespDtoMapping",
		classes = @ConstructorResult(
				targetClass = FollowRespDto.class,
				columns = {
						@ColumnResult(name="id", type = Integer.class),
						@ColumnResult(name="username", type = String.class),
						@ColumnResult(name="name", type = String.class),
						@ColumnResult(name="profileImage", type = String.class),
						@ColumnResult(name="followState", type = Boolean.class),
						@ColumnResult(name="equalUserState", type = Boolean.class),
				}
		)
)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String username;
	private String password;
	private String email; // 모델만들때 실수로 안넣음.
	private String name;
	private String website;
	private String bio; // 자기 소개
	private String phone;
	private String gender;
	private String profileImage;
	@Enumerated(EnumType.STRING)
	private UserRole role;
	private String provider;
	private String providerId;
	@CreationTimestamp
	private Timestamp createDate;

}





