package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Transactional(rollbackFor = NoResultException.class)
   @Override
   public List<User> getUserByCar(String model, int series) {
      return sessionFactory.openSession().createQuery
              ("from User user join fetch user.car where user.car.model=:model and user.car.series=:series", User.class)
              .setParameter("model", model)
              .setParameter("series", series)
              .getResultList();
   }
}
