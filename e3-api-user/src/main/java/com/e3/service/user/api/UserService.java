package com.e3.service.user.api;

import com.e3.service.user.pojo.TbUser;
import com.e3.util.common.E3Result;

public interface UserService {
    E3Result authenticat(String usercode, String password);

    E3Result checkParam(String param, Integer type);

    E3Result registerUser(TbUser tbUser);

    E3Result login(String username, String password);

    E3Result getBytokenUser(String token);
}
