package com.e3.service.user.api.impl;

import com.e3.jedis.dao.JedisClient;
import com.e3.service.user.api.UserService;
import com.e3.service.user.mapper.MenusPermissionMapper;
import com.e3.service.user.mapper.SysUserMapper;
import com.e3.service.user.mapper.TbUserMapper;
import com.e3.service.user.pojo.*;
import com.e3.util.common.E3Result;
import com.e3.util.common.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/12/21 0021.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper  userMapper;

    @Autowired
   private  MenusPermissionMapper  menusPermissionMapper;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private JedisClient jedisClient;

    //3.4.1检查数据是否可用
    @Override
    public E3Result checkParam(String param, Integer type) {
        TbUserExample  example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();

        if(type==1){
            criteria.andUsernameEqualTo(param);
        }
        if (type==2){
            criteria.andPhoneEqualTo(param);
        }
        if(type==3){
            criteria.andEmailEqualTo(param);
        }
        List<TbUser> list = tbUserMapper.selectByExample(example);
        if(list !=null &&list.size()>0){
            return E3Result.ok(false);
        }
        return E3Result.ok(true);
    }
//用户注册
    @Override
    public E3Result registerUser(TbUser tbUser) {
        //1.获取用户信息,校验用户信息
        E3Result  result = this.checkParam(tbUser.getUsername(),1);
        if (!(boolean)result.getData()){//校验用户名
            return E3Result.build(400,"用户名称不可用!!!");
        }
        E3Result  result2 = this.checkParam(tbUser.getPhone(),2);
        if (!(boolean)result2.getData()){//校验电话
            return E3Result.build(400,"电话不可用!!!");
        }

        E3Result  result3 = this.checkParam(tbUser.getEmail(),3);
        if (!(boolean)result3.getData()){//校验邮箱
            return E3Result.build(400,"邮箱不可用!!!");
        }
        //数据补全
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        tbUserMapper.insert(tbUser);
        return E3Result.ok();
    }
    //登录
    @Override
    public E3Result login(String username, String password) {
        TbUserExample  example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> list = tbUserMapper.selectByExample(example);
        TbUser  user = new TbUser();
        if(list!=null &&list.size()>0){
                user = list.get(0);
        }
        String pwd  = user.getPassword();
        //生成token码
        String  token = UUID.randomUUID().toString();
        //以token码为key 把用户信息存入到redis中.
        if (password.equals(pwd)){
            //把用户信息存入到redis中
            user.setPassword(null);
            jedisClient.set("USER_SESSION_REDIS:"+token, JsonUtils.objectToJson(user));
            //设置生命周期
            jedisClient.expire("USER_SESSION_REDIS:"+token,7200);
            return E3Result.ok(token);//token码随机数
        }
        return null;
    }
//根据token码换取用户信息
    @Override
    public E3Result getBytokenUser(String token) {
        String jsondata = jedisClient.get("USER_SESSION_REDIS:"+token);
        if (StringUtils.isNotBlank(jsondata)){
            TbUser  tbUser = JsonUtils.jsonToPojo(jsondata,TbUser.class);
            return E3Result.ok(tbUser);
        }
        return E3Result.ok();
    }

    //登录
    @Override
    public E3Result authenticat(String usercode, String pwd) {
        //1.调用getUser方法查询用户信息
        SysUser  sysUser = this.getUser(usercode);
        //2.如果不存在,返回此用户不存在
        if(sysUser==null){
            return  E3Result.build(400,"此用户不存在!!");
        }
        //3.如果存在,用户密码和输入的密码进行比较
        //获取数据库密码
        String pwd_db=sysUser.getPassword();
        //获取盐值
        String salt = sysUser.getSalt();
        //输入的密码+颜值与  pwd_dbs数据库里的密码进行比较
        String pwd_salt = DigestUtils.md5DigestAsHex((pwd+salt).getBytes()) ;
        if(!pwd_db.equals(pwd_salt)){
            return E3Result.build(400,"用户名或者密码错误");
        }


        ActiveUser user = new ActiveUser();
        user.setUserId(sysUser.getId());
        user.setUsercord(sysUser.getUsercode());
        user.setUsername(sysUser.getUsername());
        //权限管理
        //4.如果成功,返回成功成功信息
        return E3Result.ok(user);
        //5.失败的话告诉前台用户名或者密码错误.
    }






    //根据usercode查询用户信息
    public SysUser getUser(String usercode){
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsercodeEqualTo(usercode);
        List<SysUser> list = userMapper.selectByExample(example);
        if(list.size()>0){
            return  list.get(0);
        }
        return null;
    }

}
