package com.team.comma.repository;

import com.team.comma.entity.UserEntity;
import com.team.comma.entity.UserPlaylist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;  //자동 import되지 않음

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MainRepositoryTest {

    @Autowired
    private MainRepository mainRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void 마이플레이리스트조회_0(){
        // given

        // when
        List<UserPlaylist> result = mainRepository.findAllByUserInfo_UserKey(1234);

        // then
        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    public void 마이플레이리스트조회_1() {
        // given
        final UserEntity user = UserEntity.builder()
                .userKey((long) 1234)
                .email("bbbaaaa")
                .build();

        final UserPlaylist playlist = UserPlaylist.builder()
                .userInfo(user)
                .alarmSetDay("01")
                .alarmStartTime("01")
                .alarmEndTime("01")
                .build();

        // when
        final UserPlaylist addplaylist = mainRepository.save(playlist);
        List<UserPlaylist> result = mainRepository.findAllByUserInfo_UserKey(1234);

        // then
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void 마이플레이리스트등록() {
        // given
        final UserEntity user = UserEntity.builder()
                .userKey((long) 1234)
                .email("bbbaaaa")
                .build();

        final UserPlaylist playlist = UserPlaylist.builder()
                .userInfo(user)
                .alarmSetDay("01")
                .alarmStartTime("01")
                .alarmEndTime("01")
                .build();

        // when
        final UserPlaylist result = mainRepository.save(playlist);

        // then
        assertThat(result.getPlayKey()).isNotNull();
        assertThat(result.getUserInfo().getUserKey().equals((long) 1234));
        assertThat(result.getAlarmSetDay().equals("01"));
        assertThat(result.getAlarmStartTime().equals("01"));
        assertThat(result.getAlarmEndTime().equals("01"));
    }
}
