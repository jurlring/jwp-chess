package chess.repository.spring;

import chess.repository.entity.ChessGameEntity;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class ChessGameDao {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ChessGameDao(JdbcTemplate jdbcTemplate,
                        NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void save(final ChessGameEntity chessGameEntity) {
        String insertSql = "insert into chess_game (name, is_on, team_value_of_turn) values (:name, :isOn, :teamValueOfTurn)";
        SqlParameterSource source = new BeanPropertySqlParameterSource(chessGameEntity);
        namedParameterJdbcTemplate.update(insertSql, source);
    }

    public void delete(final String name) {
        String deleteSql = "delete from chess_game where name=:name";
        SqlParameterSource source = new MapSqlParameterSource("name", name);
        namedParameterJdbcTemplate.update(deleteSql, source);
    }

    public ChessGameEntity load(final String name) {
        String selectSql = "select name, is_on, team_value_of_turn from chess_game where name=:name";
        SqlParameterSource source = new MapSqlParameterSource("name", name);
        return namedParameterJdbcTemplate.queryForObject(
                selectSql,
                source,
                (rs, rn) -> new ChessGameEntity(
                        rs.getString("name"),
                        rs.getBoolean("is_on"),
                        rs.getString("team_value_of_turn"
                        )
                ));
    }

    public List<ChessGameEntity> loadAll() {
        String loadAllSql = "select * from chess_game";
        return namedParameterJdbcTemplate.query(loadAllSql,
                (rs, rn) -> new ChessGameEntity(
                        rs.getString("name"),
                        rs.getBoolean("is_on"),
                        rs.getString("team_value_of_turn")
                ));
    }
}
