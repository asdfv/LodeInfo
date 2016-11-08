package lodeinfo.repository;

import lodeinfo.model.VipEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VipRepository {

    private static final String QUERY_VIP = "SELECT c.customers_id, c.customers_lastname, c.customers_firstname, SUM(ConvertCurrency(ot.value, o.currency_code, 'BYN', DATE(o.last_modified))) AS pay_sum, COUNT(o.orders_id) AS pay_count FROM customers c JOIN orders o ON (c.customers_id=o.customers_id) JOIN orders_total ot ON (o.orders_id=ot.orders_id AND ot.class='ot_total') WHERE o.orders_status=5 AND o.payment_type IN ('payNal','payCard','payBeznal','payInsurance') GROUP BY c.customers_id HAVING pay_sum>=? AND pay_count>=? ORDER BY pay_sum DESC";
//    private static final String QUERY_VIP_COUNT = "SELECT COUNT(*) FROM (SELECT c.customers_id, c.customers_lastname, c.customers_firstname, SUM(ConvertCurrency(ot.value, o.currency_code, 'BYN', DATE(o.last_modified))) AS pay_sum, COUNT(o.orders_id) AS pay_count FROM customers c JOIN orders o ON (c.customers_id=o.customers_id) JOIN orders_total ot ON (o.orders_id=ot.orders_id AND ot.class='ot_total') WHERE o.orders_status=5 AND o.payment_type IN ('payNal','payCard','payBeznal','payInsurance') GROUP BY c.customers_id HAVING pay_sum>=? AND pay_count>=? ) final;";

    @Autowired
    @Qualifier("jdbcMegamag")
    JdbcTemplate jdbcTemplateMegamag;

//    public int getVipCount(int spent, int payments) {
//        int count = jdbcTemplateMegamag.queryForObject(
//                QUERY_VIP_COUNT, new Object[] { spent, payments }, Integer.class);
//        return count;
//    }

    public List<VipEntity> getVipList(int spent, int payments) {
        return jdbcTemplateMegamag.query(QUERY_VIP, new Object[] { spent, payments }, new RowMapper<VipEntity>() {
            @Override
            public VipEntity mapRow(ResultSet rs, int i) throws SQLException {
                VipEntity vip = new VipEntity();
                vip.setCustomersId(rs.getLong("customers_id"));
                vip.setCustomersLastname(rs.getString("customers_lastname"));
                vip.setCustomersFirstname(rs.getString("customers_firstname"));
                vip.setPaySum(rs.getInt("pay_sum"));
                vip.setPayCount(rs.getInt("pay_count"));
                return vip;
            }
        });
    }

}
