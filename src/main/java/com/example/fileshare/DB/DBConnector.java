package com.example.fileshare.DB;

import com.example.fileshare.processingPack.Group;
import com.example.fileshare.processingPack.Resource;
import sun.awt.image.ImageWatched;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
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
                "values ((select id from user_table where \"user_ID\" = '" + targetName + "')," + resource + "," + level + ")\non conflict do nothing ;";
        return DB.execute(sql, true);
    }

    public boolean shareResourceToGroup(String targetName, String resource, String level) {
        String sql = "  insert into group_resources(group_id, resource_id)\n" +
                " VALUES (" + targetName + "," + resource + ")on conflict do nothing ;";
        return DB.execute(sql, true);
    }

    public List<String[]> getGroupsForUser(String username) {
        String sql = "select groups.group_name,groups.group_id from groups\n" +
                "INNER JOIN user_groups gr on groups.group_id = gr.group_id\n" +
                "where (select id from user_table where \"user_ID\" = '" + username + "') = gr.user_id";
        ResultSet rs = DB.getData(sql);
        List<String[]> groups = new ArrayList<>();
        try {
            while (rs.next()) {
                String s[] = new String[2];
                s[0] = rs.getString(1);
                s[1] = rs.getString(2);
                groups.add(s);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return groups;
    }
    //create a group with users
    public boolean createGroup(String groupName, String[] membersList, String owner) {
        String sql = "insert into groups(group_name,creator_id,description,created_time)\n" +
                "values ('" + groupName + "',(select id from user_table where \"user_ID\" = '" + owner + "'),'Group',current_timestamp) returning group_id;";

        ResultSet set = DB.getData(sql);
        long id = 0;
        try {
            set.next();
            id = set.getLong(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Group crested in groups with id " + id);

        sql = "insert into user_groups(user_id, group_id)\n" +
                "values ((select id from user_table where \"user_ID\" = '" + owner + "')," + id + ");";
        DB.execute(sql, true);

        StringBuffer sb = new StringBuffer("insert into group_permission_table(user_id, group_id)\nvalues\n");
        for (int i = 0; i < membersList.length; i++) {
            sb.append("((select id from user_table where \"user_ID\" = '" + membersList[i] + "')," + id + ")" + (i == membersList.length - 1 ? ";" : ","));
        }

        if (DB.execute(sb.toString(), false)) {
            DB.commit();
        } else {
            DB.rollback();
            return false;
        }
        return true;
    }
    //create a public group with key
    public boolean createGroup(String groupName, String owner) {
      String sql = "insert into groups(group_name, creator_id, description, created_time, group_key) VALUES " +
              "("+groupName+",(select id from user_table where \"user_ID\" = '" + owner + "'),'group',current_timestamp,"+generateNewToken()+")";
      return DB.execute(sql,true);
    }

    //generatetoken
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    private static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    //get request for join in request
    public List<String[]> getJoinGroupRequest(String username) {
        String sql = "Select groups.group_id,groups.group_name,(select \"user_ID\" from user_table where user_table.id = groups.creator_id) from groups\n" +
                "INNER JOIN group_permission_table gr on groups.group_id = gr.group_id\n" +
                "where (select id from user_table where \"user_ID\" = '" + username + "') = gr.user_id";
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

    public boolean joinGroup(String username, String groupID) {
        //check if user has a request
        String sql = "select user_id from group_permission_table\n" +
                "where user_id = (select id from user_table where \"user_ID\" = '" + username + "') and group_id = " + groupID + ";";
        ResultSet set = DB.getData(sql);
        String userId = null;
        boolean flag = false;
        try {
            if (set.next() != false) flag = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //add user to group
        if (flag) {
            sql = "insert into user_groups(user_id, group_id)\n" +
                    "values ((select id from user_table where \"user_ID\" = '" + username + "')," + groupID + ");";

            flag = DB.execute(sql, true);


            // remove from permissions table
            sql = "delete from group_permission_table\n" +
                    "where group_id = '" + groupID + "' and user_id = (select id from user_table where \"user_ID\" = '" + username + "');";

            if (flag)
                flag = DB.execute(sql, true);
        }
        return flag;
    }

    public Group getGroup(String groupLink) {

        String sql = "select * from groups\n" +
                "where group_id = " + groupLink + ";";

        ResultSet rs = DB.getData(sql);
        String groupName = null;
        String group_id = null;
        String owner_id = null;
        String description = null;
        String created_time = null;
        try {
            rs.next();
            groupName = rs.getString(1);
            group_id = rs.getString(2);
            owner_id = rs.getString(3);
            description = rs.getString(4);
            created_time = rs.getString(5);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        List<String> members = new LinkedList<>();
        sql = "select \"user_ID\" from user_table\n" +
                "inner join user_groups g on user_table.id = g.user_id\n" +
                "where group_id = " + groupLink + ";";
        rs = DB.getData(sql);

        try {
            while (rs.next()) {
                members.add(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        sql = "select (select \"user_ID\" from user_table where id = owner_id), file_name, version, size, lables, location, description, chats,uploaded_time,resource_table.resource_id from resource_table\n" +
                "inner join group_resources gr on resource_table.resource_id = gr.resource_id\n" +
                "where group_id = " + groupLink + ";";
        List<Resource> resources = new LinkedList<Resource>();
        rs = DB.getData(sql);

        try {
            while (rs.next()) {
                Resource resource = new Resource(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getLong(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(10), rs.getString(9));
                System.out.println(resource);
                resources.add(resource);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return new Group(owner_id, groupName, group_id, created_time, description, resources, members);
    }

    public boolean removeResource(String resourceID, String username) {

        String sql = "select * from resource_table\n" +
                "where owner_id = (select id from user_table where \"user_ID\" = '" + username + "') and resource_id = '" + resourceID + "';";
        ResultSet set = DB.getData(sql);
        boolean flag = false;
        try {
            if (set.next())
                flag =true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "delete from resource_permission\n" +
                "where resource_id = '" + resourceID + "';\n" +
                "\n" +
                "delete from group_resources\n" +
                "where resource_id = '" + resourceID + "';\n" +
                "\n" +
                "delete from resource_table\n" +
                "where resource_id = '" + resourceID + "';";

        if (flag)
            return DB.execute(sql, true);
        else
            return false;
    }

    public void addComment(String resourceID, String comment, String username) {
        String sql = "";
    }

    public List<Resource> getSharedResourcesForUser(String username) {
        String sql = "select (select \"user_ID\" from user_table where id = owner_id), file_name, version, size, lables, location, description, chats,uploaded_time,resource_table.resource_id from resource_table\n" +
                "inner join resource_permission rp on resource_table.resource_id = rp.resource_id\n" +
                "where rp.user_id = (select id from user_table where \"user_ID\" = '"+username+"');";

        List<Resource> resources = new LinkedList<Resource>();
        ResultSet rs = DB.getData(sql);

        try {
            while (rs.next()) {
                Resource resource = new Resource(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getLong(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(10), rs.getString(9));
                System.out.println(resource);
                resources.add(resource);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return resources;
    }

    public boolean updateResource(Resource resource, String fileName, int version,String location) {
        String sql = "update resource_table\n" +
                "set (version,file_name,location,uploaded_time) = ("+version+",'"+fileName+"','"+location+"',current_timestamp)\n" +
                "where resource_table.resource_id = "+resource.getId()+";";

        return DB.execute(sql,true);
    }

    public boolean removeResourceFromGroup(String groupID, String resourceID) {
        String sql = "delete from group_resources where group_id = "+groupID+" and resource_id = "+resourceID+";";
        return DB.execute(sql,true);
    }
}
