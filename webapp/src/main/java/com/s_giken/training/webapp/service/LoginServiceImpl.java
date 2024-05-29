package com.s_giken.training.webapp.service;

import jakarta.servlet.http.HttpSession;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import com.s_giken.training.webapp.model.entity.Login;
import com.s_giken.training.webapp.model.entity.LoginSearchCondition;
import com.s_giken.training.webapp.repository.LoginRepository;

// ログイン情報保存のサービスクラス(実態クラス)
@Service
public class LoginServiceImpl implements LoginService {
    private HttpSession session;
    private LoginRepository loginRepository;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    // コンストラクタを作成
    public LoginServiceImpl(HttpSession session, LoginRepository loginRepository) {
        this.session = session;
        this.loginRepository = loginRepository;
    }

    // ログインした日時を保存
    @Override
    public void save() {
        // セッション開始時間をLocalDateTime型に変換
        long createTime = session.getCreationTime();
        LocalDateTime creatDate = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(createTime),
                ZoneId.of("Asia/Tokyo"));

        // Loginエンティティオブジェクトを作成し、変換したセッション開始時間を設定してセーブする
        Login loginInfo = new Login();
        loginInfo.setLoginDateTime(creatDate);
        loginRepository.save(loginInfo);
    }

    // 最新のログイン日時を収得
    @Override
    public String findLatest(LoginSearchCondition loginSearchCondition) {
        String loginDateTimestr = loginRepository
                .findByLoginDateTime(loginSearchCondition.getLoginDateTime())
                .toString();
        //LocalDateTime.parse(loginDateTimestr, formatter)
        return loginDateTimestr;
    }


    // 前回のログイン日時を収得

    @Override
    public String findLast(LoginSearchCondition loginSearchCondition) {
        String lastLoginDateTimestr = loginRepository
                .findByLastLoginDateTime(loginSearchCondition.getLoginDateTime())
                .toString();
        return lastLoginDateTimestr;
    }

}
