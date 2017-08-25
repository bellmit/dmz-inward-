package com.dmz.basic.idao;

import com.dmz.basic.document.UserDocument;

/**
 * @author dmz
 * @date 2017/8/17
 */
public interface IUserDocumentDao{

     UserDocument findByName(String name);
     void saveUser(UserDocument userDocument);

}
