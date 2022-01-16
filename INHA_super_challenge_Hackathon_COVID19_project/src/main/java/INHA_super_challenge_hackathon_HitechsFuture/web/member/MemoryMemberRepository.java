package INHA_super_challenge_hackathon_HitechsFuture.web.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> map = new HashMap<>();
    // <Member 고유 ID (sequence), 해당 멤버 객체> 로 이루어진 MAP
    private static long sequence = 0L;
    // 고유 ID 생성을 위한 sequence 변수

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        map.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return map.values().stream()
                .filter(member -> member.getId().equals(id))
                .findAny();
    }

    @Override
    public Optional<Member> findByLoginId(String Id) {
        return map.values().stream()
                .filter(member -> member.getLoginId().equals(Id))
                .findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return map.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAllStudent() {
        ArrayList<Member> result = new ArrayList<>();
        for (Member m : findAll()) {
            if (m.getJob().equals(Job.STUDENT)) {
                result.add(m);
            }
        }
        return result;
    }

    @Override
    public List<Member> findAllTeacher() {
        ArrayList<Member> result = new ArrayList<>();
        for (Member m : findAll()) {
            if (m.getJob().equals(Job.TEACHER)) {
                result.add(m);
            }
        }
        return result;
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(map.values());
    }
}
