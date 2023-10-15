package ef.repository.DatabaseRepoImpl;

import ef.model.Post;
import ef.model.Region;
import ef.model.Writer;
import ef.repository.WriterRepository;
import ef.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WriterRepoImpl implements WriterRepository {

    @Override
    public Writer save(Writer writer) {
        try {
            PreparedStatement preparedStatement = DBUtil.openConnectionToDB().prepareStatement(("INSERT INTO writer(first_name,last_name) values (?,?)"), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, writer.getFirstName());
            preparedStatement.setString(2, writer.getLastName());
            preparedStatement.executeUpdate();
            ResultSet getGeneratedId = preparedStatement.getGeneratedKeys();
            while (getGeneratedId.next()) {
                writer.setId(getGeneratedId.getLong("id_writer"));
            }
            getGeneratedId.close();
            preparedStatement.close();

            PreparedStatement preparedStatement1 = DBUtil.openConnectionToDB().prepareStatement("INSERT INTO writer_region_post(writer_id,region_id,post_id) values (?,?,?)");
            for (Post n : writer.getPosts()) {
                preparedStatement1.setLong(1, writer.getId());
                preparedStatement1.setLong(2, writer.getRegion().getId());
                preparedStatement1.setLong(3, n.getId());
                preparedStatement1.addBatch();
            }
            preparedStatement1.executeBatch();
            preparedStatement1.close();
            DBUtil.closeConnectionToBD();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return writer;
    }

    @Override
    public Writer getById(Long aLong) {
        Writer writer = new Writer();
        try {
            PreparedStatement preparedStatement = DBUtil.openConnectionToDB().prepareStatement("SELECT writer.id_writer,writer.first_name, writer.last_name,region.id_region,region.name FROM writer_region_post\n" +
                    "INNER JOIN writer\n" +
                    "ON writer_region_post.writer_id = writer.id_writer\n" +
                    "INNER JOIN region\n" +
                    "ON writer_region_post.region_id=region.id_region\n" +
                    "WHERE writer_id=?;");
            preparedStatement.setLong(1, aLong);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                writer.setId(resultSet.getLong("id_writer"));
                writer.setFirstName(resultSet.getString("first_name"));
                writer.setLastName(resultSet.getString("last_name"));
                writer.setRegion(new Region(resultSet.getLong("id_region"), resultSet.getString("name")));
            }
            resultSet.close();
            preparedStatement.close();

            PreparedStatement preparedStatement1 = DBUtil.openConnectionToDB().prepareStatement("SELECT post.id_post,post.content,post.created,post.updated FROM writer_region_post\n" +
                    "INNER JOIN writer\n" +
                    "ON writer_region_post.writer_id = writer.id_writer\n" +
                    "INNER JOIN region\n" +
                    "ON writer_region_post.region_id=region.id_region\n" +
                    "INNER JOIN post\n" +
                    "ON writer_region_post.post_id = post.id_post\n" +
                    "WHERE writer_id=?;");
            List<Post> posts = new ArrayList<>();
            preparedStatement1.setLong(1, aLong);
            ResultSet resultSet2 = preparedStatement1.executeQuery();
            while (resultSet2.next()) {
                Post post = new Post();
                post.setId(resultSet2.getLong("id_post"));
                post.setContent(resultSet2.getString("content"));
                post.setCreated(resultSet2.getLong("created"));
                post.setUpdated(resultSet2.getLong("updated"));
                posts.add(post);
            }
            writer.setPosts(posts);

            resultSet2.close();
            preparedStatement1.close();
            DBUtil.closeConnectionToBD();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        try {
            PreparedStatement preparedStatement = DBUtil.openConnectionToDB().prepareStatement("UPDATE writer SET first_name=?, last_name=? WHERE id_writer=?");
            preparedStatement.setLong(3, writer.getId());
            preparedStatement.setString(1, writer.getFirstName());
            preparedStatement.setString(2, writer.getLastName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return writer;
    }

    @Override
    public void deleteById(Long aLong) {
        try {
            PreparedStatement preparedStatement = DBUtil.openConnectionToDB().prepareStatement("DELETE FROM writer WHERE id_writer=?");
            preparedStatement.setLong(1, aLong);
            preparedStatement.executeUpdate();
            DBUtil.closeConnectionToBD();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Writer> getAll() {
        List<Writer> writers = new ArrayList<>();
        try {
            Statement statement = DBUtil.openConnectionToDB().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT writer.id_writer,writer.first_name, writer.last_name,region.id_region,region.name FROM writer_region_post\n" +
                    "INNER JOIN writer\n" +
                    "ON writer_region_post.writer_id = writer.id_writer\n" +
                    "INNER JOIN region\n" +
                    "ON writer_region_post.region_id=region.id_region\n");
            while (resultSet.next()) {
                Writer writer = new Writer();
                writer.setId(resultSet.getLong("id_writer"));
                writer.setFirstName(resultSet.getString("first_name"));
                writer.setLastName(resultSet.getString("last_name"));
                writer.setRegion(new Region(resultSet.getLong("id_region"), resultSet.getString("name")));
                PreparedStatement preparedStatement1 = DBUtil.openConnectionToDB().prepareStatement("SELECT post.id_post,post.content,post.created,post.updated FROM writer_region_post\n" +
                        "INNER JOIN writer\n" +
                        "ON writer_region_post.writer_id = writer.id_writer\n" +
                        "INNER JOIN region\n" +
                        "ON writer_region_post.region_id=region.id_region\n" +
                        "INNER JOIN post\n" +
                        "ON writer_region_post.post_id = post.id_post\n" +
                        "WHERE writer_id=?;");
                preparedStatement1.setLong(1, writer.getId());

                List<Post> posts = new ArrayList<>();
                ResultSet resultSet2 = preparedStatement1.executeQuery();
                while (resultSet2.next()) {
                    Post post = new Post();
                    post.setId(resultSet2.getLong("id_post"));
                    post.setContent(resultSet2.getString("content"));
                    post.setCreated(resultSet2.getLong("created"));
                    post.setUpdated(resultSet2.getLong("updated"));
                    posts.add(post);
                }
                writer.setPosts(posts);
                writers.add(writer);
                preparedStatement1.close();
                resultSet2.close();
            }
            statement.close();
            resultSet.close();
            DBUtil.closeConnectionToBD();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return writers;
    }
}
