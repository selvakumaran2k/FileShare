package com.example.fileshare.DB;

import com.example.fileshare.processingPack.Resource;
import sun.awt.image.ImageWatched;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//singleton class for connection to DB contains functions with sql queries
public class DBConnector {
    private DBInterface DB = null;
    private static DBConnector instance = null;

    private DBConnector() {
    }

    public static DBConnector getInstance() {
        if (instance == null) {
            instance = new DBConnector();
            instance.DB = PGDB.getInstance();
        }
        return instance;
    }

    public boolean validateUser(String username, String password) {
        return true;
//        String sql = "select password from user_table\n" +
//                "where \"user_ID\" = '"+username+"';";
//
//        ResultSet resultSet = DB.getData(sql);
//        try {
//            resultSet.next();
//            String real = resultSet.getString(1);
//            if(hashString(password).equals(real))
//            {
//              return true ;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
    }

    public List<Resource> getUserResources(String username) {
        String sql = "select (select \"user_ID\" from user_table where id = owner_id), file_name, version, size, lables, location, description, chats,uploaded_time,resource_id from resource_table\n" +
                "where owner_id = (select id from user_table where \"user_ID\" = '" + username + "');";

        List<Resource> resources = new LinkedList<Resource>();
        ResultSet rs = DB.getData(sql);

        try {
            while (rs.next()) {
                Resource resource = new Resource(username, rs.getString(2), rs.getInt(3), rs.getLong(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(10), rs.getString(9));
                System.out.println(resource);
                resources.add(resource);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return resources;
    }

    public Resource getResource(String resourceID) {
        String sql = "select (select \"user_ID\" from user_table where id = owner_id), file_name, version, size, lables, location, description, chats,uploaded_time,resource_id from resource_table\n" +
                "where resource_id = '" + resourceID + "';";

        ResultSet rs = DB.getData(sql);
        Resource resource = null;

        try {
            while (rs.next()) {
                resource = new Resource(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getLong(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(10), rs.getString(9));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return resource;
    }

    //hash string
    private String hashString(String input) {
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addResource(Resource resource) {
        String sql = "insert into resource_table(owner_id, file_name, version, size, lables, location, description, chats,uploaded_time)\n" +
                "values ((select id from user_table where  \"user_ID\" = '" + resource.getOwner() + "'),'" + resource.getFileName() + "'," + resource.getVersion() + "," + resource.getSize() + ",'" + resource.getLables() + "','" + resource.getLocation() + "','" + resource.getDescription() + "','" + resource.getChats() + "',current_timestamp);\n";
        return DB.execute(sql, true);
    }

    public boolean shareResource(String targetName, String resource, String level) {
        String sql = "insert into resource_permission(user_id, resource_id, level)\n" +
                "values ((select id from user_table where \"user_ID\" = '" + targetName + "')," + resource + "," + level + ");";
        return DB.execute(sql, true);
    }

    public boolean shareResourceToGroup(String targetName, String resource, String level) {
        String sql = "  insert into group_resources(group_id, resource_id)\n" +
                " VALUES ((select group_id from groups where group_name = '" + targetName + "')," + resource + ");";
        return DB.execute(sql, true);
    }

    public List<String> getGroupsForUser(String username) {
        String sql = "select groups.group_name from groups\n" +
                "INNER JOIN user_groups gr on groups.group_id = gr.group_id\n" +
                "where (select id from user_table where \"user_ID\" = '"+username+"') = gr.user_id";
        ResultSet rs = DB.getData(sql);
        List<String> groups = new ArrayList<>();
        try {
            while (rs.next()) {
                groups.add(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return groups;
    }

    public boolean createGroup(String groupName, String[] membersList) {
        String sql = "insert into groups(group_name)\n" +
                "values ('" + groupName + "');";

        if (!DB.execute(sql, false))
            return false;

        StringBuffer sb = new StringBuffer("insert into group_permission_table(user_id, group_id)\n");
        for (String members : membersList) {
            sb.append("values ((select id from user_table where \"user_ID\" = '" + members + "'),(select group_id from groups where \"group_name\" = '" + groupName + "'));");
        }

        if (DB.execute(sb.toString(), false)) {
            DB.commit();
        } else {
            DB.rollback();
            return false;
        }
        return true;
    }
    //get request for join in request
    public List<String[]> getJoinGroupRequest(String username)
    {
        String sql = "Select groups.group_id,groups.group_name,(select \"user_ID\" from user_table where user_table.id = groups.creator_id) from groups\n" +
                "INNER JOIN group_permission_table gr on groups.group_id = gr.group_id\n" +
                "where (select id from user_table where \"user_ID\" = '"+username+"') = gr.user_id";
        ResultSet rs = DB.getData(sql);
        List<String[]> groups = new ArrayList<>();
        try {
            while (rs.next()) {
                String s[] = new String[3];
                s[0] = rs.getString(1);
                s[1] = rs.getString(2);
                s[2] = rs.getString(3);
                groups.add(s);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return groups;
    }
    public boolean joinGroup(String username,String groupID)
    {
        String sql = "select user_id from group_permission_table\n" +
                "where user_id = (select id from user_table where \"user_ID\" = '"+username+"') and group_id = "+groupID+";";
        ResultSet set = DB.getData(sql);
        String userId = null;
        boolean flag  = false;
        try {
            if(set.next()!=false) flag = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(flag)
        {
            sql = "insert into user_groups(user_id, group_id)\n" +
                    "values ((select id from user_table where \"user_ID\" = '"+username+"'),"+groupID+");";

            flag = DB.execute(sql,true);
        }
        return flag;


    }
}
