package com.me.test.pojo;

import lombok.*;

@Data
@Setter
@Getter
//使用@AllArgsConstructor自动生成有参构造
@AllArgsConstructor
//使用@NoArgsConstructor自动生成无参构造
@NoArgsConstructor
public class Notice {
    private  Integer  massageView;
    private  Integer  applyView;
}
