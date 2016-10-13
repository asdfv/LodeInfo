package lodeinfo.repository;

import lodeinfo.model.SMSEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SMSRepository {

//    Add +1 to Curdate()
    private static final String QUERY_SMS = "SELECT CONCAT_WS(' ',cus.customers_lastname,cus.customers_firstname,cus.customers_father) AS customers_fio, cp.phone_number, ct.cwd_workers_work_date, ct.cwd_workers_work_time_begin, CONCAT_WS(' ',ct.cwd_workers_lastname,ct.cwd_workers_firstname,ct.cwd_workers_father) AS worker_fio, cd_object.categories_name AS object_name, cd_spec.categories_name AS branch_name, cws.cwd_workers_specials_name AS special_name FROM cwd_timetable ct JOIN cwd_timetable_to_orders cto ON (ct.cwd_workers_work_id=cto.timetable_id AND cto.relation_active) JOIN orders o ON (cto.orders_id=o.orders_id) JOIN customers cus ON (o.customers_id=cus.customers_id) JOIN customers_phones cp ON (cus.customers_id=cp.customers_id AND cp.is_default) JOIN cwd_rooms cr ON (ct.cwd_w2r_rooms_id = cr.cwd_rooms_id) JOIN categories c ON (ct.cwd_workers_id = c.workers_id AND c.object_id=cr.categories_object_id) JOIN categories_description cd_spec ON (c.parent_id = cd_spec.categories_id) JOIN categories_description cd_object ON (cr.categories_object_id = cd_object.categories_id) JOIN cwd_workers cw ON (ct.cwd_workers_id=cw.cwd_workers_id) JOIN cwd_workers_specials cws ON (cw.cwd_workers_special_id=cws.cwd_workers_specials_id) WHERE ct.cwd_workers_work_date=CURDATE() + 1 AND cp.phone_number LIKE '+375_________' LIMIT 3";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<SMSEntity> getSMS(){
        return jdbcTemplate.query(QUERY_SMS, new RowMapper<SMSEntity>() {
            @Override
            public SMSEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                SMSEntity smsEntity = new SMSEntity();

                smsEntity.setCustomersFio(rs.getString("customers_fio"));
                smsEntity.setPhoneNumber(rs.getString("phone_number"));
                smsEntity.setCwdWorkersWorkDate(rs.getString("cwd_workers_work_date"));
                smsEntity.setCwdWorkersWorkTimeBegin(rs.getString("cwd_workers_work_time_begin"));
                smsEntity.setWorkerFio(rs.getString("worker_fio"));
                smsEntity.setObjectName(rs.getString("object_name"));
                smsEntity.setBranchName(rs.getString("branch_name"));
                smsEntity.setSpecialName(rs.getString("special_name"));
                smsEntity.setSmsText(smsEntity.toString());

                return smsEntity;
            }
        });
    }
}
