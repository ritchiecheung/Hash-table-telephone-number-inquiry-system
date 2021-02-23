package cn.ritchiezhang.hash.dao;

import cn.ritchiezhang.hash.pojo.UserInfo;
import org.springframework.stereotype.Repository;


import java.util.*;

@Repository
public class UserInfoDao {
    static HashMap<Integer, UserInfo> user = new HashMap<>();
    static int MAXSIZE = 11;

    //哈希函数，将电话号码每一位求和进行哈希
    public int getHashKey(String phoneNumber) {
        char[] arr = phoneNumber.toCharArray();
        int key = 0;
        for (int i = 0; i < phoneNumber.length(); i++) {
            key += arr[i] - '0';
        }
        return key % MAXSIZE;
    }

    //二次探测再散列，解决冲突问题
    public int HandleCollision(int key) {
        int d = 1;
        while (user.get(key) != null) {
            key = (key + d) % MAXSIZE;
            d = (int) Math.pow(d, 2);
        }

        return key;
    }
    //判断数据是否有重复
    public boolean check(String name, String phoneNumber, String address) {
        for (Integer key : user.keySet()) {
            UserInfo obj = user.get(key);
            if (obj.getName().contains(name) && obj.getPhoneNumber().contains(phoneNumber)) {
                return false;
            }
        }

        return true;
    }

    //保存数据的方法
    public HashMap<Integer, UserInfo> save(String name, String phoneNumber, String address) {

        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setPhoneNumber(phoneNumber);
        userInfo.setAddress(address);
        //判断是否输入了重复的数据
        if (check(name, phoneNumber, address) == false) {
            return null;
        }
        int key = getHashKey(userInfo.getPhoneNumber());
        if (user.get(key) != null) {
            key = HandleCollision(key);
        }

        userInfo.setKey(key);
        user.put(key, userInfo);
        return user;
    }

    //查询所有数据的方法
    public Collection<UserInfo> findAll() {
        return user.values();
    }

    //按照电话号码查询数据的方法
    public Collection<UserInfo> findByNumber(String phoneNumber) {
        ArrayList<UserInfo> arrayList = new ArrayList<>();
        for (Integer key : user.keySet()) {
            UserInfo obj = user.get(key);
            if (obj.getPhoneNumber().contains(phoneNumber)) {
                arrayList.add((user.get(key)));
            }
        }
        if (arrayList != null) {
            return arrayList;
        } else {
            return null;
        }
    }

    //按照用户名查询数据的方法
    public Collection<UserInfo> findByName(String name) {
        ArrayList<UserInfo> arrayList = new ArrayList<>();
        for (Integer key : user.keySet()) {
            UserInfo obj = user.get(key);
            if (obj.getName().contains(name)) {
                arrayList.add((user.get(key)));
            }
        }
        if (arrayList != null) {
            return arrayList;
        } else {
            return null;
        }
    }

    //按照key查询数据的方法
    public UserInfo findByKey(Integer key) {
        return user.get(key);
    }

    //更新数据的方法
    public HashMap<Integer, UserInfo> update(String name, String phoneNumber, String address) {
        int kkey = 0;
        if (check(name, phoneNumber, address) == false) {
            return null;
        } else {
            for (Integer key : user.keySet()) {
                UserInfo obj = user.get(key);
                if (obj.getName().contains(name) || obj.getPhoneNumber().contains(phoneNumber)) {
                    kkey = key;
                    break;
                }

            }
            UserInfo userInfo = new UserInfo();
            userInfo.setName(name);
            userInfo.setAddress(address);
            userInfo.setPhoneNumber(phoneNumber);
            userInfo.setKey(kkey);
            user.put(kkey, userInfo);
            return user;
        }

    }

    //删除数据的方法
    public void delete(Integer key) {
        user.remove(key);
    }

}
