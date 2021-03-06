package com.tams.controller;

import com.tams.annotation.Permission;
import com.tams.domain.Role;
import com.tams.dto.AjaxResult;
import com.tams.enums.OperationTypeEnum;
import com.tams.enums.RightTypeEnum;
import com.tams.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author swiChen
 * @date 2022/1/13
 **/

@RestController
@RequestMapping("role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Permission(operation = OperationTypeEnum.R , right = RightTypeEnum.CONFIG)
    @GetMapping("list/info")
    public AjaxResult<List<Role>> queryRoles(){
        return AjaxResult.succ(roleService.list());
    }

}
