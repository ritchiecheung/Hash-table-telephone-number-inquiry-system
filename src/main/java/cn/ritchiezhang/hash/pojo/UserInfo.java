package cn.ritchiezhang.hash.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserInfo {
    private Integer key;
    private String name;
    private String phoneNumber;
    private String address;
}
