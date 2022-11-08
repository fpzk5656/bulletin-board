package singleproject.bulletinboard.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;
import singleproject.bulletinboard.domain.Member;

@Repository
public class MemoryMemberRepository implements MemberRepository {

	private final ConcurrentHashMap<Long, Member> store = new ConcurrentHashMap<>();
	private static AtomicLong sequence = new AtomicLong();

	@Override
	public Member save(Member member) {

		Long newId = sequence.incrementAndGet();
		Member newMember = member.builder()
			.id(newId)
			.name(member.getName())
			.age(member.getAge())
			.point(member.getPoint())
			.password(member.getPassword())
			.birthday(member.getBirthday())
			.build();
		store.put(newId, newMember);

		return newMember;
	}

	@Override
	public Optional<Member> findById(Long id) {

		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Member> findByName(String name) {

		return store.values().stream()
			.filter(member -> member.isSameName(name))
			.findAny();
	}

	@Override
	public List<Member> findAll() {

		return new ArrayList<>(store.values());
	}

	public void clearStore() {
		store.clear();
		sequence = new AtomicLong();
	}
}
