package service;

import bean.Member;
import dao.MemberDao;

public class MemberService {
    public static String loginAuthentication(String emailId, String password) {
      //  boolean res = MemberDao.getByEmailIdAndPassword(emailId, password);
//        Member m = null;
//        if (res == true) {
//            int id = MemberDao.getByEmailIdAndPassword(emailId);
//            m = MemberDao.getById(id);
//            return String.valueOf(m.getType());
//        }
      return null;
    }


    public static void main(String[] args) {
        System.out.println(MemberService.loginAuthentication("harsh@gmail.com","harsh123"));
        System.out.println(MemberService.loginAuthentication("ram@gmail.com","ram123"));


    }
}