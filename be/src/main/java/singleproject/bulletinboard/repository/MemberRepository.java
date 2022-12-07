package singleproject.bulletinboard.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import singleproject.bulletinboard.domain.user.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByName(String name);
	Optional<Member> findByEmail(String email);
}
