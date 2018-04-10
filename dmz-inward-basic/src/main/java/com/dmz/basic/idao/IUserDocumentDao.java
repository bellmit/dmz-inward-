package com.dmz.basic.idao;

import com.dmz.basic.document.UserDocument;
import com.mongodb.gridfs.GridFSDBFile;

import java.io.File;

/**
 * @author dmz
 * @date 2017/8/17
 */
public interface IUserDocumentDao {

    UserDocument findByName(String name);

    void saveUser(UserDocument userDocument);

    void saveFile(String collectionName, File file, String fileid, String companyid, String filename);

    GridFSDBFile retrieveFileOne(String collectionName, String fileId);


}
