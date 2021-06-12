package by.resliv.dao;

import by.resliv.entity.KeyResponse;
import by.resliv.entity.KeyResponseKeyModel;
import by.resliv.entity.KeyResponseModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Repository
@Transactional
public class KeyResponseDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public String getResponseByKey(String key) {
        Session currentSession = sessionFactory.getCurrentSession();
        KeyResponse keyResponse = currentSession
                .createQuery("from KeyResponse where lower(key) = :key", KeyResponse.class)
                .setParameter("key", key.toLowerCase(Locale.ROOT).trim())
                .getSingleResult();
        return keyResponse.getResponse();
    }

    @Transactional(readOnly = true)
    public boolean existResponseByKey(String key) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession
                .createQuery("from KeyResponse where lower(key) = :key", KeyResponse.class)
                .setParameter("key", key.toLowerCase(Locale.ROOT).trim())
                .uniqueResult() != null;
    }

    @Transactional(readOnly = true)
    public List<KeyResponse> getAllKeyResponse() {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.createQuery("from KeyResponse", KeyResponse.class).list();
    }

    public void saveKeyResponse(KeyResponse keyResponse) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(keyResponse);
    }

    @Transactional(readOnly = true)
    public KeyResponse getKeyResponseById(long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession
                .createQuery("from KeyResponse where id = :id", KeyResponse.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional(readOnly = true)
    public boolean existKeyResponseById(long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession
                .createQuery("from KeyResponse where id = :id", KeyResponse.class)
                .setParameter("id", id)
                .uniqueResult() != null;
    }

    @Transactional(readOnly = true)
    public KeyResponse getKeyResponseByKey(String key) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession
                .createQuery("from KeyResponse where lower(key) = :key", KeyResponse.class)
                .setParameter("key", key.toLowerCase(Locale.ROOT).trim())
                .getSingleResult();
    }

    public void deleteKeyResponseById(long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(getKeyResponseById(id));
    }

    public void deleteKeyResponseByKey(String key) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(getKeyResponseByKey(key));
    }

    public void updateKeyResponseById(long id, KeyResponseModel keyResponseModel) {
        Session currentSession = sessionFactory.getCurrentSession();
        KeyResponse keyResponse = getKeyResponseById(id);
        keyResponse.setResponse(keyResponseModel.getResponse());
        currentSession.update(keyResponse);
    }

    public void updateKeyResponseByKey(String key, KeyResponseModel keyResponseModel) {
        Session currentSession = sessionFactory.getCurrentSession();
        KeyResponse keyResponse = getKeyResponseByKey(key);
        keyResponse.setResponse(keyResponseModel.getResponse());
        currentSession.update(keyResponse);
    }

    public void updateKeyResponseKeyById(long id, KeyResponseKeyModel keyResponseKeyModel) {
        Session currentSession = sessionFactory.getCurrentSession();
        KeyResponse keyResponse = getKeyResponseById(id);
        keyResponse.setKey(keyResponseKeyModel.getKey());
        currentSession.update(keyResponse);
    }

    public void updateKeyResponseKeyByKey(String key, KeyResponseKeyModel keyResponseKeyModel) {
        Session currentSession = sessionFactory.getCurrentSession();
        KeyResponse keyResponse = getKeyResponseByKey(key);
        keyResponse.setKey(keyResponseKeyModel.getKey());
        currentSession.update(keyResponse);
    }
}
