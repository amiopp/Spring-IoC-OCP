package dao;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Profile;

@Component("dao")
@Profile("prod")
public class DaoImpl implements IDao {
    @Override
    public double getValue() {
        return 100.0;
    }
}
