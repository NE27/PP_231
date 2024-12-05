package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void updateUser(long id, User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void deleteAllUsers() {
        entityManager.createQuery("delete from User u").executeUpdate();
    }

    @Override
    @Transactional
    public void deleteUserById(long id) {
        entityManager.remove(getUserById(id));
    }
}
