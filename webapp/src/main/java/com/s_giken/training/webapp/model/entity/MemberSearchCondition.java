package com.s_giken.training.webapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // メンバー変数に対するゲッター・セッターを自動生成
@NoArgsConstructor // 引数のないコンストラクタを自動生成
@AllArgsConstructor // 全てのメンバ変数に対する引数を持つコンストラクタを自動生成
public class MemberSearchCondition {
    private String mail;
    private String name;
    private String category;
    private String sort;
}
