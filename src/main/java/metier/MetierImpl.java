package metier;

import dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("metier")
public class MetierImpl implements IMetier {

    @Autowired
    private IDao dao;

    @Override
    public double calcul() {
        return dao.getValue() * 2;
    }

    // Optional: Trace to see what was injected
    // @PostConstruct
    // public void init() {
    // System.out.println("[TRACE] DAO injected: " +
    // dao.getClass().getSimpleName());
    // }
}
