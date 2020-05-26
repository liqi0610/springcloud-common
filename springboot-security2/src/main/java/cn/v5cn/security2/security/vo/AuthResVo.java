package cn.v5cn.security2.security.vo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author : liqi
 * Date: 2018-11-23
 * Time: 18:25
 */

public class AuthResVo {

    private String userName;

    //private Long customerId;

    private List<String> permissions;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    public Long getCustomerId() {
//        return customerId;
//    }
//
//    public void setCustomerId(Long customerId) {
//        this.customerId = customerId;
//    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
