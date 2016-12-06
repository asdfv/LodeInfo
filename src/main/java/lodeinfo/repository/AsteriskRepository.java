package lodeinfo.repository;

import lodeinfo.model.CdrEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AsteriskRepository {

//    Month stats query
    private final static String MISSED_COUNT = "select count(event) as 'countMiss' from queue_log where time like concat(?,'%') and event = 'ABANDON' and data3 > 15;";
    private final static String INCOMING_COUNT = "select count(event) as 'countIn' from queue_log where time like concat(?,'%') and event = 'ENTERQUEUE';";
    private final static String ANSWERED_COUNT = "select count(event) as 'countAns' from queue_log where time like concat(?, '%') and event = 'CONNECT';";
    private final static String AVG_WAIT_TIME = "select avg(data1) as 'avgWaitTime' from queue_log where time like concat(?,'%') and event = 'CONNECT';";

//    Day stats query
    private final static String MISSED_BY_HOUR = "select count(event) as 'countMiss' from queue_log where time like concat(?,'%') and hour(time) = ? and event = 'ABANDON' and data3 > 15;";
    private final static String INCOMING_BY_HOUR = "select count(event) as 'countIn' from queue_log where time like concat(?,'%') and hour(time) = ? and event = 'ENTERQUEUE';";
    private final static String OUTGOING_BY_HOUR = "select count(*) as 'countOut' from cdr WHERE calldate like concat(?, '%') and ( dstchannel like 'DAHDI%' or dstchannel like 'SIP/dinstar-trunk-gsm%') and hour(calldate) = ?;";
    private final static String CALL_DURATION_BY_HOUR = "select avg(data2) as 'countDur' from queue_log where time like concat(?,'%') and hour(time) = ? and (event = 'COMPLETEAGENT' or event = 'COMPLETECALLER');";

//    CDR
    private final static String CDR_FIND_BY_SRC_AND_DST = "select * from cdr where dst like concat('%', ?, '%') or src like concat('%', ?, '%') order by calldate desc limit 500";
    private final static String CDR_FIND_BY_CALLDATE = "select * from cdr where calldate like concat(?, '%')";



    @Autowired
    @Qualifier("jdbcAsterisk")
    JdbcTemplate jdbcAsterisk;

    // For month, daily
    public int missedCountForDay(String day) {
        int missCount = jdbcAsterisk.queryForObject(
                MISSED_COUNT, new Object[]{day}, Integer.class);
        return missCount;
    }

    public int incomingCountForDay(String day) {
        int incomingCount = jdbcAsterisk.queryForObject(
                INCOMING_COUNT, new Object[]{day}, Integer.class);
        return incomingCount;
    }

    public int answeredCountForDay(String day) {
        int answeredCount = jdbcAsterisk.queryForObject(
                ANSWERED_COUNT, new Object[]{day}, Integer.class);
        return answeredCount;
    }

    public int avgTimeForDay(String day) {
        Object obj = jdbcAsterisk.queryForObject(
                AVG_WAIT_TIME, new Object[]{day}, Integer.class);
        if (obj != null) return Integer.parseInt(obj.toString());
        else return 0;
    }


    // For day, hourly
    public int missedCountForHour(String day, int hour) {
        int missCount = jdbcAsterisk.queryForObject(
                MISSED_BY_HOUR, new Object[]{day, hour}, Integer.class);
        return missCount;
    }

    public int incomingCountForHour(String day, int hour) {
        int incomingCount = jdbcAsterisk.queryForObject(
                INCOMING_BY_HOUR, new Object[]{day, hour}, Integer.class);
        return incomingCount;
    }

    public int outgoingCountForHour(String day, int hour) {
        int outgoingCount = jdbcAsterisk.queryForObject(
                OUTGOING_BY_HOUR, new Object[]{day, hour}, Integer.class);
        return outgoingCount;
    }

    public int callDurationForHour(String day, int hour) {
        Object obj = jdbcAsterisk.queryForObject(
                CALL_DURATION_BY_HOUR, new Object[]{day, hour}, Integer.class);
        if (obj != null) return Integer.parseInt(obj.toString());
        else return 0;
    }

    // CDR
    public List<CdrEntity> findCdrEntityBySrcAndDst(String numberToSearch) {
        return jdbcAsterisk.query(CDR_FIND_BY_SRC_AND_DST, new Object[] { numberToSearch, numberToSearch }, new RowMapper<CdrEntity>() {
            @Override
            public CdrEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                CdrEntity cdr = new CdrEntity();

                cdr.setRecordId(resultSet.getLong("record_id"));
                cdr.setCalldate(resultSet.getString("calldate"));
                cdr.setClid(resultSet.getString("clid"));
                cdr.setClid(resultSet.getString("clid"));
                cdr.setSrc(resultSet.getString("src"));
                cdr.setDst(resultSet.getString("dst"));
                cdr.setDcontext(resultSet.getString("dcontext"));
                cdr.setChannel(resultSet.getString("channel"));
                cdr.setDstchannel(resultSet.getString("dstchannel"));
                cdr.setLastapp(resultSet.getString("lastapp"));
                cdr.setLastdata(resultSet.getString("lastdata"));
                cdr.setDuration(resultSet.getInt("duration"));
                cdr.setBillsec(resultSet.getInt("billsec"));
                cdr.setDisposition(resultSet.getString("disposition"));
                cdr.setUniqueid(resultSet.getString("uniqueid"));

                return cdr;
            }
        });
    }

    public List<CdrEntity> findCdrByCalldate(String dayToSearch) {
        return jdbcAsterisk.query(CDR_FIND_BY_CALLDATE, new Object[] { dayToSearch }, new RowMapper<CdrEntity>() {

            @Override
            public CdrEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                CdrEntity cdr = new CdrEntity();

                cdr.setRecordId(resultSet.getLong("record_id"));
                cdr.setCalldate(resultSet.getString("calldate"));
                cdr.setClid(resultSet.getString("clid"));
                cdr.setClid(resultSet.getString("clid"));
                cdr.setSrc(resultSet.getString("src"));
                cdr.setDst(resultSet.getString("dst"));
                cdr.setDcontext(resultSet.getString("dcontext"));
                cdr.setChannel(resultSet.getString("channel"));
                cdr.setDstchannel(resultSet.getString("dstchannel"));
                cdr.setLastapp(resultSet.getString("lastapp"));
                cdr.setLastdata(resultSet.getString("lastdata"));
                cdr.setDuration(resultSet.getInt("duration"));
                cdr.setBillsec(resultSet.getInt("billsec"));
                cdr.setDisposition(resultSet.getString("disposition"));
                cdr.setUniqueid(resultSet.getString("uniqueid"));

                return cdr;
            }
        });
    }


}
