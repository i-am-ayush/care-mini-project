package dao;

import bean.Member;
import bean.Seeker;
import org.apache.log4j.Logger;
import util.QueryExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeekerDao {
    private final static Logger logger = Logger.getLogger(DatabaseConnector.class);
    static Connection conn = DatabaseConnector.getConnection();
    public static boolean save(Seeker seeker){
        String emailid=seeker.getEmail();
        MemberDao.save(seeker);
        int member_id=MemberDao.getByEmailid(emailid);
        System.out.println(member_id);
        try {
            PreparedStatement stmt = conn.prepareStatement("insert into seeker(memberId,noOfChildren,spouse) values(?,?,?)");
            stmt.setInt(1,member_id);
            stmt.setInt(2,seeker.getNoOfChildren());
            stmt.setString(3,seeker.getSpouseName());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }
    public static void update(Seeker seeker){
        MemberDao.update(seeker);
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE seeker "
                    + "SET total_children=?, spouse_name=? "
                    + "WHERE memberId=?");

            stmt.setInt(1, seeker.getNoOfChildren());
            stmt.setString(2, seeker.getSpouseName());
            stmt.setInt(3, seeker.getMemberId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public boolean delete(int seekerId){
        return MemberDao.delete(seekerId);
    }
    public static Seeker getById(int seekerId){
        Seeker seeker=new Seeker();
       // Member member=MemberDao.getById(seekerId);
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM seeker,member where memberId=id and memberId =?");
            stmt.setInt(1, seekerId);
            ResultSet res = QueryExecutor.queryExecute(stmt);
            while (res.next()) {
                seeker.setMemberId(res.getInt(1));
                seeker.setNoOfChildren(res.getInt(2));
                seeker.setSpouseName(res.getString(3));
                seeker.setId(res.getInt("id"));
                seeker.setAddress(res.getString("address"));
                seeker.setEmail(res.getString("email"));
                seeker.setFirstName(res.getString("firstName"));
                seeker.setLastName(res.getString("lastName"));
                seeker.setPhoneNumber(res.getInt("phoneNumber"));
                seeker.setType(Member.MemberType.stringToEnum(res.getString("type")));
            }
//            seeker.setId(member.getId());
//            seeker.setAddress(member.getAddress());
//            seeker.setEmail(member.getEmail());
//            seeker.setFirstName(member.getFirstName());
//            seeker.setLastName(member.getLastName());
//            seeker.setPhoneNumber(member.getPhoneNumber());
//            seeker.setType(member.getType());
            res.close();
            stmt.close();
            return seeker;

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static void main(String[] args) {
//        Seeker s=new Seeker();
//        s.setAddress("delhi");
//        s.setEmail("abhilash@gmail.com");
//        s.setFirstName("abhilash");
//        s.setLastName("kumar");
//        s.setPassword("abhilash123");
//        s.setPhoneNumber(990173253);
//        s.setNoOfChildren(2);
//        s.setSpouseName("vijiya");
//        s.setType(Seeker.MemberType.SEEKER);
//        SeekerDao.save(s);

      Seeker s1=getById(1);
       System.out.println(s1.getMemberId() + s1.getId());
   }
}

