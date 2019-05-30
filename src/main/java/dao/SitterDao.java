package dao;

import bean.Member;
import bean.Seeker;
import bean.Sitter;
import org.apache.log4j.Logger;
import util.QueryExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SitterDao {
    private final static Logger logger = Logger.getLogger(DatabaseConnector.class);
    static Connection conn = DatabaseConnector.getConnection();
    public static boolean save(Sitter sitter){
        MemberDao.save(sitter);
        int id=MemberDao.getByEmailid(sitter.getEmail());
        try {

            PreparedStatement stmt = conn.prepareStatement("insert into sitter(memberId,yearsOfExp,expectedPay) values(?,?,?)");
            stmt.setInt(1,id);
            stmt.setInt(2,sitter.getYearsOfExp());
            stmt.setDouble(3,sitter.getExpectedPay());
            boolean result=stmt.execute();
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }
    public static void update(Sitter sitter){
        MemberDao.update(sitter);
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE sitter "
                    + "SET yearsOfExp=?, expectedPay=? "
                    + "WHERE memberId=?");
            stmt.setInt(1, sitter.getYearsOfExp());
            stmt.setDouble(2, sitter.getExpectedPay());
            stmt.setInt(3, sitter.getMemberId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static boolean delete(int sitterId){
        return MemberDao.delete(sitterId);
    }
    public static Sitter getById(int sitterId){
        Sitter sitter=new Sitter();
        Member member=MemberDao.getById(sitterId);
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sitter,member where memberId=id and memberId =?");
            stmt.setInt(1, sitterId);
            ResultSet res = QueryExecutor.queryExecute(stmt);
            while (res.next()) {
                sitter.setMemberId(res.getInt(1));
                sitter.setYearsOfExp(res.getInt(2));
                sitter.setExpectedPay(res.getDouble(3));
                sitter.setId(res.getInt("id"));
                sitter.setAddress(res.getString("address"));
                sitter.setEmail(res.getString("email"));
                sitter.setFirstName(res.getString("firstName"));
                sitter.setLastName(res.getString("lastName"));
                sitter.setPhoneNumber(res.getInt("phoneNumber"));
                sitter.setType(Member.MemberType.stringToEnum(res.getString("type")));
            }
//            sitter.setId(member.getId());
//            sitter.setAddress(member.getAddress());
//            sitter.setEmail(member.getEmail());
//            sitter.setFirstName(member.getFirstName());
//            sitter.setLastName(member.getLastName());
//            sitter.setPhoneNumber(member.getPhoneNumber());
//            sitter.setType(member.getType());
            res.close();
            stmt.close();
            return sitter;

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
//
//    public static void main(String[] args) {
//        Sitter s=new Sitter();
//        s.setAddress("kota");
//        s.setEmail("ram@gmail.com");
//        s.setFirstName("ram");
//        s.setLastName("jha");
//        s.setPassword("ram123");
//        s.setPhoneNumber(990243288);
//        s.setExpectedPay(650.0);
//        s.setYearsOfExp(1);
//        s.setType(Seeker.MemberType.SITTER);
//        SitterDao.save(s);
//            Sitter s=getById(6);
//        System.out.println(s.getId());
//        System.out.println(s.getLastName());
//  }
}

