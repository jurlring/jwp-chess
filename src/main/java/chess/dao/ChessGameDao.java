package chess.dao;

import chess.entity.ChessGameEntity;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ChessGameDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ChessGameDao(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Number save(final ChessGameEntity chessGameEntity) {
        String insertSql = "insert into chess_game (name, password, power, team_value_of_turn)"
                + " values (:name, :password, :power, :teamValueOfTurn)";
        SqlParameterSource source = new BeanPropertySqlParameterSource(chessGameEntity);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertSql, source, keyHolder);
        return keyHolder.getKey();
    }

    public void delete(final ChessGameEntity chessGameEntity) {
        String deleteSql = "delete from chess_game where id=:id and password=:password";
        SqlParameterSource source = new BeanPropertySqlParameterSource(chessGameEntity);
        int deleteSize = namedParameterJdbcTemplate.update(deleteSql, source);
        if (deleteSize == 0) {
            throw new IllegalArgumentException("[ERROR] 비밀번호가 잘못되었습니다!");
        }
    }

    public ChessGameEntity load(final long id) {
        String selectSql = "select * from chess_game where id=:id";
        SqlParameterSource source = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.queryForObject(
                selectSql,
                source,
                getChessGameEntityRowMapper());
    }

    public List<ChessGameEntity> loadAll() {
        String loadAllSql = "select * from chess_game";
        return namedParameterJdbcTemplate.query(loadAllSql, getChessGameEntityRowMapper());
    }

    private RowMapper<ChessGameEntity> getChessGameEntityRowMapper() {
        return (rs, rn) -> new ChessGameEntity(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("password"),
                rs.getBoolean("power"),
                rs.getString("team_value_of_turn")
        );
    }

    public void updatePowerAndTurn(final ChessGameEntity chessGameEntity) {
        String updateSql = "update chess_game set power=:power, team_value_of_turn=:teamValueOfTurn where id=:id";
        SqlParameterSource source = new BeanPropertySqlParameterSource(chessGameEntity);
        namedParameterJdbcTemplate.update(updateSql, source);
    }

    public void updatePower(final ChessGameEntity chessGameEntity) {
        String updateSql = "update chess_game set power=:power where id=:id";
        SqlParameterSource source = new BeanPropertySqlParameterSource(chessGameEntity);
        namedParameterJdbcTemplate.update(updateSql, source);
    }
}
