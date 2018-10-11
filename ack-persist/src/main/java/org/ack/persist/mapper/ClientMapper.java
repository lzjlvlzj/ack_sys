package org.ack.persist.mapper;

        import org.ack.persist.AckMapper;
        import org.ack.pojo.Client;
        import org.ack.pojo.User;

        import java.util.List;

/**
 * 客户数据库接口
 *
 * @author ack
 */
public interface ClientMapper extends AckMapper<Client, Integer> {
    /**
     * 查询用户负责的客户信息
     * @param user
     * @return
     */
    List<Client> findClientByUser(User user);
}
