package com.wuyifeng.runner.core.service;

public interface ManagerService {

    /**
     * 管理员登录
     * @param username
     * @param password
     * @return
     */
    public ManagerService login(String username, String password);

    /**
     * 通过ID获取管理员信息
     * @param id
     * @return
     */
    public ManagerService get(Long id);

    /**
     * 修改密码
     * @param id
     * @param newPwd
     * @return
     */
    public ManagerService modifyPwd(Long id, String newPwd);
}
