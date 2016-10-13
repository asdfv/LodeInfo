package lodeinfo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VipRepository {

    private static final String QUERY_VIP = "SELECT c.customers_id, c.customers_lastname, c.customers_firstname, SUM(ConvertCurrency(ot.value, o.currency_code, 'BYN', DATE(o.last_modified))) AS pay_sum, COUNT(o.orders_id) AS pay_count FROM customers c JOIN orders o ON (c.customers_id=o.customers_id) JOIN orders_total ot ON (o.orders_id=ot.orders_id AND ot.class='ot_total') WHERE o.orders_status=5 AND o.payment_type IN ('payNal','payCard','payBeznal','payInsurance') GROUP BY c.customers_id HAVING pay_sum>=:spent AND pay_count>=:payments limit 100;";
    private static final String QUERY_VIP_COUNT = "SELECT COUNT(*) FROM (SELECT c.customers_id, c.customers_lastname, c.customers_firstname, SUM(ConvertCurrency(ot.value, o.currency_code, 'BYN', DATE(o.last_modified))) AS pay_sum, COUNT(o.orders_id) AS pay_count FROM customers c JOIN orders o ON (c.customers_id=o.customers_id) JOIN orders_total ot ON (o.orders_id=ot.orders_id AND ot.class='ot_total') WHERE o.orders_status=5 AND o.payment_type IN ('payNal','payCard','payBeznal','payInsurance') GROUP BY c.customers_id HAVING pay_sum>=? AND pay_count>=? ) final;";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int getVipCount(int spent, int payments) {
        int count = jdbcTemplate.queryForObject(
                QUERY_VIP_COUNT, new Object[] { spent, payments }, Integer.class);
        return count;
    }

}
