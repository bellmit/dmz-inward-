package com.dmz.basic.implement;

import com.dmz.basic.document.UserDocument;
import com.dmz.basic.idao.IUserDocumentDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author dmz
 * @date 2017/8/17
 */
@Repository
public class UserDocumentDao implements IUserDocumentDao {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public UserDocument findByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        UserDocument user = mongoTemplate.findOne(query, UserDocument.class);
        return user;
    }

    @Override
    public void saveUser(UserDocument userDocument) {
        mongoTemplate.save(userDocument);
    }
}
