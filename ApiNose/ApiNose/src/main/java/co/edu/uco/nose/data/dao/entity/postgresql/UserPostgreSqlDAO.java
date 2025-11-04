package co.edu.uco.nose.data.dao.entity.postgresql;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.nose.crosscuting.messagecatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.entity.CityEntity;
import co.edu.uco.nose.entity.IdTypeEntity;
import co.edu.uco.nose.entity.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class UserPostgreSqlDAO extends SqlConnection implements UserDAO {

    public UserPostgreSqlDAO(final Connection connection) {
        super(connection);
    }

    // ---------------------------------------------------------------------------------------------
    // CREATE
    // ---------------------------------------------------------------------------------------------
    @Override
    public void create(final UserEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("INSERT INTO classdoo.users(");
        sql.append("id, id_type, id_number, first_name, second_name, first_surname, second_surname, ");
        sql.append("home_city, email, mobile_number, email_confirmed, mobile_number_confirmed) ");
        sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        try (final PreparedStatement ps = getConnection().prepareStatement(sql.toString())) {

            ps.setObject(1, entity.getId());
            ps.setObject(2, entity.getIdType() != null ? entity.getIdType().getId() : null);
            ps.setString(3, entity.getIdNumber());
            ps.setString(4, entity.getFirstName());
            ps.setString(5, entity.getSecondName());
            ps.setString(6, entity.getFirstSurname());
            ps.setString(7, entity.getSecondSurname());
            ps.setObject(8, entity.getHomeCity() != null ? entity.getHomeCity().getId() : null);
            ps.setString(9, entity.getEmail());
            ps.setString(10, entity.getMobileNumber());
            ps.setBoolean(11, entity.isEmailConfirmed());
            ps.setBoolean(12, entity.isMobileNumberConfirmed());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw NoseException.create(e,
                    MessagesEnum.USER_ERROR_SQL_CREATE.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_SQL_CREATE.getContent());
        }
    }

    // ---------------------------------------------------------------------------------------------
    // FIND ALL
    // ---------------------------------------------------------------------------------------------
    @Override
    public List<UserEntity> findAll() {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("SELECT id, id_type, id_number, first_name, second_name, first_surname, second_surname, ");
        sql.append("home_city, email, mobile_number, email_confirmed, mobile_number_confirmed ");
        sql.append("FROM classdoo.users");

        final List<UserEntity> users = new ArrayList<>();

        try (final PreparedStatement ps = getConnection().prepareStatement(sql.toString());
             final ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                var user = new UserEntity();

                user.setId((UUID) rs.getObject("id"));
                user.setIdNumber(rs.getString("id_number"));
                user.setFirstName(rs.getString("first_name"));
                user.setSecondName(rs.getString("second_name"));
                user.setFirstSurname(rs.getString("first_surname"));
                user.setSecondSurname(rs.getString("second_surname"));
                user.setEmail(rs.getString("email"));
                user.setMobileNumber(rs.getString("mobile_number"));
                user.setEmailConfirmed(rs.getBoolean("email_confirmed"));
                user.setMobileNumberConfirmed(rs.getBoolean("mobile_number_confirmed"));

                // Relaciones
                var idType = new IdTypeEntity();
                idType.setId((UUID) rs.getObject("id_type"));
                user.setIdType(idType);

                var city = new CityEntity();
                city.setId((UUID) rs.getObject("home_city"));
                user.setHomeCity(city);

                users.add(user);
            }

        } catch (SQLException e) {
            throw NoseException.create(e,
                    MessagesEnum.USER_ERROR_SQL_FIND_ALL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_SQL_FIND_ALL.getContent());
        }

        return users;
    }

    // ---------------------------------------------------------------------------------------------
    // FIND BY FILTER
    // ---------------------------------------------------------------------------------------------
    @Override
    public List<UserEntity> findByFilter(final UserEntity filterEntity) {
        if (filterEntity == null) {
            return findAll();
        }

        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder("SELECT * FROM classdoo.users WHERE 1=1 ");
        final List<Object> parameters = new ArrayList<>();

        if (filterEntity.getFirstName() != null && !filterEntity.getFirstName().isEmpty()) {
            sql.append("AND first_name ILIKE ? ");
            parameters.add("%" + filterEntity.getFirstName() + "%");
        }

        if (filterEntity.getEmail() != null && !filterEntity.getEmail().isEmpty()) {
            sql.append("AND email ILIKE ? ");
            parameters.add("%" + filterEntity.getEmail() + "%");
        }

        final List<UserEntity> users = new ArrayList<>();

        try (final PreparedStatement ps = getConnection().prepareStatement(sql.toString())) {

            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }

            try (final ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    var user = new UserEntity();
                    user.setId((UUID) rs.getObject("id"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setSecondName(rs.getString("second_name"));
                    user.setFirstSurname(rs.getString("first_surname"));
                    user.setSecondSurname(rs.getString("second_surname"));
                    user.setEmail(rs.getString("email"));
                    user.setMobileNumber(rs.getString("mobile_number"));
                    user.setEmailConfirmed(rs.getBoolean("email_confirmed"));
                    user.setMobileNumberConfirmed(rs.getBoolean("mobile_number_confirmed"));

                    var idType = new IdTypeEntity();
                    idType.setId((UUID) rs.getObject("id_type"));
                    user.setIdType(idType);

                    var city = new CityEntity();
                    city.setId((UUID) rs.getObject("home_city"));
                    user.setHomeCity(city);

                    users.add(user);
                }
            }

        } catch (SQLException e) {
            throw NoseException.create(e,
                    MessagesEnum.USER_ERROR_FIND_BY_FILTER_SQL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_FILTER_SQL.getContent());
        }

        return users;
    }

    // ---------------------------------------------------------------------------------------------
    // FIND BY ID
    // ---------------------------------------------------------------------------------------------
    @Override
    public UserEntity findById(final UUID id) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = "SELECT * FROM classdoo.users WHERE id = ?";

        try (final PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setObject(1, id);

            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    var user = new UserEntity();
                    user.setId((UUID) rs.getObject("id"));
                    user.setIdNumber(rs.getString("id_number"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setSecondName(rs.getString("second_name"));
                    user.setFirstSurname(rs.getString("first_surname"));
                    user.setSecondSurname(rs.getString("second_surname"));
                    user.setEmail(rs.getString("email"));
                    user.setMobileNumber(rs.getString("mobile_number"));
                    user.setEmailConfirmed(rs.getBoolean("email_confirmed"));
                    user.setMobileNumberConfirmed(rs.getBoolean("mobile_number_confirmed"));

                    var idType = new IdTypeEntity();
                    idType.setId((UUID) rs.getObject("id_type"));
                    user.setIdType(idType);

                    var city = new CityEntity();
                    city.setId((UUID) rs.getObject("home_city"));
                    user.setHomeCity(city);

                    return user;
                }
            }

        } catch (SQLException e) {
            throw NoseException.create(e,
                    MessagesEnum.USER_ERROR_FIND_BY_ID_SQL.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_FIND_BY_ID_SQL.getContent());
        }

        return null;
    }

    // ---------------------------------------------------------------------------------------------
    // UPDATE
    // ---------------------------------------------------------------------------------------------
    @Override
    public void update(final UserEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("UPDATE classdoo.users SET id_type=?, id_number=?, first_name=?, second_name=?, ");
        sql.append("first_surname=?, second_surname=?, home_city=?, email=?, mobile_number=?, ");
        sql.append("email_confirmed=?, mobile_number_confirmed=? WHERE id=?");

        try (final PreparedStatement ps = getConnection().prepareStatement(sql.toString())) {

            ps.setObject(1, entity.getIdType() != null ? entity.getIdType().getId() : null);
            ps.setString(2, entity.getIdNumber());
            ps.setString(3, entity.getFirstName());
            ps.setString(4, entity.getSecondName());
            ps.setString(5, entity.getFirstSurname());
            ps.setString(6, entity.getSecondSurname());
            ps.setObject(7, entity.getHomeCity() != null ? entity.getHomeCity().getId() : null);
            ps.setString(8, entity.getEmail());
            ps.setString(9, entity.getMobileNumber());
            ps.setBoolean(10, entity.isEmailConfirmed());
            ps.setBoolean(11, entity.isMobileNumberConfirmed());
            ps.setObject(12, entity.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw NoseException.create(e,
                    MessagesEnum.USER_ERROR_SQL_UPDATE.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_SQL_UPDATE.getContent());
        }
    }

    // ---------------------------------------------------------------------------------------------
    // DELETE
    // ---------------------------------------------------------------------------------------------
    @Override
    public void delete(final UUID id) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = "DELETE FROM classdoo.users WHERE id = ?";

        try (final PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw NoseException.create(e,
                    MessagesEnum.USER_ERROR_SQL_DELETE.getContent(),
                    MessagesEnum.TECHNICAL_ERROR_SQL_DELETE.getContent());
        }
    }
}
