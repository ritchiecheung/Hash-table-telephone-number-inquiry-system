package cn.ritchiezhang.hash.controller;

import cn.ritchiezhang.hash.dao.UserInfoDao;
import cn.ritchiezhang.hash.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;
import java.util.HashMap;

@CrossOrigin
@RestController
public class HashController {
    @Autowired
    UserInfoDao userInfoDao;
    UserInfo userInfo;

    //保存数据的接口
    @PostMapping("/save")
    public String save(@PathParam("name") String name, @PathParam("phoneNumber") String phoneNumber, @PathParam("address") String address) {
        HashMap<Integer, UserInfo> save = userInfoDao.save(name, phoneNumber, address);
        if (save != null) {
            return "success";
        } else {
            return "error";
        }
    }

    //查询所有数据的接口
    @GetMapping("/findAll")
    public Collection<UserInfo> findAll() {
        return userInfoDao.findAll();
    }

    //按照手机号查询数据的接口
    @GetMapping("/findByPhoneNumber/{phoneNumber}")
    public Collection<UserInfo> findByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        return userInfoDao.findByNumber(phoneNumber);
    }

    //删除数据的接口
    @DeleteMapping("/delete/{key}")
    public void delete(@PathVariable("key") int key) {
        userInfoDao.delete(key);
    }

    //按照姓名查询数据的接口
    @GetMapping("/findByName/{name}")
    public Collection<UserInfo> findByName(@PathVariable("name") String name) {
        return userInfoDao.findByName(name);
    }

    //更新数据的接口
    @PostMapping("/update")
    public String update(@PathParam("name") String name, @PathParam("phoneNumber") String phoneNumber, @PathParam("address") String address) {
        HashMap<Integer, UserInfo> update = userInfoDao.update(name, phoneNumber, address);
        if (update != null) {
            return "success";
        } else {
            return "error";
        }
    }

    //按照key查询数据的接口
    @GetMapping("/findByKey/{key}")
    public UserInfo findByKey(@PathVariable("key") int key) {
        return userInfoDao.findByKey(key);
    }

}
