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

    private static final String QUERY_VIP = "SELECT c.customers_id, c.customers_lastname, c.customers_firstname, SUM(ot.value * curr_orders.conv_coef) AS pay_sum, COUNT(o.orders_id) AS pay_count FROM customers c JOIN orders o ON(c.customers_id = o.customers_id) JOIN orders_total ot ON (o.orders_id = ot.orders_id AND ot.class = 'ot_total') JOIN ( SELECT dates.date, ch.currency_code AS from_currency, ch_conv.currency_code AS to_currency, ch_conv.value / ch.value AS conv_coef FROM ( SELECT CURDATE() - INTERVAL (a.a + (10 * b.a) + (100 * c.a) + (1000 * d.a)) DAY + INTERVAL 365 DAY AS `date` FROM (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS a CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS b CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS c CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS d) AS dates JOIN currencies_history ch ON (ch.active_from <= dates.date AND ch.active_to >= dates.date) JOIN currencies_history ch_conv ON ( ch_conv.active_from <= dates.date AND ch_conv.active_to >= dates.date AND ch_conv.currency_code = 'BYN' ) ) AS curr_orders ON (curr_orders.from_currency = o.currency_code AND curr_orders.date = DATE(o.last_modified)) WHERE o.orders_status = 5 AND o.payment_type IN ('payNal', 'payCard', 'payBeznal', 'payInsurance') GROUP BY c.customers_id HAVING pay_sum>=? AND pay_count>=? ORDER BY pay_sum DESC";
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
