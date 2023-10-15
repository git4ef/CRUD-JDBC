package ef.repository.DatabaseRepoImpl;

import ef.model.Post;
import ef.repository.PostRepository;
import ef.util.DBUtil;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.Instant.*;

public class PostRepoImpl implements PostRepository {
    @Override
    public Post save(Post post) {
        try {
            PreparedStatement preparedStatement = DBUtil.openConnectionToDB().prepareStatement(("INSERT INTO post (content,created,updated) VALUES (?,?,?)"),Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setLong(2, now().toEpochMilli());
            preparedStatement.setLong(3, 0L);
            preparedStatement.executeUpdate();
            ResultSet getIdPost = preparedStatement.getGeneratedKeys();
            while (getIdPost.next()){
                post.setId(getIdPost.getLong("id_post"));
            }
            preparedStatement.close();
            getIdPost.close();
            DBUtil.closeConnectionToBD();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public Post getById(Long aLong) {
        Post post = new Post();
        try {
            PreparedStatement preparedStatement = DBUtil.openConnectionToDB().prepareStatement("SELECT * FROM post WHERE id_post=?");
            preparedStatement.setLong(1, aLong);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                post.setId(resultSet.getLong("id_post"));
                post.setContent(resultSet.getString("content"));
                post.setCreated(resultSet.getLong("created"));
                post.setUpdated(resultSet.getLong("updated"));
            }
            resultSet.close();
            preparedStatement.close();
            DBUtil.closeConnectionToBD();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public Post update(Post post) {
        try {
            PreparedStatement preparedStatement = DBUtil.openConnectionToDB().prepareStatement("UPDATE post SET content=?, updated=? WHERE id_post=?");
            preparedStatement.setLong(3, post.getId());
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setLong(2, now().toEpochMilli());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            DBUtil.closeConnectionToBD();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void deleteById(Long aLong) {
        try {
            PreparedStatement preparedStatement = DBUtil.openConnectionToDB().prepareStatement("DELETE FROM post WHERE id_post=?");
            preparedStatement.setLong(1, aLong);
            preparedStatement.executeUpdate();
            DBUtil.closeConnectionToBD();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> postList = new ArrayList<>();
        try {
            Statement statement = DBUtil.openConnectionToDB().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM post");
            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getLong("id_post"));
                post.setContent(resultSet.getString("content"));
                post.setCreated(resultSet.getLong("created"));
                post.setUpdated(resultSet.getLong("updated"));
                postList.add(post);
            }
            statement.close();
            resultSet.close();
            DBUtil.closeConnectionToBD();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }
}
