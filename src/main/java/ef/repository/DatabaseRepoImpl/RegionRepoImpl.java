package ef.repository.DatabaseRepoImpl;

import ef.util.DBUtil;
import ef.model.Region;
import ef.repository.RegionRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RegionRepoImpl implements RegionRepository {

    @Override
    public Region save(Region region) {
        try {
            PreparedStatement preparedStatement = DBUtil.openConnectionToDB().prepareStatement(("INSERT INTO region (name) VALUES (?)"), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, region.getName());
            preparedStatement.executeUpdate();
            ResultSet getGeneratedId = preparedStatement.getGeneratedKeys();
            while (getGeneratedId.next()) {
                region.setId(getGeneratedId.getLong("id_region"));
            }
            getGeneratedId.close();
            DBUtil.closeConnectionToBD();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return region;
    }

    @Override
    public Region getById(Long aLong) {
        Region region = new Region();
        try {
            PreparedStatement preparedStatement = DBUtil.openConnectionToDB().prepareStatement("SELECT * FROM region WHERE id_region=?");
            preparedStatement.setLong(1, aLong);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                region.setId(resultSet.getLong("id_region"));
                region.setName(resultSet.getString("name"));
            }
            resultSet.close();
            preparedStatement.close();
            DBUtil.closeConnectionToBD();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return region;
    }

    @Override
    public Region update(Region region) {
        try {
            PreparedStatement preparedStatement = DBUtil.openConnectionToDB().prepareStatement("UPDATE region SET name=? WHERE id_region=?");
            preparedStatement.setLong(2, region.getId());
            preparedStatement.setString(1, region.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            DBUtil.closeConnectionToBD();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return region;
    }

    @Override
    public void deleteById(Long aLong) {
        try {
            PreparedStatement preparedStatement = DBUtil.openConnectionToDB().prepareStatement("DELETE FROM region WHERE id_region=?");
            preparedStatement.setLong(1, aLong);
            preparedStatement.executeUpdate();
            DBUtil.closeConnectionToBD();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Region> getAll() {
        List<Region> regionList = new ArrayList<>();
        try {
            Statement statement = DBUtil.openConnectionToDB().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM region");
            while (resultSet.next()) {
                Region region = new Region();
                region.setId(resultSet.getLong("id_region"));
                region.setName(resultSet.getString("name"));
                regionList.add(region);
            }
            statement.close();
            resultSet.close();
            DBUtil.closeConnectionToBD();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return regionList;
    }
}

