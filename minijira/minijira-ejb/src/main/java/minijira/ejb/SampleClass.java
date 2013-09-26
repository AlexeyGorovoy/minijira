package minijira.ejb;

import minijira.ejb.database.model.Customer;
import minijira.ejbapi.SampleInterface;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by  Alexey Gorovoy
 * Date:    06.09.13
 * Time:    16:19
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Local( SampleInterface.class )
@Stateless
public class SampleClass implements SampleInterface {
    @PersistenceContext
    EntityManager em;

    @Override
    public String doSomething() {
        // Working variant
        Query query = em.createNamedQuery("Customer.findAll");
        List<Customer> customers = query.getResultList();
        Customer customer = customers.get(0);
        return customer.getTitle() + ". " + customer.getDescription();
    }
}
