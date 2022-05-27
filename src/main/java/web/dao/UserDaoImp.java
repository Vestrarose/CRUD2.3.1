package web.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import javax.persistence.*;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }


    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    public void removeUserById(long id) {
        Query query = entityManager.createQuery("delete from User where id = :idCode");
        query.setParameter("idCode", id);
        query.executeUpdate();
    }


    public User showUserById(int id) {
        TypedQuery<User> q = entityManager.createQuery("select u from User u where u.id = " + id, User.class);

        return q.getSingleResult();

    }

    public void update(long id, User updateUser) {
        Query query = entityManager.createQuery("update User set firstName = :nameParam, lastName = :lastNameParam" +
                ", email = :emailParam" +
                " where id = :idCode");
        query.setParameter("idCode", id);
        query.setParameter("nameParam", updateUser.getFirstName());
        query.setParameter("lastNameParam", updateUser.getLastName());
        query.setParameter("emailParam", updateUser.getEmail());
        query.executeUpdate();

    }

}