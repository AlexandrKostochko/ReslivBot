package by.resliv.resource;

import by.resliv.entity.KeyResponse;
import by.resliv.entity.KeyResponseKeyModel;
import by.resliv.entity.KeyResponseModel;
import by.resliv.service.KeyResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class KeyResponseResource {

    @Autowired
    private KeyResponseService keyResponseService;

    @PostMapping
    public ResponseEntity<KeyResponse> addKeyResponse(@Valid @RequestBody KeyResponse keyResponse) {
        keyResponseService.saveKeyResponse(keyResponse);
        return new ResponseEntity<>(keyResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<KeyResponse> getKeyResponseById(@PathVariable("id") long id) {
        KeyResponse keyResponse = keyResponseService.getKeyResponseById(id);
        return new ResponseEntity<>(keyResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/name/{key}")
    public ResponseEntity<KeyResponse> getKeyResponseByKey(@PathVariable("key") String key) {
        KeyResponse keyResponse = keyResponseService.getKeyResponseByKey(key);
        return new ResponseEntity<>(keyResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<KeyResponse>> getAllKeyResponse() {
        List<KeyResponse> keyResponses = keyResponseService.getAllKeyResponse();
        return new ResponseEntity<>(keyResponses, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteKeyResponseById(@PathVariable("id") long id) {
        keyResponseService.deleteKeyResponseById(id);
        return new ResponseEntity<>("Delete was performed", HttpStatus.OK);
    }

    @DeleteMapping(path = "/name/{key}")
    public ResponseEntity<String> deleteKeyResponseByKey(@PathVariable("key") String key) {
        keyResponseService.deleteKeyResponseByKey(key);
        return new ResponseEntity<>("Delete was performed", HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateKeyResponseById(@PathVariable("id") long id, @Valid @RequestBody KeyResponseModel keyResponseModel) {
        keyResponseService.updateKeyResponseById(id, keyResponseModel);
        return new ResponseEntity<>("is update", HttpStatus.OK);
    }

    @PutMapping(path = "/name/{key}")
    public ResponseEntity<String> updateKeyResponseByKey(@PathVariable("key") String key, @Valid @RequestBody KeyResponseModel keyResponseModel) {
        keyResponseService.updateKeyResponseByKey(key, keyResponseModel);
        return new ResponseEntity<>("is update", HttpStatus.OK);
    }

    @PutMapping(path = "/updateKey/{id}")
    public ResponseEntity<String> updateKeyResponseKeyById(@PathVariable("id") long id, @Valid @RequestBody KeyResponseKeyModel keyResponseKeyModel) {
        keyResponseService.updateKeyResponseKeyById(id, keyResponseKeyModel);
        return new ResponseEntity<>("is update", HttpStatus.OK);
    }

    @PutMapping(path = "/updateKey/name/{key}")
    public ResponseEntity<String> updateKeyResponseKeyByKey(@PathVariable("key") String key, @Valid @RequestBody KeyResponseKeyModel keyResponseKeyModel) {
        keyResponseService.updateKeyResponseKeyByKey(key, keyResponseKeyModel);
        return new ResponseEntity<>("is update", HttpStatus.OK);
    }
}
