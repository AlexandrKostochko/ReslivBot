package by.resliv.service;

import by.resliv.dao.KeyResponseDao;
import by.resliv.entity.KeyResponse;
import by.resliv.entity.KeyResponseKeyModel;
import by.resliv.entity.KeyResponseModel;
import by.resliv.service.exception.KeyResponseExistException;
import by.resliv.service.exception.KeyResponseNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyResponseService {

    @Autowired
    private KeyResponseDao keyResponseDao;

    public String response(String key) {
        if (keyResponseDao.existResponseByKey(key)) {
            return keyResponseDao.getResponseByKey(key);
        } else {
            return "Такого города у нас нет";
        }
    }

    public String keys() {
        if (!keyResponseDao.getAllKeyResponse().isEmpty()) {
            StringBuilder keys = new StringBuilder();
            for (KeyResponse keyResponse : keyResponseDao.getAllKeyResponse()) {
                keys.append(keyResponse.getKey()).append(" ");
            }
            return keys.toString();
        } else {
            return "К сожалению городов нет(";
        }
    }

    public void saveKeyResponse(KeyResponse keyResponse) {
        if (!keyResponseDao.existResponseByKey(keyResponse.getKey())) {
            keyResponseDao.saveKeyResponse(keyResponse);
        } else {
            throw new KeyResponseExistException("Данный город уже существует");
        }
    }

    public KeyResponse getKeyResponseById(long id) {
        if (keyResponseDao.existKeyResponseById(id)) {
            return keyResponseDao.getKeyResponseById(id);
        } else {
            throw new KeyResponseNotExistException("Город с таким id не существует");
        }
    }

    public KeyResponse getKeyResponseByKey(String key){
        if(keyResponseDao.existResponseByKey(key)) {
            return keyResponseDao.getKeyResponseByKey(key);
        } else {
            throw new KeyResponseNotExistException("Город с таким именем не существует");
        }
    }

    public List<KeyResponse> getAllKeyResponse() {
        if(!keyResponseDao.getAllKeyResponse().isEmpty()) {
            return keyResponseDao.getAllKeyResponse();
        } else {
            throw new KeyResponseNotExistException("Городов не существует");
        }
    }

    public void deleteKeyResponseById(long id) {
        if(keyResponseDao.existKeyResponseById(id)) {
            keyResponseDao.deleteKeyResponseById(id);
        } else {
            throw new KeyResponseNotExistException("Город с таким id не существует");
        }
    }

    public void deleteKeyResponseByKey(String key) {
        if(keyResponseDao.existResponseByKey(key)) {
            keyResponseDao.deleteKeyResponseByKey(key);
        } else {
            throw new KeyResponseNotExistException("Город с таким именем не существует");
        }
    }

    public void updateKeyResponseById(long id, KeyResponseModel keyResponseModel) {
        if(keyResponseDao.existKeyResponseById(id)) {
            keyResponseDao.updateKeyResponseById(id, keyResponseModel);
        } else {
            throw new KeyResponseNotExistException("Город с таким id не существует");
        }
    }

    public void updateKeyResponseByKey(String key, KeyResponseModel keyResponseModel) {
        if(keyResponseDao.existResponseByKey(key)) {
            keyResponseDao.updateKeyResponseByKey(key, keyResponseModel);
        } else {
            throw new KeyResponseNotExistException("Город с таким именем не существует");
        }
    }

    public void updateKeyResponseKeyById(long id, KeyResponseKeyModel keyResponseKeyModel) {
        if(keyResponseDao.existResponseByKey(keyResponseKeyModel.getKey())) {
            throw new KeyResponseExistException("Данный город уже существует");
        } else {
            if(keyResponseDao.existKeyResponseById(id)) {
                keyResponseDao.updateKeyResponseKeyById(id, keyResponseKeyModel);
            } else {
                throw new KeyResponseNotExistException("Город с таким id не существует");
            }
        }
    }

    public void updateKeyResponseKeyByKey(String key, KeyResponseKeyModel keyResponseKeyModel) {
        if(keyResponseDao.existResponseByKey(keyResponseKeyModel.getKey())) {
            throw new KeyResponseExistException("Данный город уже существует");
        } else {
            if(keyResponseDao.existResponseByKey(key)) {
                keyResponseDao.updateKeyResponseKeyByKey(key, keyResponseKeyModel);
            } else {
                throw new KeyResponseNotExistException("Город с таким именем не существует");
            }
        }
    }
}
